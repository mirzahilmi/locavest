package id.my.miruza.locavest.data

data class ProductItem(
    val id : Int,
    val name: String,
    val image: String,
    val price: Float,
    val format: String,
    val weightPerPiece: Int
){
    constructor():this
        (1000,"","", 0f, "", 0)
}
