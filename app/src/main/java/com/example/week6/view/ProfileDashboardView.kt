package com.example.week6.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week6.viewmodel.CampusSharedViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileDashboardView(sharedViewModel: CampusSharedViewModel) {
    val profile = sharedViewModel.profileState.value
    val joinedClubsCount = sharedViewModel.communities.count { it.isJoined }
    val totalProjectsCount = sharedViewModel.projects.size

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E88E5))
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(67.dp)
                            .background(Color.White.copy(alpha = 0.2f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = profile.name.take(2).uppercase(),
                            color = Color.White,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = profile.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Text(
                        text = profile.major,
                        fontSize = 15.sp,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                    Text(
                        text = profile.nim,
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.7f),
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(11.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                ) {
                    Column(
                        modifier = Modifier.padding(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$joinedClubsCount",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E88E5)
                        )
                        Text(text = "Joined Clubs", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    }
                }
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$totalProjectsCount",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(text = "Total Projects", fontSize = 11.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }

        item {
            Text(
                text = "Semua Komunitas yang Diikuti ($joinedClubsCount)",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color(0xFF1E88E5)
            )
        }

        val joinedList = sharedViewModel.communities.filter { it.isJoined }
        if (joinedList.isEmpty()) {
            item {
                Text(
                    text = "Belum bergabung dengan komunitas",
                    color = Color.Gray,
                    fontSize = 13.sp
                )
            }
        } else {
            items(joinedList, key = { it.id }) { club ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = club.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text(text = club.fullName, fontSize = 11.sp, color = Color.Gray)
                        }
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFFFEBEE), RoundedCornerShape(45.dp))
                                .padding(horizontal = 11.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "Joined",
                                color = Color(0xFFC62828),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "Semua Project Mahasiswa ($totalProjectsCount)",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        if (sharedViewModel.projects.isEmpty()) {
            item {
                Text(
                    text = "Belum ada Project",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        } else {
            items(sharedViewModel.projects, key = { it.id }) { proj ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(13.dp)
                ) {
                    Column(modifier = Modifier.padding(15.dp)) {
                        Text(text = proj.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(
                            text = sharedViewModel.communities.find { it.id == proj.communityId }?.name ?: "",
                            fontSize = 14.sp,
                            color = Color(0xFF1E88E5),
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = proj.description, fontSize = 15.sp, color = Color.Gray, maxLines = 2)
                        Spacer(modifier = Modifier.height(9.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            proj.techStack.forEach { tech ->
                                SuggestionChip(
                                    onClick = { },
                                    label = { Text(tech, fontSize = 12.sp) },
                                    shape = RoundedCornerShape(5.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
