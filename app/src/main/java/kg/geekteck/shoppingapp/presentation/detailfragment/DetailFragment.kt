package kg.geekteck.shoppingapp.presentation.detailfragment

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
import kg.geekteck.shoppingapp.databinding.FragmentDetailBinding
import kg.geekteck.shoppingapp.domain.entity.ShopItem

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding: FragmentDetailBinding by viewBinding(CreateMethod.INFLATE)
    private lateinit var args: DetailFragmentArgs

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = DetailFragmentArgs.fromBundle(requireArguments())
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

        setData()
    }

    private fun setData() {
        if (args.item != null){
            binding.apply {
                etName.setText(args.item!!.name)
                etCount.setText(args.item!!.count.toString())
                checkBox.isChecked = args.item!!.enable
            }
            sendData(args.item!!.id)
        }
    }

    private fun sendData(id: Int) {
        binding.apply {
            btnSave.setOnClickListener {
                if (etName.text.isNullOrEmpty() || etCount.text.isNullOrEmpty()) {
                    etName.error = "The empty field"
                } else {
                    etName.error = null
                    val name = etName.text.toString()
                    val count = etCount.text.toString()
                    val enabled = checkBox.isChecked
                    val bundle = Bundle()
                    val item = ShopItem(name, count.toInt(), enabled, id)
                    bundle.putSerializable("item", item)
                    parentFragmentManager.setFragmentResult("item_edit", bundle)
                    navController.navigateUp()
                }
            }
        }
    }
}