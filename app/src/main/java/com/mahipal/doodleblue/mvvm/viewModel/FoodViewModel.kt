package com.mahipal.doodleblue.mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahipal.doodleblue.mvvm.model.FoodCartDetails

class FoodViewModel: ViewModel() {

    var foodCartDetails = MutableLiveData<FoodCartDetails>()

    fun attachFoodCartDetails(foodCartDetails: FoodCartDetails) {
        this.foodCartDetails.postValue(foodCartDetails)
    }

    fun addQuantity(position: Int) {
        var cartTotalPrice = 0
        var cartTotalQuantity = 0

        val foodCartDetails = foodCartDetails.value
        foodCartDetails?.foodList?.let { foodList ->

            if (foodList.size > position) {
                if (foodList[position].foodQuantity == 20) {
                    return@let
                } else {
                    foodList[position].foodQuantity = foodList[position].foodQuantity + 1
                    foodList[position].foodTotalPrice = foodList[position].foodPrice.times(foodList[position].foodQuantity)
                }
            }

            foodList.forEach {
                cartTotalPrice += it.foodTotalPrice
                cartTotalQuantity += it.foodQuantity
            }
        }

        foodCartDetails?.cartTotalPrice = cartTotalPrice
        foodCartDetails?.cartTotalQuantity = cartTotalQuantity

        this.foodCartDetails.postValue(foodCartDetails)
    }

    fun minusQuantity(position: Int) {
        var cartTotalPrice = 0
        var cartTotalQuantity = 0

        val foodCartDetails = foodCartDetails.value
        foodCartDetails?.foodList?.let { foodList ->

            if (foodList.size > position) {
                if (foodList[position].foodQuantity == 0) {
                    return@let
                } else {
                    foodList[position].foodQuantity = foodList[position].foodQuantity - 1
                    foodList[position].foodTotalPrice = foodList[position].foodPrice.times(foodList[position].foodQuantity)
                }
            }

            foodList.forEach {
                cartTotalPrice += it.foodTotalPrice
                cartTotalQuantity += it.foodQuantity
            }
        }

        foodCartDetails?.cartTotalPrice = cartTotalPrice
        foodCartDetails?.cartTotalQuantity = cartTotalQuantity

        this.foodCartDetails.postValue(foodCartDetails)
    }
}