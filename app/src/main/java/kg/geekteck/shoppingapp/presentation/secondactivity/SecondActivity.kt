package kg.geekteck.shoppingapp.presentation.secondactivity

import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geekteck.shoppingapp.R
import kg.geekteck.shoppingapp.databinding.ActivitySecondBinding

@AndroidEntryPoint
class SecondActivity : AppCompatActivity(R.layout.activity_second) {
    private val binding: ActivitySecondBinding by viewBinding() }