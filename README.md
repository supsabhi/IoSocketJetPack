
# 📡 Real-Time Parking Status App (Jetpack Compose + Socket.IO)

A modern **Android application built with Jetpack Compose** that demonstrates **real-time data synchronization** using **Socket.IO**.
The app instantly updates parking occupancy or status variables whenever the server data changes.

---

## ✨ Features

* **Live Updates**
  Real-time communication via WebSockets using the `socket.io-client` library.

* **Reactive UI**
  Built entirely with **Jetpack Compose**, ensuring seamless state-to-UI updates.

* **Lifecycle Aware**
  Uses `ViewModel` and `LaunchedEffect` to manage socket connections safely and efficiently.

---

## 🛠️ Tech Stack

* **UI:** Jetpack Compose
* **Architecture:** MVVM (Model–View–ViewModel)
* **Real-Time Communication:** Socket.IO Client (Java)
* **Serialization:** Kotlinx Serialization
* **Async Handling:** Kotlin Coroutines & Main Thread Handlers

---

## 🚀 Getting Started

### 1️⃣ Prerequisites

* **Android Studio:** Ladybug (or newer)
* **Minimum SDK:** 24+

---

### 2️⃣ Installation

Add the required dependencies to your **`build.gradle.kts`** file:

```kotlin
dependencies {
    implementation("io.socket:socket.io-client:2.1.0")
    implementation("org.json:json:20231013")
}
```

---

### 3️⃣ Usage

#### 🔧 Configure Server URL

Update the Socket.IO server URL in `SocketManager.kt`:

```kotlin
socket = IO.socket("https://your-socket-server.com", opts)
```

---

#### 📡 Server Event Format

The app listens for the event **`digital_signage_5_1`**.
Ensure your server emits a JSON object with the following structure:

```json
{
  "var1": "Level 1: 50 Spaces",
  "var2": "Level 2: FULL",
  "var3": "Status: Open"
}
```

---

## 📂 Project Structure

```text
├── ParkingScreen.kt
│   └── UI layer that reacts to real-time message changes
│
├── ParkingViewModel.kt
│   └── Manages socket connection lifecycle and state
│
├── SocketManager.kt
│   └── Handles WebSocket connection and JSON parsing
│
├── ParkingMessage.kt
│   └── Data class representing parking status
```

---

## 📝 Example Code Snippet

The app uses a `SocketManager` to bridge the network layer and UI:

```kotlin
// Listening for data and posting to Main Thread
socket?.on("digital_signage_5_1") { args ->
    val json = args[0] as JSONObject
    val message = ParkingMessage(
        var1 = json.optString("var1"),
        var2 = json.optString("var2"),
        var3 = json.optString("var3")
    )
    Handler(Looper.getMainLooper()).post {
        onMessage(message)
    }
}
```

---

## 🤝 Contributing

Contributions are welcome! 🎉
Feel free to:

* Open an issue for bugs or suggestions
* Submit a pull request to improve the signage logic or UI

---

## 📄 License

This project is open-source and available under the **MIT License**.

