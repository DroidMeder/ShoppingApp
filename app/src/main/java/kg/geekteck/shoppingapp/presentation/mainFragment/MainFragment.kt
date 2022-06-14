package kg.geekteck.shoppingapp.presentation.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geekteck.shoppingapp.R
import kg.geekteck.shoppingapp.databinding.FragmentMainBinding
import kg.geekteck.shoppingapp.domain.entity.ShopItem

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(CreateMethod.INFLATE)
    private val viewModel: MainFragmentViewModel by viewModels()
    private val adapter = Adapter()
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navHostFragment = requireActivity()
            .supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onStart() {
        super.onStart()
        parentFragmentManager.setFragmentResultListener("rk_news", viewLifecycleOwner
        ) { _: String?, result: Bundle ->
            val n: ShopItem? = result.getSerializable("item") as ShopItem?
            if (n != null) {
                viewModel.addShopItem(n)
                initRecycler()
            }
        }

        parentFragmentManager.setFragmentResultListener("item_edit", viewLifecycleOwner
        ) { _: String?, result: Bundle ->
            val n: ShopItem? = result.getSerializable("item") as ShopItem?
            if (n != null) {
                viewModel.editShopItemCompletely(n)
                initRecycler()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initClicks()
    }

    private fun initClicks() {

        binding.fab.setOnClickListener {
            navController.navigate(MainFragmentDirections.actionMainFragmentToEditFragment())
        }

        adapter.onItemClick={
            navController.navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it))
        }
        adapter.onItemLongClick={
            viewModel.editShopItem(it)
            initRecycler()
        }
    }

    private fun initRecycler() {
        viewModel.getShopList().observe(requireActivity()) {
            adapter.list = it
            println(it.toString())
        }
        binding.rec.adapter = adapter

        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.list[viewHolder.absoluteAdapterPosition]
                viewModel.deleteShopItem(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rec)
    }
}