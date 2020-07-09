package com.mahipal.doodleblue.mvvm.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahipal.doodleblue.FoodItemAction
import com.mahipal.doodleblue.R
import com.mahipal.doodleblue.mvvm.model.FoodCartDetails
import com.mahipal.doodleblue.mvvm.viewModel.FoodViewModel
import kotlinx.android.synthetic.main.activity_restaurant.*

class RestaurantFragment: Fragment() {

    private var foodDetailsAdapter: FoodDetailsAdapter? = null
    private var foodViewModel: FoodViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_restaurant,null,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodViewModel = (activity as MainActivity).foodViewModel
        val navController = activity?.let { Navigation.findNavController(it,R.id.nav_host_frag) }

        foodViewModel?.foodCartDetails?.observe(activity as MainActivity, Observer { foodCartDetails ->
            setDataToView(foodCartDetails)
        })

        constraint_view_cart.setOnClickListener {
            navController?.navigate(R.id.action_restaurant_to_foodcart_fragment)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDataToView(foodCartDetails: FoodCartDetails?) {
        foodCartDetails?.foodList?.let {

            rv_food_details_list.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)

            foodDetailsAdapter = FoodDetailsAdapter(it) { action, position ->

                if (action.equals(FoodItemAction.ADD.name,true)) {
                    foodViewModel?.addQuantity(position)
                } else {
                    foodViewModel?.minusQuantity(position)
                }
            }
            rv_food_details_list.adapter = foodDetailsAdapter
        }

        if (foodCartDetails?.cartTotalQuantity?:0 > 0) {
            constraint_view_cart.visibility = View.VISIBLE
            tv_item_count.text = "(${foodCartDetails?.cartTotalQuantity} ITEMS)"
        } else {
            constraint_view_cart.visibility = View.GONE
            tv_item_count.text = ""
        }
    }

}