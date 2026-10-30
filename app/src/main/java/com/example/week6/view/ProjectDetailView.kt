package com.example.week6.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week6.model.Project

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectDetailView(
    project: Project,
    communityName: String,
    isCompleted: Boolean,
    onStatusToggle: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF1E88E5), RoundedCornerShape(6.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(text = communityName, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }

        Text(
            text = project.title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(11.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
        ) {
            Row(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Mark as Done", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(
                        text = if (isCompleted) "Status: Completed" else "Status: In Progress",
                        fontSize = 11.sp,
                        color = if (isCompleted) Color(0xFF2E7D32) else Color(0xFFE65100),
                        fontWeight = FontWeight.Medium
                    )
                }
                Switch(
                    checked = isCompleted,
                    onCheckedChange = onStatusToggle
                )
            }
        }

        Text(text = "Deskripsi Project", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(
            text = project.description,
            fontSize = 14.sp,
            color = Color.Gray,
            lineHeight = 20.sp
        )

        Text(text = "Tech Stack yang dipilih", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            project.techStack.forEach { tech ->
                SuggestionChip(
                    onClick = {},
                    label = { Text(tech, fontSize = 13.sp) },
                    shape = RoundedCornerShape(5.dp)
                )
            }
        }
    }
}
