package id.AimarWork.FileDataTemporary

import id.AimarWork.ModelDataClass.Format_Quantity
import id.AimarWork.ModelDataClass.Item_from_Category
import id.my.miruza.locavest.R

val vegetables = arrayListOf<Item_from_Category>(
    Item_from_Category("Boston Lettuce", R.drawable.veggie_example_01,
        20000f, Format_Quantity.Kilo, 68, ),
    Item_from_Category("Purple Cauliflower", R.drawable.veggie_example_02,
        30000f, Format_Quantity.Kilo, 70),
    Item_from_Category("Savoy Cabbage", R.drawable.veggie_example_03
        , 45000f, Format_Quantity.Kilo, 75)
)