package com.nxide.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nxide.data.BottomPanelType
import com.nxide.ui.theme.*

@Composable
fun BottomToolbar(
    activePanel: BottomPanelType?,
    onPanelClick: (BottomPanelType) -> Unit,
    onAiClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(NxBgSecondary)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left panels
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            BottomPanelType.values().forEach { panel ->
                val isActive = panel == activePanel
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isActive) NxBgTertiary else NxBorder.copy(alpha = 0f))
                        .clickable { onPanelClick(panel) }
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(panel.icon, fontSize = 16.sp)
                        Text(
                            panel.label,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (isActive) NxGreen else NxTextSecondary
                        )
                    }
                }
            }
        }

        // AI button
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(
                    androidx.compose.ui.graphics.Brush.horizontalGradient(
                        colors = listOf(NxGreen.copy(alpha = 0.1f), NxBlue.copy(alpha = 0.1f))
                    )
                )
                .clickable { onAiClick() }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text("🤖", fontSize = 16.sp)
                Text("AI 助手", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = NxGreen)
            }
        }
    }
}
