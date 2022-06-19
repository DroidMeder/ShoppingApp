package kg.geekteck.shoppingapp.data

import androidx.lifecycle.MutableLiveData
import kg.geekteck.shoppingapp.domain.entity.ShopItem
import kg.geekteck.shoppingapp.domain.repository.ShopListRepository

class ShopListRepositoryImpl: ShopListRepository {
    private var autoIncrement = 0
    private var shopListLD = MutableLiveData<List<ShopItem>>()

    private val shopList = sortedSetOf<ShopItem>({o1, o2 ->
        o1.id.compareTo(o2.id)
    })

    override suspend fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrement++
        }
        shopList.add(shopItem)
        updateList()
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        deleteShopItem(oldElement)
        addShopItem(shopItem)
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element has not found $shopItemId")
    }

    override fun getShopList() = shopListLD

    private fun updateList(){
        shopListLD.postValue(shopList.toList())
    }
}