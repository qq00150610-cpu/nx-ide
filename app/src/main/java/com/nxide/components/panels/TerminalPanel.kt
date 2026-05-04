package com.nxide.components.panels

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nxide.data.TerminalLine
import com.nxide.ui.theme.*

@Composable
fun TerminalPanel(
    lines: List<TerminalLine>,
    onClear: () -> Unit,
    onExecute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    LaunchedEffect(lines.size) {
        if (lines.isNotEmpty()) {
            listState.animateScrollToItem(lines.size - 1)
        }
    }

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
            Text("💻 Terminal", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = NxTextSecondary)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(NxBgInput)
                    .clickable { onClear() }
                    .padding(horizontal = 10.dp, vertical = 3.dp)
            ) {
                Text("🗑️ 清除", fontSize = 11.sp, color = NxTextSecondary)
            }
        }

        HorizontalDivider(color = NxBorder)

        // Terminal content
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f).padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            items(lines) { line ->
                Text(
                    line.text,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    color = if (line.isCommand) NxGreen else NxTextSecondary,
                    fontWeight = if (line.isCommand) FontWeight.Medium else FontWeight.Normal,
                    lineHeight = 20.sp
                )
            }
        }

        // Input
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(NxBgSecondary)
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("$ ", fontSize = 12.sp, color = NxGreen, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Monospace)
            BasicTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    color = NxTextPrimary,
                    fontFamily = FontFamily.Monospace
                ),
                cursorBrush = SolidColor(NxGreen),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Box {
                        if (input.isEmpty()) {
                            Text("输入命令...", fontSize = 12.sp, color = NxTextMuted, fontFamily = FontFamily.Monospace)
                        }
                        innerTextField()
                    }
                }
            )
            if (input.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(NxGreen.copy(alpha = 0.2f))
                        .clickable {
                            onExecute(input)
                            input = ""
                        }
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text("⏎", fontSize = 12.sp, color = NxGreen)
                }
            }
        }
    }
}
