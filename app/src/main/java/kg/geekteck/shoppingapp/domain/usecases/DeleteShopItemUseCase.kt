package kg.geekteck.shoppingapp.domain.usecases

import kg.geekteck.shoppingapp.domain.entity.ShopItem
import kg.geekteck.shoppingapp.domain.repository.ShopListRepository

class DeleteShopItemUseCase(private val repository: ShopListRepository) {
    suspend fun deleteShopItem(shopItem: ShopItem){
        repository.deleteShopItem(shopItem)
    }
}