package com.nxide.components.panels

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nxide.ui.theme.*

data class ProjectFile(
    val name: String,
    val type: String,
    val size: String,
    val modified: String
)

private val projectFiles = listOf(
    ProjectFile("app", "folder", "-", "2024-03-15"),
    ProjectFile("MainActivity.kt", "kotlin", "4.2 KB", "2024-03-15 08:36"),
    ProjectFile("Color.kt", "kotlin", "0.3 KB", "2024-03-15 08:30"),
    ProjectFile("Theme.kt", "kotlin", "1.1 KB", "2024-03-15 08:30"),
    ProjectFile("Type.kt", "kotlin", "0.8 KB", "2024-03-15 08:30"),
    ProjectFile("AndroidManifest.xml", "xml", "0.7 KB", "2024-03-15 08:25"),
    ProjectFile("strings.xml", "xml", "0.1 KB", "2024-03-15 08:20"),
    ProjectFile("themes.xml", "xml", "0.2 KB", "2024-03-15 08:20"),
    ProjectFile("build.gradle.kts (app)", "gradle", "1.8 KB", "2024-03-15 08:15"),
    ProjectFile("build.gradle.kts", "gradle", "0.2 KB", "2024-03-15 08:10"),
    ProjectFile("settings.gradle.kts", "gradle", "0.3 KB", "2024-03-15 08:10"),
    ProjectFile("gradle.properties", "properties", "0.2 KB", "2024-03-15 08:10"),
)

@Composable
fun FilesPanel(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        PanelHeader("📁 Project Files") {
            Text("${projectFiles.size} 个文件", fontSize = 11.sp, color = NxTextMuted)
        }

        // Header row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(NxBgSecondary)
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("名称", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = NxTextMuted, modifier = Modifier.weight(1f))
            Text("大小", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = NxTextMuted, modifier = Modifier.width(80.dp))
            Text("修改时间", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = NxTextMuted, modifier = Modifier.width(140.dp))
        }

        // File rows
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(projectFiles) { file ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { }
                        .padding(horizontal = 12.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            when (file.type) {
                                "kotlin" -> "🟣"
                                "xml" -> "🔵"
                                "gradle" -> "🟢"
                                "properties" -> "⚙️"
                                else -> "📁"
                            },
                            fontSize = 14.sp
                        )
                        Text(file.name, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = NxTextPrimary)
                    }
                    Text(file.size, fontSize = 12.sp, color = NxTextMuted, modifier = Modifier.width(80.dp))
                    Text(file.modified, fontSize = 12.sp, color = NxTextMuted, modifier = Modifier.width(140.dp))
                }
            }
        }
    }
}
