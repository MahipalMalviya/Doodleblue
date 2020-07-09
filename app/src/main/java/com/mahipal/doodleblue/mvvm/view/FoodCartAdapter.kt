package com.mahipal.doodleblue.mvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahipal.doodleblue.FoodItemAction
import com.mahipal.doodleblue.R
import com.mahipal.doodleblue.mvvm.model.FoodDetails
import kotlinx.android.synthetic.main.item_food_cart.view.*

class FoodCartAdapter(private val foodList: ArrayList<FoodDetails>,
                      private val onItemClick: ((String, Int) -> Unit)): RecyclerView.Adapter<FoodCartAdapter.FoodCartHolder>() {

    private var showMore = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCartHolder {
        return FoodCartHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_food_cart,parent,false))
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodCartHolder, position: Int) {
        if (foodList.size > 2 && position.plus(1) == 2 && !showMore) {
            holder.itemView.tv_show_more.visibility = View.VISIBLE
        } else {
            holder.itemView.tv_show_more.visibility = View.GONE
        }
        val foodDetails = foodList[position]
        holder.bindFoodItem(foodDetails)

        holder.itemView.tv_add_food.setOnClickListener {
            onItemClick.invoke(FoodItemAction.ADD.name,position)
        }

        holder.itemView.tv_minus_food.setOnClickListener {
            onItemClick.invoke(FoodItemAction.MINUS.name,position)
        }

        holder.itemView.tv_plus_food.setOnClickListener {
            onItemClick.invoke(FoodItemAction.ADD.name,position)
        }

        holder.itemView.tv_show_more.setOnClickListener {
            dataChanged()
        }
    }

    private fun dataChanged() {
        showMore = true
        notifyDataSetChanged()
    }

    inner class FoodCartHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

        private fun addFoodVisible(foodDetails: FoodDetails) {
            itemView.tv_minus_food.visibility = View.VISIBLE
            itemView.tv_food_quantity.visibility = View.VISIBLE
            itemView.tv_plus_food.visibility = View.VISIBLE
            itemView.tv_add_food.visibility = View.INVISIBLE

            itemView.tv_food_quantity.text = foodDetails.foodQuantity.toString()
        }

        fun bindFoodItem(foodDetails: FoodDetails) {

            itemView.constraint_food_item.visibility = View.VISIBLE

            if (adapterPosition.plus(1) <= 2 && !showMore) {

                itemView.tv_food_name.text = foodDetails.foodName
                itemView.tv_food_price.text = foodDetails.foodPrice.toString()
                addFoodVisible(foodDetails)
            } else if (showMore) {

                itemView.tv_food_name.text = foodDetails.foodName
                itemView.tv_food_price.text = foodDetails.foodPrice.toString()
                addFoodVisible(foodDetails)
            } else {
                itemView.constraint_food_item.visibility = View.INVISIBLE
            }



        }

    }
}