package kg.geekteck.shoppingapp.domain.usecases

import kg.geekteck.shoppingapp.domain.entity.ShopItem
import kg.geekteck.shoppingapp.domain.repository.ShopListRepository

class EditShopItemUseCase(private val repository: ShopListRepository) {
    suspend fun editShopItem(shopItem: ShopItem){
        repository.editShopItem(shopItem)
    }
}