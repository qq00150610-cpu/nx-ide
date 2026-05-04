package com.nxide.data

/**
 * 默认项目文件结构
 */
object DefaultProject {
    val projectName = "我的安卓项目"

    val files = listOf(
        FileNode(
            name = "app",
            type = FileType.FOLDER,
            isExpanded = true,
            children = listOf(
                FileNode(
                    name = "src",
                    type = FileType.FOLDER,
                    isExpanded = true,
                    children = listOf(
                        FileNode(
                            name = "main",
                            type = FileType.FOLDER,
                            isExpanded = true,
                            children = listOf(
                                FileNode(
                                    name = "java",
                                    type = FileType.FOLDER,
                                    isExpanded = true,
                                    children = listOf(
                                        FileNode(
                                            name = "com",
                                            type = FileType.FOLDER,
                                            isExpanded = true,
                                            children = listOf(
                                                FileNode(
                                                    name = "nxide",
                                                    type = FileType.FOLDER,
                                                    isExpanded = true,
                                                    children = listOf(
                                                        FileNode(
                                                            name = "MainActivity.kt",
                                                            type = FileType.FILE,
                                                            language = CodeLanguage.KOTLIN,
                                                            content = SampleCode.MAIN_ACTIVITY
                                                        ),
                                                        FileNode(
                                                            name = "ui.theme",
                                                            type = FileType.FOLDER,
                                                            isExpanded = false,
                                                            children = listOf(
                                                                FileNode("Color.kt", FileType.FILE, CodeLanguage.KOTLIN, content = SampleCode.COLOR_KT),
                                                                FileNode("Theme.kt", FileType.FILE, CodeLanguage.KOTLIN, content = SampleCode.THEME_KT),
                                                                FileNode("Type.kt", FileType.FILE, CodeLanguage.KOTLIN, content = SampleCode.TYPE_KT),
                                                            )
                                                        ),
                                                    )
                                                )
                                            )
                                        )
                                    )
                                ),
                                FileNode(
                                    name = "res",
                                    type = FileType.FOLDER,
                                    isExpanded = false,
                                    children = listOf(
                                        FileNode(
                                            name = "values",
                                            type = FileType.FOLDER,
                                            children = listOf(
                                                FileNode("strings.xml", FileType.FILE, CodeLanguage.XML, content = SampleCode.STRINGS_XML),
                                                FileNode("themes.xml", FileType.FILE, CodeLanguage.XML, content = SampleCode.THEMES_XML),
                                            )
                                        ),
                                        FileNode("drawable", FileType.FOLDER),
                                    )
                                ),
                                FileNode("AndroidManifest.xml", FileType.FILE, CodeLanguage.XML, content = SampleCode.ANDROID_MANIFEST),
                            )
                        )
                    )
                ),
                FileNode("build.gradle.kts", FileType.FILE, CodeLanguage.GRADLE, content = SampleCode.APP_BUILD_GRADLE),
            )
        ),
        FileNode("build.gradle.kts", FileType.FILE, CodeLanguage.GRADLE, content = SampleCode.ROOT_BUILD_GRADLE),
        FileNode("settings.gradle.kts", FileType.FILE, CodeLanguage.GRADLE, content = SampleCode.SETTINGS_GRADLE),
        FileNode("gradle.properties", FileType.FILE, CodeLanguage.PROPERTIES, content = SampleCode.GRADLE_PROPERTIES),
    )

    val defaultLogs = listOf(
        LogEntry(time = "08:36:21", level = LogLevel.INFO, tag = "AndroidRuntime", message = "Starting activity com.nxide.MainActivity"),
        LogEntry(time = "08:36:22", level = LogLevel.DEBUG, tag = "Compose", message = "Composition: setContent called"),
        LogEntry(time = "08:36:22", level = LogLevel.INFO, tag = "MaterialTheme", message = "Applying dark color scheme"),
        LogEntry(time = "08:36:23", level = LogLevel.WARN, tag = "Deprecation", message = "TopAppBar is deprecated, use CenterAlignedTopAppBar"),
        LogEntry(time = "08:36:23", level = LogLevel.INFO, tag = "MainActivity", message = "onCreate completed in 245ms"),
        LogEntry(time = "08:36:24", level = LogLevel.DEBUG, tag = "Navigation", message = "Navigating to HomeScreen"),
        LogEntry(time = "08:36:25", level = LogLevel.INFO, tag = "System", message = "App fully loaded and interactive"),
    )

    val buildSteps = listOf(
        BuildStep(1, "Clean"),
        BuildStep(2, "Build Config"),
        BuildStep(3, "Compile Kotlin"),
        BuildStep(4, "Process Resources"),
        BuildStep(5, "Link Resources"),
        BuildStep(6, "Merge Manifests"),
        BuildStep(7, "Package APK"),
        BuildStep(8, "Install & Run"),
    )

    val templates = listOf(
        ProjectTemplate("empty", "Empty Activity", "创建空的Activity项目", TemplateCategory.BASIC, "📱", 0xFF4ADE80, listOf("Kotlin", "Gradle")),
        ProjectTemplate("compose", "Empty Compose Activity", "使用Jetpack Compose创建空项目", TemplateCategory.BASIC, "🎨", 0xFF60A5FA, listOf("Kotlin", "Gradle")),
        ProjectTemplate("fragment", "Fragment + ViewModel", "使用Fragment和ViewModel的项目模板", TemplateCategory.ARCHITECTURE, "🧩", 0xFFA78BFA, listOf("Kotlin", "Gradle")),
        ProjectTemplate("navigation", "Navigation Drawer Activity", "带导航抽屉的Activity", TemplateCategory.UI_COMPONENTS, "🧭", 0xFFFB923C, listOf("Kotlin", "Gradle")),
        ProjectTemplate("bottomnav", "Bottom Navigation Activity", "底部导航栏Activity", TemplateCategory.UI_COMPONENTS, "📊", 0xFFF472B6, listOf("Kotlin", "Gradle")),
        ProjectTemplate("viewpager", "ViewPager2 Activity", "使用ViewPager2的滑动页面", TemplateCategory.UI_COMPONENTS, "📄", 0xFF22D3EE, listOf("Kotlin", "Gradle")),
        ProjectTemplate("recyclerview", "RecyclerView Activity", "使用RecyclerView的列表页面", TemplateCategory.UI_COMPONENTS, "📋", 0xFF4ADE80, listOf("Kotlin", "Gradle")),
        ProjectTemplate("tabs", "Tabbed Activity", "使用TabLayout的Activity", TemplateCategory.UI_COMPONENTS, "📑", 0xFF60A5FA, listOf("Kotlin", "Gradle")),
        ProjectTemplate("maps", "Google Maps Activity", "使用Google Maps的地图应用", TemplateCategory.GOOGLE, "🗺️", 0xFF22C55E, listOf("Kotlin", "Gradle")),
        ProjectTemplate("admob", "Google AdMob Ads Activity", "集成Google AdMob广告", TemplateCategory.GOOGLE, "📢", 0xFFEAB308, listOf("Kotlin", "Gradle")),
        ProjectTemplate("login", "Login Activity", "登录页面模板", TemplateCategory.BASIC, "🔐", 0xFFEF4444, listOf("Kotlin", "Gradle")),
        ProjectTemplate("master", "Master/Detail Flow", "主从详情页面", TemplateCategory.ARCHITECTURE, "📱", 0xFFA78BFA, listOf("Kotlin", "Gradle")),
        ProjectTemplate("fullscreen", "Fullscreen Activity", "全屏Activity", TemplateCategory.BASIC, "🖥️", 0xFF1E40AF, listOf("Kotlin", "Gradle")),
        ProjectTemplate("settings", "Settings Activity", "设置页面模板", TemplateCategory.UI_COMPONENTS, "⚙️", 0xFF6B7280, listOf("Kotlin", "Gradle")),
        ProjectTemplate("scroller", "Scrolling Activity", "可滚动的Activity", TemplateCategory.UI_COMPONENTS, "📜", 0xFFFB923C, listOf("Kotlin", "Gradle")),
        ProjectTemplate("sqlite", "SQLite Activity", "使用SQLite数据库", TemplateCategory.DATA, "🗃️", 0xFF22D3EE, listOf("Kotlin", "Gradle")),
        ProjectTemplate("content", "Content Provider", "内容提供者模板", TemplateCategory.DATA, "🔗", 0xFFF472B6, listOf("Kotlin", "Gradle")),
        ProjectTemplate("service", "Foreground Service", "前台服务模板", TemplateCategory.SYSTEM, "🔄", 0xFF4ADE80, listOf("Kotlin", "Gradle")),
        ProjectTemplate("broadcast", "Broadcast Receiver", "广播接收器模板", TemplateCategory.SYSTEM, "📡", 0xFF60A5FA, listOf("Kotlin", "Gradle")),
        ProjectTemplate("intent", "Intent Service", "Intent服务模板", TemplateCategory.SYSTEM, "📨", 0xFFA78BFA, listOf("Kotlin", "Gradle")),
    )

    val terminalLines = listOf(
        TerminalLine("$ gradle assembleDebug", isCommand = true),
        TerminalLine(""),
        TerminalLine("> Task :app:preBuild UP-TO-DATE"),
        TerminalLine("> Task :app:preDebugBuild UP-TO-DATE"),
        TerminalLine("> Task :app:mergeDebugNativeDebugMetadata NO-SOURCE"),
        TerminalLine("> Task :app:generateDebugBuildConfig UP-TO-DATE"),
        TerminalLine("> Task :app:compileDebugKotlin UP-TO-DATE"),
        TerminalLine("> Task :app:processDebugResources UP-TO-DATE"),
        TerminalLine("> Task :app:packageDebug UP-TO-DATE"),
        TerminalLine(""),
        TerminalLine("BUILD SUCCESSFUL in 2s", isCommand = true),
        TerminalLine("8 actionable tasks: 8 up-to-date"),
        TerminalLine("$ adb install -r app-debug.apk", isCommand = true),
        TerminalLine("Performing Streamed Install"),
        TerminalLine("Success"),
        TerminalLine("$ adb shell am start -n com.nxide/.MainActivity", isCommand = true),
        TerminalLine("Starting: Intent { cmp=com.nxide/.MainActivity }"),
    )
}
