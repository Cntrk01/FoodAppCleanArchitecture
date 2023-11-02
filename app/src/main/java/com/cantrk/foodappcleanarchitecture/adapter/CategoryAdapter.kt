package com.cantrk.foodappcleanarchitecture.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cantrk.foodappcleanarchitecture.databinding.CategoryItemRowBinding
import com.cantrk.foodappcleanarchitecture.dataclass.Category

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var randomList = listOf<Category>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMealList(list : List<Category>){
        this.randomList= list
        notifyDataSetChanged()
    }

    class ViewHolder(val binding : CategoryItemRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inf=CategoryItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(inf)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=randomList[position]

        holder.binding.apply {
            Glide.with(categoryImage).load(item.strCategoryThumb).into(categoryImage)
            categoryName.text=item.strCategory
        }
    }

    override fun getItemCount(): Int {
        return randomList.size
    }
}