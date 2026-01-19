package valtrans.parkonic.websocket


import android.os.Handler
import android.os.Looper
import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import valtrans.parkonic.websocket.data.model.ParkingMessage

class SocketManager {
    private var socket: Socket? = null
var var1: String=""
var var2: String=""
var var3: String=""
    fun connect(onMessage: (ParkingMessage) -> Unit) {
        try {
            val opts = IO.Options().apply {
                forceNew = true
            }

            socket = IO.socket("https://your-socket-server.com", opts)

            socket?.on(Socket.EVENT_CONNECT) {
                Log.d("sups","Socket.IO Connected")
            }
            // Listen for custom event (replace with your event name)
            socket?.on("digital_signage_5_1") { args ->
                if (args.isNotEmpty() && args[0] is JSONObject) {
                    val json = args[0] as JSONObject
                     var1 = json.optString("var1")
                     var2 = json.optString("var2")
                     var3 = json.optString("var3")

                    Log.d("sups","Got message: var1=$var1 var2=$var2 var3=$var3")
                    Handler(Looper.getMainLooper()).post {
                        onMessage(ParkingMessage(var1, var2, var3))
                    }
                }
            }
            socket?.on(Socket.EVENT_CONNECT_ERROR) { args ->
                Log.e("SocketIO", "Connect Error: ${args.joinToString()}")
            }

            socket?.on(Socket.EVENT_DISCONNECT) { args ->
                Log.d("SocketIO", "Disconnected: ${args.joinToString()}")
            }
            socket?.connect()


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun disconnect() {
        socket?.disconnect()
        socket?.off()
    }
}
