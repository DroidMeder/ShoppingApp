package kg.geekteck.shoppingapp.presentation.mainFragment

import androidx.lifecycle.ViewModel
import kg.geekteck.shoppingapp.data.ShopListRepositoryImpl
import kg.geekteck.shoppingapp.domain.entity.ShopItem
import kg.geekteck.shoppingapp.domain.usecases.AddShopItemUseCase
import kg.geekteck.shoppingapp.domain.usecases.DeleteShopItemUseCase
import kg.geekteck.shoppingapp.domain.usecases.EditShopItemUseCase
import kg.geekteck.shoppingapp.domain.usecases.GetShopListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainFragmentViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl()

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.Default)

    fun getShopList() = getShopListUseCase.getShopList()

    fun addShopItem(shopItem: ShopItem){
        scope.launch {
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun editShopItem(shopItem: ShopItem){
        scope.launch {
            val newItem = shopItem.copy(enable = !shopItem.enable)
            editShopItemUseCase.editShopItem(newItem)
        }
    }

    fun editShopItemCompletely(shopItem: ShopItem){
        scope.launch {
            val newShopItem = ShopItem(
                shopItem.name,
                shopItem.count,
                shopItem.enable,
                shopItem.id
            )
            editShopItemUseCase.editShopItem(newShopItem)
        }
    }

    fun deleteShopItem(shopItem: ShopItem) = scope.launch {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}