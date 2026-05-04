package com.nxide.components.panels

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.nxide.data.LogEntry
import com.nxide.data.LogLevel
import com.nxide.ui.theme.*

@Composable
fun LogcatPanel(
    logs: List<LogEntry>,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(NxBgSecondary)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("📋 Logcat", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = NxTextSecondary)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(NxBgInput)
                        .clickable { onClear() }
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                ) {
                    Text("🗑️ 清除", fontSize = 11.sp, color = NxTextSecondary)
                }
                Text("${logs.size} 条日志", fontSize = 11.sp, color = NxTextMuted)
            }
        }

        HorizontalDivider(color = NxBorder)

        // Logs
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(logs) { log ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 1.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(log.time, fontSize = 11.sp, color = NxTextMuted, fontFamily = FontFamily.Monospace, modifier = Modifier.width(60.dp))
                    Text(
                        log.level.label,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Monospace,
                        color = when (log.level) {
                            LogLevel.INFO -> NxBlue
                            LogLevel.WARN -> NxYellow
                            LogLevel.ERROR -> NxRed
                            LogLevel.DEBUG -> NxPurple
                        },
                        modifier = Modifier.width(48.dp)
                    )
                    Text(log.tag, fontSize = 11.sp, color = NxCyan, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Medium, modifier = Modifier.width(100.dp))
                    Text(
                        log.message,
                        fontSize = 11.sp,
                        color = when (log.level) {
                            LogLevel.ERROR -> NxRed
                            LogLevel.WARN -> NxYellow
                            else -> NxTextSecondary
                        },
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}
