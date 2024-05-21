package id.AimarWork.ModelDataClass

data class Item_from_Category(val Name : String, val imageItem : Int,
                              val price : Float, val quantity_type : Format_Quantity, val weight_per_piece : Int)

enum class Format_Quantity{
    Kilo,
    Liter,
    Piece
}