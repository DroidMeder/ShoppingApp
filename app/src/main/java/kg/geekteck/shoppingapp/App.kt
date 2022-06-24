package kg.geekteck.shoppingapp

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import kg.geekteck.shoppingapp.data.local.AppDatabase

@HiltAndroidApp
class App : Application() {
}