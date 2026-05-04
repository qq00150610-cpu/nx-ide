package com.nxide.components.panels

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

@Composable
fun LayoutPanel(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        // Header
        PanelHeader("📐 Layout Inspector")

        Row(modifier = Modifier.weight(1f).padding(12.dp)) {
            // Phone preview
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                PhonePreview()
            }

            Spacer(Modifier.width(16.dp))

            // View hierarchy
            Column(
                modifier = Modifier
                    .width(220.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp))
                    .background(NxBgSecondary)
                    .padding(8.dp)
            ) {
                Text(
                    "VIEW HIERARCHY",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NxTextMuted,
                    letterSpacing = 0.5.sp
                )
                Spacer(Modifier.height(8.dp))
                TreeNode("📦 Scaffold", 0)
                TreeNode("📋 TopAppBar", 1)
                TreeNode("📦 Column", 1)
                TreeNode("📝 Text(\"欢迎使用\")", 2)
                TreeNode("📝 Text(\"NX IDE\")", 2)
                TreeNode("📦 Row", 2)
                TreeNode("🔘 AssistChip", 3)
                TreeNode("🔘 AssistChip", 3)
                TreeNode("📊 NavigationBar", 1)
            }
        }
    }
}

@Composable
private fun TreeNode(text: String, depth: Int) {
    Text(
        text,
        fontSize = 11.sp,
        color = NxTextSecondary,
        modifier = Modifier.padding(start = (depth * 16).dp, top = 2.dp, bottom = 2.dp),
        fontFamily = FontFamily.Monospace
    )
}

@Composable
private fun PhonePreview() {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(320.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(NxBgPrimary)
    ) {
        Column {
            // Status bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NxBgSecondary)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("8:40", fontSize = 9.sp, color = NxTextMuted)
                Text("📶 🔋", fontSize = 9.sp, color = NxTextMuted)
            }

            // Toolbar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NxBgTertiary)
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("☰", fontSize = 11.sp, color = NxTextPrimary)
                Text("NX IDE", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = NxTextPrimary)
                Text("⋮", fontSize = 11.sp, color = NxTextPrimary)
            }

            // Content
            Column(modifier = Modifier.weight(1f).padding(8.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(NxBgCard)
                        .padding(8.dp)
                ) {
                    Column {
                        Text("欢迎使用 NX IDE", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = NxGreen)
                        Text("AI驱动的安卓开发环境", fontSize = 9.sp, color = NxTextMuted)
                        Spacer(Modifier.height(6.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Chip("＋ 新建项目")
                            Chip("📂 打开项目")
                        }
                    }
                }
                Spacer(Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(NxBgCard)
                        .padding(8.dp)
                ) {
                    Column {
                        Text("快速开始", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = NxTextPrimary)
                        ListItem("📱 我的安卓项目")
                        ListItem("🎨 Compose 示例")
                    }
                }
            }

            // Bottom nav
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NxBgSecondary)
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("🏠 首页", fontSize = 9.sp, color = NxGreen)
                Text("📁 项目", fontSize = 9.sp, color = NxTextMuted)
                Text("⚙️ 设置", fontSize = 9.sp, color = NxTextMuted)
            }
        }
    }
}

@Composable
private fun Chip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(NxBgTertiary)
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(text, fontSize = 9.sp, color = NxTextSecondary)
    }
}

@Composable
private fun ListItem(text: String) {
    Text(
        text,
        fontSize = 10.sp,
        color = NxTextSecondary,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}
