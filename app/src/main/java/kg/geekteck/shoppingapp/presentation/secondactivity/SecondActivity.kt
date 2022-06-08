package kg.geekteck.shoppingapp.presentation.secondactivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geekteck.shoppingapp.R
import kg.geekteck.shoppingapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity(R.layout.activity_second) {
    private val binding: ActivitySecondBinding by viewBinding()
    private val viewModel: SecondActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rec.layoutManager = LinearLayoutManager(this)
        binding.rec.adapter = Adapter(viewModel.getShopList())
    }
}