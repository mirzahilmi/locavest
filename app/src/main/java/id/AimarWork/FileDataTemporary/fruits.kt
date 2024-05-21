package id.AimarWork.FileDataTemporary

import id.AimarWork.ModelDataClass.Format_Quantity
import id.AimarWork.ModelDataClass.Item_from_Category
import id.my.miruza.locavest.R

val fruits = arrayListOf<Item_from_Category>(
    Item_from_Category("Fruittie", R.drawable.veggie_example_01
        , 12500f, Format_Quantity.Kilo, 100),
    Item_from_Category("Fr :>", R.drawable.veggie_example_02,
        45000f, Format_Quantity.Kilo, 120),
    Item_from_Category("Mau nangis", R.drawable.veggie_example_03,
        55000f, Format_Quantity.Kilo, 140)
)