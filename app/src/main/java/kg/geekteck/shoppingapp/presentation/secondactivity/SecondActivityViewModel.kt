package kg.geekteck.shoppingapp.presentation.secondactivity

import androidx.lifecycle.ViewModel
import kg.geekteck.shoppingapp.data.ShopListRepositoryImpl
import kg.geekteck.shoppingapp.domain.usecases.GetShopListUseCase

class SecondActivityViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl()

    private val getShopListUseCase = GetShopListUseCase(repository)

    fun getShopList() = getShopListUseCase.getShopList()
}