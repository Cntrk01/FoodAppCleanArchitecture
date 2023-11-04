package com.cantrk.foodappcleanarchitecture.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cantrk.foodappcleanarchitecture.databinding.CategoryRow2Binding
import com.cantrk.foodappcleanarchitecture.dataclass.Meal

class CategoryMealListAdapter : RecyclerView.Adapter<CategoryMealListAdapter.ViewHolder>() {

    private var randomList = listOf<Meal>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMealList(list : List<Meal>){
        this.randomList= list
        notifyDataSetChanged()
    }

    class ViewHolder(val binding : CategoryRow2Binding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inf= CategoryRow2Binding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(inf)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=randomList[position]

        holder.binding.apply {
            Glide.with(categoryImage).load(item.strMealThumb).into(categoryImage)
            categoryName.text=item.strMeal
        }
    }

    override fun getItemCount(): Int {
        return randomList.size
    }
}