package kg.geekteck.shoppingapp.presentation.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geekteck.shoppingapp.R
import kg.geekteck.shoppingapp.databinding.ActivityMainBinding
import kg.geekteck.shoppingapp.domain.entity.ShopItem

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            btnAdd.setOnClickListener {
                viewModel.addShopItem(
                    ShopItem(
                        "potato",
                        2,
                        false
                    )
                )
            }

            btnDelete.setOnClickListener {
                viewModel.deleteShopItem(
                    ShopItem(
                        "potato",
                        2,
                        false
                    )
                )
            }

            btnEdit.setOnClickListener {
                viewModel.editShopItem(
                    ShopItem("tomato",
                        14,
                        false,
                        14
                    )
                )
            }

            btn.setOnClickListener {
                show("initListeners: ${viewModel.getShopList()}")
            }
        }
    }

    private fun show(s : String){
        Toast.makeText(
            this@MainActivity,
            s,
            Toast.LENGTH_SHORT
        ).show()
        Log.d(TAG, s)
        println("**************** $s ****************")
    }
}