package com.example.week6.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week6.viewmodel.CampusSharedViewModel

@Composable
fun CommunitiesDirectoryView(
    sharedViewModel: CampusSharedViewModel,
    onNavigateToClubDetail: (String) -> Unit
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(17.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Communities",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                Text(
                    text = "Pilih komunitas untuk membuka Club Projects",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        items(sharedViewModel.communities, key = { it.id }) { club ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                onClick = {
                    if (club.isJoined) {
                        onNavigateToClubDetail(club.id)
                    } else {
                        Toast.makeText(
                            context,
                            "Harap bergabung ke komunitas terlebih dahulu!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = club.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            Text(text = "${club.fullName} • ${club.memberCount} Mahasiswa", fontSize = 11.sp, color = Color.Gray)
                        }

                        Button(
                            onClick = { sharedViewModel.toggleJoinCommunity(club.id) },
                            shape = RoundedCornerShape(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (club.isJoined) Color(0xFFFFEBEE) else Color(0xFF1E88E5),
                                contentColor = if (club.isJoined) Color(0xFFC62828) else Color.White
                            ),
                            contentPadding = PaddingValues(horizontal = 15.dp, vertical = 0.dp),
                            modifier = Modifier.height(36.dp)
                        ) {
                            Text(
                                text = if (club.isJoined) "Joined" else "+ Join",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = club.description,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 19.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))
                    Spacer(modifier = Modifier.height(7.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (club.isJoined) {
                            Text(
                                text = "Unlocked",
                                fontSize = 14.sp,
                                color = Color(0xFF1E88E5),
                                fontWeight = FontWeight.SemiBold
                            )
                        } else {
                            Text(
                                text = "Locked",
                                fontSize = 14.sp,
                                color = Color.LightGray,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}
