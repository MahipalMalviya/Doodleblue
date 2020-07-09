package com.mahipal.doodleblue.mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahipal.doodleblue.FoodItemAction
import com.mahipal.doodleblue.R
import com.mahipal.doodleblue.mvvm.model.FoodCartDetails
import com.mahipal.doodleblue.mvvm.model.FoodDetails
import com.mahipal.doodleblue.mvvm.viewModel.FoodViewModel
import kotlinx.android.synthetic.main.activity_food_cart.*

class FoodCartFragment: Fragment() {

    private var foodViewModel: FoodViewModel? = null
    private var foodCartAdapter: FoodCartAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_food_cart,null,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodViewModel = (activity as MainActivity).foodViewModel

        foodViewModel?.foodCartDetails?.observe(this, Observer { foodCartDetails ->
            setDataToView(foodCartDetails)
        })
    }

    private fun setDataToView(foodCartDetails: FoodCartDetails?) {
        foodCartDetails?.foodList?.let {
            val foodList = it.filter { foodDetails ->
                foodDetails.foodQuantity > 0
            } as ArrayList<FoodDetails>

            rv_food_cart_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

            foodCartAdapter = FoodCartAdapter(foodList) { action, position ->

                if (action.equals(FoodItemAction.ADD.name,true)) {
                    foodViewModel?.addQuantity(position)
                } else {
                    foodViewModel?.minusQuantity(position)
                }
            }
            rv_food_cart_list.adapter = foodCartAdapter
        }

        if (foodCartDetails?.cartTotalPrice?:0 > 0) {
            tv_total_price.text = "${foodCartDetails?.cartTotalPrice}"
        } else {
            tv_total_price.text = ""
        }
    }
}