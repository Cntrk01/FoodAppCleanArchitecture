package com.cantrk.foodappcleanarchitecture.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cantrk.foodappcleanarchitecture.databinding.RandomMealItemBinding
import com.cantrk.foodappcleanarchitecture.dataclass.RandomMeal

class PopularMealAdapter : RecyclerView.Adapter<PopularMealAdapter.ViewHolder>() {

    private var popularMealList = listOf<RandomMeal>()

    @SuppressLint("NotifyDataSetChanged")
    fun setPopularMealList(list : List<RandomMeal>){
        this.popularMealList= list
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: RandomMealItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inf=RandomMealItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(inf)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPosition=popularMealList[position]

        holder.binding.apply {
            Glide.with(randomMealImage)
                .load(itemPosition.strMealThumb)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(randomMealImage)
        }
    }

    override fun getItemCount(): Int {
        return popularMealList.size
    }
}