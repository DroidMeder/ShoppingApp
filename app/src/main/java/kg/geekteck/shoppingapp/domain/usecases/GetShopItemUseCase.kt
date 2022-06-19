package kg.geekteck.shoppingapp.domain.usecases

import kg.geekteck.shoppingapp.domain.repository.ShopListRepository

class GetShopItemUseCase(private val repository: ShopListRepository) {
    suspend fun getShopItem(shopItemId: Int) = repository.getShopItem(shopItemId)


}