package com.example.week6.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week6.viewmodel.ClubProjectsDetailViewModel
import com.example.week6.viewmodel.FormViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ClubProjectsDetailView(
    communityId: String,
    viewModel: ClubProjectsDetailViewModel,
    onProjectClick: (String) -> Unit,
    onAddProjectClick: (title: String, description: String, techStack: List<String>) -> Unit
) {
    val club = viewModel.getCommunity(communityId) ?: return
    val listProj = viewModel.getProjectsForCommunity(communityId)
    var showDialog by remember { mutableStateOf(false) }
    val dialogViewModel: FormViewModel = viewModel()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showDialog = true },
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = { Text("Add Project") },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = RoundedCornerShape(14.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(17.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(17.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(
                        alpha = 0.4f
                    )
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = club.fullName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "${club.memberCount} Anggota • ${listProj.size} Proyek Aktif",
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFE8F5E9), RoundedCornerShape(50.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            "Joined ✓",
                            color = Color(0xFF2E7D32),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Text(
                text = "Daftar Proyek Mahasiswa (${listProj.size})",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (listProj.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Belum ada proyek yang dicatat",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(listProj, key = { it.id }) { proj ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            onClick = { onProjectClick(proj.id) }
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = proj.title,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = proj.description,
                                        fontSize = 12.sp,
                                        color = Color.Gray,
                                        maxLines = 3
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))

                                    FlowRow(
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        verticalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        proj.techStack.forEach { tech ->
                                            SuggestionChip(
                                                onClick = {},
                                                label = { Text(tech, fontSize = 11.sp) },
                                                shape = RoundedCornerShape(6.dp)
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "ID: proj-${proj.communityId}-${proj.id.take(5)}",
                                            fontSize = 11.sp,
                                            color = Color.LightGray,
                                        )
                                        Box(
                                            modifier = Modifier
                                                .background(
                                                    if (proj.status == "Completed") Color(0xFFE8F5E9) else Color(
                                                        0xFFFFF3E0
                                                    ),
                                                    RoundedCornerShape(6.dp)
                                                )
                                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                        ) {
                                            Text(
                                                text = if (proj.status == "Completed") "• Completed" else "• In Progress",
                                                color = if (proj.status == "Completed") Color(
                                                    0xFF2E7D32
                                                ) else Color(0xFFE65100),
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }

                                IconButton(
                                    onClick = { viewModel.deleteProject(proj.id) },
                                    modifier = Modifier.offset(x = 8.dp, y = (-8).dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.RemoveCircle,
                                        contentDescription = "Delete Project",
                                        tint = Color(0xFFD32F2F)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    if (showDialog) {
        AddProjectDialog(
            communityName = club.name,
            onDismiss = {
                dialogViewModel.resetForm()
                showDialog = false
            },
            onSave = { typedTitle, typedDesc, typedTech ->
                onAddProjectClick(typedTitle, typedDesc, typedTech)
                dialogViewModel.resetForm()
                showDialog = false
            }
        )
    }
}