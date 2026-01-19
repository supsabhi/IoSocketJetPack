package com.example.websocket

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.websocket.ViewModel.ParkingViewModel

@Composable
fun ParkingScreen(viewModel: ParkingViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val msg = viewModel.latestMessage

    LaunchedEffect(Unit) {
        viewModel.start()
    }

    if (msg == null) {
        Text("⌛ Waiting for WebSocket message...")
    } else {
        Column {
            Spacer(modifier = Modifier.height(60.dp))
            Text("Var1: ${msg.var1}")
            Spacer(modifier = Modifier.height(30.dp))

            Text("Var2: ${msg.var2}")
            Spacer(modifier = Modifier.height(30.dp))

            Text("Var3: ${msg.var3}")
        }
    }
}
