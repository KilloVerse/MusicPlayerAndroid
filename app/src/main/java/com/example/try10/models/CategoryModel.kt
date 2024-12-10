package com.example.try10.models

data class CategoryModel(
    val name:String,
    val coverUrl:String,
){
    constructor():this("","")
}
