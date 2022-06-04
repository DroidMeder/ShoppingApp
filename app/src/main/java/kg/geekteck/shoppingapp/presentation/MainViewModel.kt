package kg.geekteck.shoppingapp.presentation

import androidx.lifecycle.ViewModel
import kg.geekteck.shoppingapp.data.ShopListRepositoryImpl
import kg.geekteck.shoppingapp.domain.entity.ShopItem
import kg.geekteck.shoppingapp.domain.usecases.*

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl()

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val getShopListUseCase = GetShopListUseCase(repository)

    fun addShopItem(shopItem: ShopItem){
        addShopItemUseCase.addShopItem(shopItem)
    }

    fun editShopItem(shopItem: ShopItem){
        editShopItemUseCase.editShopItem(shopItem)
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun getShopItem(shopItemId: String) = getShopItemUseCase.getShopItem(shopItemId.toInt())


    fun getShopList() = getShopListUseCase.getShopList()

}