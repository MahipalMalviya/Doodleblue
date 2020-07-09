package com.mahipal.doodleblue.mvvm.model

import android.content.Context
import com.mahipal.doodleblue.R
import java.io.Serializable

class FoodCartDetails: Serializable{

    var foodList: ArrayList<FoodDetails>? = null
    var cartTotalPrice: Int = 0
    var cartTotalQuantity: Int = 0

    fun getFoodDetails(context: Context?): ArrayList<FoodDetails> {
        val foodList = ArrayList<FoodDetails>()

        val foodDetails1 = FoodDetails()
        foodDetails1.foodName = context?.resources?.getString(R.string.paneer_butter_masala)
        foodDetails1.foodPrice = context?.resources?.getString(R.string.paneer_butter_masala_price)?.toInt()?:0
        foodList.add(foodDetails1)

        val foodDetails2 = FoodDetails()
        foodDetails2.foodName = context?.resources?.getString(R.string.paneer_tikka)
        foodDetails2.foodPrice = context?.resources?.getString(R.string.paneer_tikka_price)?.toInt()?:0
        foodList.add(foodDetails2)

        val foodDetails3 = FoodDetails()
        foodDetails3.foodName = context?.resources?.getString(R.string.paneer_handi)
        foodDetails3.foodPrice = context?.resources?.getString(R.string.paneer_handi_price)?.toInt()?:0
        foodList.add(foodDetails3)

        return foodList
    }
}