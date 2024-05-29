package id.my.miruza.locavest.data

data class CategoryItem(
    val name: String,
    var image: String?,
    val totalItem: Int,
){
    constructor() : this ("", "", 0)
}
