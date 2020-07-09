package com.mahipal.doodleblue.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahipal.doodleblue.R
import com.mahipal.doodleblue.mvvm.model.FoodCartDetails
import com.mahipal.doodleblue.mvvm.viewModel.FoodViewModel

class MainActivity : AppCompatActivity() {

    lateinit var foodViewModel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        foodViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(FoodViewModel::class.java)

        val foodCartDetails = FoodCartDetails()
        foodCartDetails.foodList = foodCartDetails.getFoodDetails(this)

        foodViewModel.attachFoodCartDetails(foodCartDetails)

    }
}
