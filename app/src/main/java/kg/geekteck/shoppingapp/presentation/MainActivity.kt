package kg.geekteck.shoppingapp.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import kg.geekteck.shoppingapp.databinding.ActivityMainBinding
import kg.geekteck.shoppingapp.domain.entity.ShopItem

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listeners()
    }

    private fun listeners() = with(binding) {

        btnAdd.setOnClickListener {
            if (!etField.text.isNullOrEmpty()) {
                show(etField.text.toString())
                viewModel.addShopItem(ShopItem(etField.text.toString()))
                etField.setText("")
                etField.error = null
            }else {
                etField.error = "The empty field is not allowed!!!"
                show("The empty field is not allowed!!!")
            }
        }

        btnEdit.setOnClickListener {
            if (!etField.text.isNullOrEmpty()){
                viewModel.editShopItem(ShopItem(etField.text.toString()))
                etField.setText("")
                etField.error = null
            }else {
                etField.error = "The empty field is not allowed!!!"
                show("The empty field is not allowed!!!")
            }
        }

        btnDelete.setOnClickListener {
            if (!etField.text.isNullOrEmpty()){
                viewModel.deleteShopItem(ShopItem(etField.text.toString()))
                etField.setText("")
                etField.error = null
            }else {
                etField.error = "The empty field is not allowed!!!"
                show("The empty field is not allowed!!!")
            }
        }

        btnGetItem.setOnClickListener {
            if (!etField.text.isNullOrEmpty() && etField.text.isDigitsOnly()){
                show(viewModel.getShopItem(etField.text.toString()).toString())
                etField.setText("")
                etField.error = null
            } else {
                etField.error = "Only numbers are allowed!!!"
                show("Only numbers are allowed!!!")
            }
        }

        btn.setOnClickListener {
            show(viewModel.getShopList().toString())
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