package kg.geekteck.shoppingapp.presentation.secondactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kg.geekteck.shoppingapp.R
import kg.geekteck.shoppingapp.databinding.ToDoBinding
import kg.geekteck.shoppingapp.databinding.ToDoneBinding
import kg.geekteck.shoppingapp.domain.entity.ShopItem

class Adapter(private var list: List<ShopItem>)  : RecyclerView.Adapter<Adapter.ItemHolder>(){

    inner class ItemHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(shopItem: ShopItem) {
            binding.root.findViewById<TextView>(R.id.tv_name).text = shopItem.name
            binding.root.findViewById<TextView>(R.id.tv_count).text = shopItem.count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = when (viewType) {
            ENABLED -> ToDoneBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
            NOT_ENABLED -> ToDoBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
            else -> throw IllegalArgumentException("Invalid view type")
        }
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return when (list[position].enable) {
            true -> ENABLED
            else -> NOT_ENABLED
        }
    }

    companion object {
        private const val NOT_ENABLED = 0
        private const val ENABLED = 1
    }
}