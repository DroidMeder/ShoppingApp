package kg.geekteck.shoppingapp.domain.entity

data class ShopItem(
    val name: String,
    var count: Int = COUNT_OF_ITEM,
    var enable: Boolean = ENABLE,
    val id : Int = UNDEFINED_ID
){
    companion object {
        var UNDEFINED_ID = 0
        const val COUNT_OF_ITEM = 12
        const val ENABLE = false
    }



    fun syncId(int: Int){
        UNDEFINED_ID = int
    }

    init {
        UNDEFINED_ID++
    }
}
