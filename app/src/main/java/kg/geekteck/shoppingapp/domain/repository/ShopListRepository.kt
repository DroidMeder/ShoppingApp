package kg.geekteck.shoppingapp.domain.repository

import kg.geekteck.shoppingapp.domain.entity.ShopItem

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int): ShopItem

    // refactor
    fun getShopList(): List<ShopItem>

}