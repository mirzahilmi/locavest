package id.my.miruza.locavest.data

data class ProductItem(
    val name: String,
    val image: String,
    val price: Float,
    val format: String,
    val weightPerPiece: Int
){
    constructor():this
        ("","", 0f, "", 0)
}
