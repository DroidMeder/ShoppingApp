package kg.geekteck.shoppingapp.domain.usecases

import kg.geekteck.shoppingapp.domain.entity.ShopItem
import kg.geekteck.shoppingapp.domain.repository.ShopListRepository

class AddShopItemUseCase(private val repository: ShopListRepository) {
    fun addShopItem(shopItem: ShopItem){
        repository.addShopItem(shopItem)
    }
}