package kg.geekteck.shoppingapp.domain.repository

import androidx.lifecycle.LiveData
import kg.geekteck.shoppingapp.domain.entity.ShopItem

interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun deleteShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItem(shopItemId: Int): ShopItem

    // refactor
    fun getShopList(): LiveData<List<ShopItem>>
}