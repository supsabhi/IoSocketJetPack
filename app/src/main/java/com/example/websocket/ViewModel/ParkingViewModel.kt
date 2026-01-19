package com.example.websocket.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.websocket.SocketManager
import com.example.websocket.data.model.ParkingMessage


class ParkingViewModel : ViewModel() {
    var latestMessage by mutableStateOf<ParkingMessage?>(null)
        private set

    private val socketManager = SocketManager()
    var var1: String=""
    var var2: String=""
    var var3: String=""
    fun start() {
        socketManager.connect(){
                message ->
            latestMessage = message
        }

    }

    override fun onCleared() {
        super.onCleared()
        socketManager.disconnect()
    }
}
