package com.example.whatsapp.introdution.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.whatsapp.R
import com.example.whatsapp.introdution.bottomnav.BottomNavigation
import com.example.whatsapp.introdution.chatbox.ChatListBox
import com.example.whatsapp.introdution.chatbox.ChatListModel
import com.example.whatsapp.introdution.navigation.Routes
import com.example.whatsapp.introdution.viewmodels.BaseViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(navHostController: NavHostController, homeBaseViewModel: BaseViewModel) {
    var showPopup by remember {
        mutableStateOf(false)
    }
    val chatData by homeBaseViewModel.chatList.collectAsState()
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    if (userId != null) {
        LaunchedEffect(Unit) {
            homeBaseViewModel.getChatForUser(userId) { chats ->

            }
        }
    }
    var showMenu by remember { mutableStateOf(false) }
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
                showPopup = true
            },
            containerColor = colorResource(id = R.color.light_green),
            contentColor = Color.White,
            modifier = Modifier.size(65.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add_chat),
                contentDescription = null,
                modifier = Modifier.size(28.dp),
                tint = Color.White
            )
        }
    }, bottomBar = {
        BottomNavigation(navHostController, selectedItem = 0, onClick = { index ->
            when (index) {
                0 -> {
                    navHostController.navigate(Routes.HomeScreen)
                }

                1 -> {
                    navHostController.navigate(Routes.UpdateScreen)
                }

                2 -> {
                    navHostController.navigate(Routes.CommunitiesScreen)
                }

                3 -> {
                    navHostController.navigate(Routes.CallScreen)
                }
            }
        })
    }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(color = Color.White)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                var isSearching by remember { mutableStateOf(false) }
                var searchText by remember { mutableStateOf("") }
                var showMenu by remember { mutableStateOf(false) }

                if (isSearching) {
                    TextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                        },
                        placeholder = {
                            Text("Search", color = Color.Gray)
                        },
                        singleLine = true,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(start = 12.dp)
                            .fillMaxWidth(0.8f),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent
                        )
                    )
                } else {
                    Text(
                        "WhatsApp",
                        fontSize = 28.sp,
                        color = colorResource(R.color.light_green),
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 12.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.ic_camera),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        if (isSearching) {
                            IconButton(onClick = {
                                isSearching = false
                                searchText = ""
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_close),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                isSearching = true
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_search),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                        IconButton(onClick = {
                            showMenu = !showMenu
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_menu),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            DropdownMenu(expanded = showMenu, onDismissRequest = {
                                showMenu = false
                            }, modifier = Modifier.background(color = Color.White)) {
                                DropdownMenuItem(
                                    text = { Text("New Group") },
                                    onClick = { showMenu = false })
                                DropdownMenuItem(
                                    text = { Text("New Broadcast") },
                                    onClick = { showMenu = false })
                                DropdownMenuItem(
                                    text = { Text("Linked Device") },
                                    onClick = { showMenu = false })
                                DropdownMenuItem(
                                    text = { Text("Starred Messages") },
                                    onClick = { showMenu = false })
                                DropdownMenuItem(text = { Text("Settings") }, onClick = {
                                    showMenu = false
                                    navHostController.navigate(Routes.SettingScreen)
                                })
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(12.dp))
            if (showPopup) {
                AddUserPopup(onDismiss = { showPopup = false }, onUserAdd = { newUser ->
                    homeBaseViewModel.addChat(newUser)
                }, baseViewModel = homeBaseViewModel)
            }
            LazyColumn {
                items(chatData) { chat ->
                    ChatListBox(chatListModel = chat, onClick = {
                        navHostController.navigate(
                            Routes.ChatScreen.createRoute(
                                phoneNumber = chat.phoneNumber ?: "ok"
                            )
                        )
                    }, baseViewModel = homeBaseViewModel)
                }
            }
        }
    }
}

@Composable
fun AddUserPopup(
    onDismiss: () -> Unit,
    onUserAdd: (ChatListModel) -> Unit,
    baseViewModel: BaseViewModel
) {
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var isSearching by remember {
        mutableStateOf(false)
    }
    var userFound by remember {
        mutableStateOf<ChatListModel?>(null)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Enter Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
        Row {
            Button(
                onClick = {
                    isSearching = true
                    baseViewModel.searchUserByPhoneNumber(phoneNumber) { user ->
                        isSearching = false
                        if (user != null) {
                            userFound = user
                        } else {
                            userFound = null
                        }
                    }
                }, modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))
            ) {
                Text("Search")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onDismiss,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    colorResource(R.color.light_green)
                )
            ) {
                Text("Cancel")
            }
        }
        if (isSearching) {
            Text("Searching....", color = Color.Gray)
        }
        userFound?.let {
            Column {
                Text("User Found ${it.name}")
                Button(
                    onClick = {
                        onUserAdd(it)
                        onDismiss()
                    }, colors = ButtonDefaults.buttonColors(
                        colorResource(R.color.light_green)
                    )
                ) {
                    Text("Add to Chat")
                }
            }
        } ?: run {
            if (!isSearching) {
                Text("NO User found with this phone Number", color = Color.Gray)
            }
        }
    }
}