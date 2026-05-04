package com.nxide.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nxide.data.ProjectTemplate
import com.nxide.data.TemplateCategory
import com.nxide.ui.theme.*

@Composable
fun TemplateScreen(
    templates: List<ProjectTemplate>,
    selectedCategory: TemplateCategory,
    onCategorySelect: (TemplateCategory) -> Unit,
    onTemplateSelect: (ProjectTemplate) -> Unit,
    modifier: Modifier = Modifier
) {
    val filtered = if (selectedCategory == TemplateCategory.ALL) templates
    else templates.filter { it.category == selectedCategory }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NxBgPrimary)
            .padding(20.dp)
    ) {
        // Title
        Text("选择项目模板", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = NxTextPrimary)
        Text("选择一个模板开始创建新项目", fontSize = 13.sp, color = NxTextMuted)

        Spacer(Modifier.height(16.dp))

        // Filters
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(NxBgSecondary)
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            FilterGroup("语言:", listOf("Kotlin" to true, "Java" to false))
            FilterGroup("构建:", listOf("Gradle" to true, "Groovy" to false))
            FilterGroup("UI:", listOf("Jetpack Compose" to true, "XML" to false))
        }

        Spacer(Modifier.height(12.dp))

        // Category tabs
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(TemplateCategory.values()) { category ->
                val isActive = category == selectedCategory
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isActive) NxGreen else NxBgSecondary)
                        .clickable { onCategorySelect(category) }
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Text(
                        category.displayName,
                        fontSize = 13.sp,
                        color = if (isActive) NxBgPrimary else NxTextSecondary,
                        fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Template grid
        LazyVerticalGrid(
            columns = GridCells.Adaptive(220.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(filtered) { template ->
                TemplateCard(template, onTemplateSelect)
            }
        }
    }
}

@Composable
private fun FilterGroup(label: String, options: List<Pair<String, Boolean>>) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(label, fontSize = 12.sp, color = NxTextMuted, fontWeight = FontWeight.SemiBold)
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            options.forEach { (text, isActive) ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (isActive) NxGreen else NxBgInput)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text,
                        fontSize = 12.sp,
                        color = if (isActive) NxBgPrimary else NxTextSecondary,
                        fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
private fun TemplateCard(
    template: ProjectTemplate,
    onSelect: (ProjectTemplate) -> Unit
) {
    val color = Color(template.color)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(NxBgCard)
            .clickable { onSelect(template) }
            .padding(16.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(template.icon, fontSize = 22.sp)
            }

            Spacer(Modifier.height(12.dp))

            Text(template.name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = NxTextPrimary)
            Text(template.description, fontSize = 12.sp, color = NxTextMuted, lineHeight = 16.sp)

            Spacer(Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                template.tags.forEach { tag ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(NxBgInput)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(tag, fontSize = 10.sp, color = NxTextMuted)
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color)
                    .clickable { onSelect(template) }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("使用此模板", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = NxBgPrimary)
            }
        }
    }
}
