package kg.geekteck.shoppingapp.presentation.editfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geekteck.shoppingapp.R
import kg.geekteck.shoppingapp.databinding.FragmentEditBinding
import kg.geekteck.shoppingapp.domain.entity.ShopItem
import kotlin.random.Random

@AndroidEntryPoint
class EditFragment : Fragment(R.layout.fragment_edit) {
    private val binding: FragmentEditBinding by viewBinding(CreateMethod.INFLATE)
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navHostFragment = requireActivity()
            .supportFragmentManager
            .findFragmentById(R.id.nav_host)
                as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendData()
    }

    private fun sendData() {
        binding.apply {
            btnSave.setOnClickListener {
                if (etName.text.isNullOrEmpty() || etCount.text.isNullOrEmpty()) {
                    etName.error = "The empty field"
                } else {
                    etName.error = null
                    val name = etName.text.toString()
                    val count = etCount.text.toString()
                    val bundle = Bundle()
                    val item = ShopItem(name, count.toInt(), Random.nextBoolean())
                    bundle.putSerializable("item", item)
                    parentFragmentManager.setFragmentResult("rk_news", bundle)
                    navController.navigateUp()
                }
            }
        }
    }
}