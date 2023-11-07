package com.cantrk.foodappcleanarchitecture.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cantrk.foodappcleanarchitecture.databinding.FavoriteItemRowBinding
import com.cantrk.foodappcleanarchitecture.dataclass.FoodSaveEntity

class FavoriteListAdapter : RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {

    private var favoriteList= emptyList<FoodSaveEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setFavoriteList(list:List<FoodSaveEntity>){
        this.favoriteList=list
        notifyDataSetChanged()
    }

    class ViewHolder(val binding:FavoriteItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inf=FavoriteItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(inf)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPosition=favoriteList[position]
        holder.binding.apply {
            Glide.with(mealImage).load(itemPosition.image).into(mealImage)
            mealName.text=itemPosition.title
            mealCategory.text=itemPosition.category
            mealLocation.text=itemPosition.location
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }
}