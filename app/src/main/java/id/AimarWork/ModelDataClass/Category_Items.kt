package id.AimarWork.ModelDataClass

import id.AimarWork.EnumPackageAimar.Category_Item_enum

data class Category_Items(val name : String, val image: Int, val category : Category_Item_enum,
                          val totalItem : Int)