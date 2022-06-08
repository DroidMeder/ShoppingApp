package kg.geekteck.shoppingapp.domain.entity

data class ShopItem(
    val name: String,
    var count: Int, // = COUNT_OF_ITEM,
    var enable: Boolean, // = ENABLE,
    var id : Int = UNDEFINED_ID
){
    companion object {
        const val UNDEFINED_ID = 0
    }
}
