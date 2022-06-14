package kg.geekteck.shoppingapp.domain.entity

import java.io.Serializable

data class ShopItem(
    val name: String,
    var count: Int, // = COUNT_OF_ITEM,
    var enable: Boolean, // = ENABLE,
    var id : Int = UNDEFINED_ID
) : Serializable{
    companion object {
        const val UNDEFINED_ID = 0
    }
}
