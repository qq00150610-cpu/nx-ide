package com.nxide.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nxide.data.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

data class NxIdeState(
    val activeTab: MainTab = MainTab.PROJECT,
    val activeBottomPanel: BottomPanelType? = null,
    val projectName: String = DefaultProject.projectName,
    val files: List<FileNode> = DefaultProject.files,
    val activeFile: String = "MainActivity.kt",
    val fileContents: Map<String, String> = mapOf(
        "MainActivity.kt" to SampleCode.MAIN_ACTIVITY,
        "Color.kt" to SampleCode.COLOR_KT,
        "Theme.kt" to SampleCode.THEME_KT,
        "Type.kt" to SampleCode.TYPE_KT,
        "AndroidManifest.xml" to SampleCode.ANDROID_MANIFEST,
        "strings.xml" to SampleCode.STRINGS_XML,
        "themes.xml" to SampleCode.THEMES_XML,
        "build.gradle.kts (app)" to SampleCode.APP_BUILD_GRADLE,
        "build.gradle.kts" to SampleCode.ROOT_BUILD_GRADLE,
        "settings.gradle.kts" to SampleCode.SETTINGS_GRADLE,
        "gradle.properties" to SampleCode.GRADLE_PROPERTIES,
    ),
    val templateCategory: TemplateCategory = TemplateCategory.ALL,
    val templates: List<ProjectTemplate> = DefaultProject.templates,
    val logs: List<LogEntry> = DefaultProject.defaultLogs,
    val buildSteps: List<BuildStep> = DefaultProject.buildSteps,
    val isBuilding: Boolean = false,
    val terminalLines: List<TerminalLine> = DefaultProject.terminalLines,
    val sidebarOpen: Boolean = true,
    val aiPrompt: String = "",
    val aiMessages: List<AiMessage> = emptyList(),
    val showAiPanel: Boolean = false,
    val isAiThinking: Boolean = false
)

class NxIdeViewModel : ViewModel() {
    private val _state = MutableStateFlow(NxIdeState())
    val state: StateFlow<NxIdeState> = _state.asStateFlow()

    fun setTab(tab: MainTab) {
        _state.update { it.copy(activeTab = tab) }
    }

    fun toggleBottomPanel(panel: BottomPanelType) {
        _state.update { current ->
            current.copy(
                activeBottomPanel = if (current.activeBottomPanel == panel) null else panel
            )
        }
    }

    fun setActiveFile(fileName: String) {
        _state.update { it.copy(activeFile = fileName) }
    }

    fun setFileContent(fileName: String, content: String) {
        _state.update { current ->
            current.copy(fileContents = current.fileContents + (fileName to content))
        }
    }

    fun toggleFolder(folderName: String) {
        _state.update { current ->
            current.copy(files = toggleFolderRecursive(current.files, folderName))
        }
    }

    private fun toggleFolderRecursive(nodes: List<FileNode>, name: String): List<FileNode> {
        return nodes.map { node ->
            if (node.name == name && node.type == FileType.FOLDER) {
                node.copy(isExpanded = !node.isExpanded)
            } else if (node.children.isNotEmpty()) {
                node.copy(children = toggleFolderRecursive(node.children, name))
            } else {
                node
            }
        }
    }

    fun setTemplateCategory(category: TemplateCategory) {
        _state.update { it.copy(templateCategory = category) }
    }

    fun clearLogs() {
        _state.update { it.copy(logs = emptyList()) }
    }

    fun startBuild() {
        val current = _state.value
        if (current.isBuilding) return

        _state.update {
            it.copy(
                isBuilding = true,
                buildSteps = DefaultProject.buildSteps.map { s -> s.copy(status = BuildStatus.PENDING) }
            )
        }

        viewModelScope.launch {
            val durations = listOf(100L, 300L, 500L, 200L, 150L, 100L, 400L, 300L)
            val steps = DefaultProject.buildSteps

            for (i in steps.indices) {
                _state.update { current ->
                    current.copy(
                        buildSteps = current.buildSteps.map { s ->
                            if (s.id == steps[i].id) s.copy(status = BuildStatus.RUNNING) else s
                        }
                    )
                }
                delay(durations[i] + Random.nextInt(200))
                _state.update { current ->
                    current.copy(
                        buildSteps = current.buildSteps.map { s ->
                            if (s.id == steps[i].id) s.copy(
                                status = BuildStatus.SUCCESS,
                                duration = "${durations[i] / 1000.0}s".let { d ->
                                    String.format("%.1fs", durations[i] / 1000.0)
                                }
                            ) else s
                        }
                    )
                }
            }

            _state.update { it.copy(isBuilding = false) }
            addLog(LogLevel.INFO, "Build", "BUILD SUCCESSFUL - APK installed and running")
        }
    }

    fun resetBuild() {
        _state.update {
            it.copy(
                buildSteps = DefaultProject.buildSteps.map { s -> s.copy(status = BuildStatus.PENDING, duration = null) },
                isBuilding = false
            )
        }
    }

    fun addLog(level: LogLevel, tag: String, message: String) {
        val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        _state.update { current ->
            current.copy(logs = current.logs + LogEntry(time = time, level = level, tag = tag, message = message))
        }
    }

    fun clearTerminal() {
        _state.update { it.copy(terminalLines = listOf(TerminalLine("$ ", isCommand = true))) }
    }

    fun executeTerminalCommand(command: String) {
        _state.update { current ->
            val newLines = current.terminalLines + TerminalLine("$ $command", isCommand = true)
            current.copy(terminalLines = newLines)
        }

        val cmd = command.trim().lowercase()
        when {
            cmd == "clear" -> clearTerminal()
            cmd == "ls" -> addTerminalLine("app  build.gradle.kts  settings.gradle.kts  gradle.properties")
            cmd.startsWith("gradle tasks") -> {
                addTerminalLine("")
                addTerminalLine("Build tasks", isCommand = true)
                addTerminalLine("-----------")
                addTerminalLine("assemble - Assembles the outputs of this project.")
                addTerminalLine("build - Assembles and tests this project.")
                addTerminalLine("clean - Deletes the build directory.")
            }
            cmd.startsWith("adb") -> {
                addTerminalLine("Android Debug Bridge version 1.0.41")
                addTerminalLine("Installed as /Users/dev/Library/Android/sdk/platform-tools/adb")
            }
            cmd.startsWith("git") -> {
                addTerminalLine("On branch main")
                addTerminalLine("Your branch is up to date with 'origin/main'.")
                addTerminalLine("nothing to commit, working tree clean")
            }
            cmd == "help" -> {
                addTerminalLine("Available commands: ls, gradle, adb, git, clear, help")
            }
            else -> addTerminalLine("bash: ${command.trim()}: command not found")
        }
    }

    private fun addTerminalLine(text: String, isCommand: Boolean = false) {
        _state.update { current ->
            current.copy(terminalLines = current.terminalLines + TerminalLine(text, isCommand))
        }
    }

    fun setAiPrompt(prompt: String) {
        _state.update { it.copy(aiPrompt = prompt) }
    }

    fun toggleAiPanel() {
        _state.update { it.copy(showAiPanel = !it.showAiPanel) }
    }

    fun sendAiMessage() {
        val prompt = _state.value.aiPrompt.trim()
        if (prompt.isEmpty()) return

        _state.update { current ->
            current.copy(
                aiMessages = current.aiMessages + AiMessage(role = MessageRole.USER, content = prompt),
                aiPrompt = "",
                isAiThinking = true
            )
        }

        viewModelScope.launch {
            delay(1000 + Random.nextInt(1500))

            val response = generateAiResponse(prompt)
            _state.update { current ->
                current.copy(
                    aiMessages = current.aiMessages + AiMessage(role = MessageRole.AI, content = response),
                    isAiThinking = false
                )
            }
        }
    }

    private fun generateAiResponse(prompt: String): String {
        return when {
            "导航" in prompt || "底部" in prompt -> """我来帮你添加底部导航栏。首先在 Scaffold 的 bottomBar 参数中添加 NavigationBar 组件：

```kotlin
NavigationBar {
    items.forEachIndexed { index, item ->
        NavigationBarItem(
            icon = { Icon(item.icon, null) },
            label = { Text(item.title) },
            selected = selectedItem == index,
            onClick = { selectedItem = index }
        )
    }
}
```

需要我进一步完善导航逻辑吗？"""

            "主题" in prompt || "配色" in prompt -> """建议使用 Material3 的动态配色方案：

```kotlin
val colorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
    if (darkTheme) dynamicDarkColorScheme(context)
    else dynamicLightColorScheme(context)
} else {
    if (darkTheme) darkColorScheme(primary = Green40)
    else lightColorScheme(primary = Green80)
}
```

这样可以在 Android 12+ 设备上自动适配系统主题色。"""

            "数据库" in prompt || "Room" in prompt -> """添加 Room 数据库需要以下步骤：

1. **添加依赖** - 在 build.gradle.kts 中
2. **创建 Entity** - 定义数据表结构
3. **创建 DAO** - 定义数据库操作
4. **创建 Database** - 数据库实例

需要我生成完整的数据库代码吗？"""

            "测试" in prompt -> """我可以为你生成以下测试：

- **单元测试**: ViewModel 逻辑测试
- **UI 测试**: Compose 界面测试
- **集成测试**: 数据库操作测试

请选择需要的测试类型。"""

            "代码" in prompt || "写" in prompt || "生成" in prompt -> """好的，我来为你生成代码。基于当前项目结构：

```kotlin
@Composable
fun NewFeature() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "新功能",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        // 在这里添加更多组件
    }
}
```

你可以将这段代码添加到项目中。需要我进一步完善吗？"""

            "优化" in prompt || "重构" in prompt -> """我建议采用以下优化方案：

1. **MVVM 架构** - 使用 ViewModel + StateFlow
2. **依赖注入** - 使用 Hilt 管理依赖
3. **Repository 模式** - 统一数据访问层
4. **UseCase 模式** - 封装业务逻辑

需要我详细展开某个方面吗？"""

            else -> """好的，我来帮你处理这个问题。

根据你的项目结构，我建议：
1. 先分析当前代码的架构
2. 确定需要修改的部分
3. 逐步实现改动

请告诉我更具体的需求，我可以提供更有针对性的帮助。"""
        }
    }

    fun toggleSidebar() {
        _state.update { it.copy(sidebarOpen = !it.sidebarOpen) }
    }
}
