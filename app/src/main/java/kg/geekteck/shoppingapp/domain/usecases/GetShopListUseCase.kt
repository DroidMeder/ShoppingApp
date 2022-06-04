package kg.geekteck.shoppingapp.domain.usecases

import kg.geekteck.shoppingapp.domain.repository.ShopListRepository

class GetShopListUseCase(private val repository: ShopListRepository) {
    fun getShopList() = repository.getShopList()
}