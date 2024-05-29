package id.AimarWork.ModelDataClass

data class Item_from_Category(val name : String, val image : String,
                              val price : Float, val format : String, val weight_per_piece : Int){
    constructor() : this("", "", 0f, "", 0)
}

enum class Format_Quantity{
    Kilo,
    Liter,
    Piece
}