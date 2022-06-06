package kg.geekteck.shoppingapp.data

import android.util.Log
import kg.geekteck.shoppingapp.domain.entity.ShopItem
import kg.geekteck.shoppingapp.domain.repository.ShopListRepository

private const val TAG = "MainActivity"

class ShopListRepositoryImpl: ShopListRepository {
    private val shopList = mutableListOf<ShopItem>()

    override fun addShopItem(shopItem: ShopItem) {
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        val initialListSize = shopList.size
        for (item in shopList){
            if (item.name == shopItem.name){
                removeIt(item)
                break
            }
        }
        if (initialListSize == shopList.size) {
            show("There's no a shopItem to delete, like this one." +
                    " Please check it, and try again")
        }
    }

    private fun removeIt(item: ShopItem) {
        shopList.remove(item)
        updateItemsId(item)
    }

    private fun updateItemsId(item: ShopItem) {
        for ((index, element) in shopList.withIndex()){
            val (name, count, enable) = element
            shopList[index] = ShopItem(name, count, enable, index)
        }
        item.syncId(shopList.size)
    }

    override fun editShopItem(shopItem: ShopItem) {
        var isItemExists = false
        for ((index, element) in shopList.withIndex()){
            if (element.name == shopItem.name) {
                isItemExists = true
                val (name, count, enable, id) = element
                shopList[index] = ShopItem(name, count, !enable, id = id)
            }
        }
        if (!isItemExists){
            show("There's no shopItem like this one. Please check it, and try again")
        } else {
            shopItem.syncId(shopList.size)
        }
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        val shopItem = ShopItem("Item is not found!!!", 0, false, 0)
        for (item in shopList){
            if (item.id == shopItemId){
                item.syncId(shopList.size)
                return item
            }
        }
        return shopItem
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }

    private fun show(s : String){
        Log.d(TAG, s)
        println("******<<<********** $s ********>>>********")
    }
}