package id.AimarWork.ModelDataClass

import id.AimarWork.EnumPackageAimar.Category_Item_enum

data class Category_Items(
    val name: String, var image: String? ,val totalItem: Int)
{
    constructor() : this("", "", 0)
}
