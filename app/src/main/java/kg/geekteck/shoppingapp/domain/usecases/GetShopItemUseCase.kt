package kg.geekteck.shoppingapp.domain.usecases

import kg.geekteck.shoppingapp.domain.entity.ShopItem
import kg.geekteck.shoppingapp.domain.repository.ShopListRepository

class GetShopItemUseCase(private val repository: ShopListRepository) {
    fun getShopItem(shopItemId: Int) = repository.getShopItem(shopItemId)


}