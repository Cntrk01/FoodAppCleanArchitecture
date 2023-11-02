package com.cantrk.foodappcleanarchitecture.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cantrk.foodappcleanarchitecture.databinding.RandomMealItemBinding
import com.cantrk.foodappcleanarchitecture.dataclass.RandomMeal

class RandomMealAdapter : RecyclerView.Adapter<RandomMealAdapter.ViewHolder>() {

    private var randomList = listOf<RandomMeal>()

    var itemClickForDetail: ((String) -> Unit)? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setMealList(list : List<RandomMeal>){
        this.randomList= list
        notifyDataSetChanged()
    }

    class ViewHolder(val binding : RandomMealItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inf=RandomMealItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(inf)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPos=randomList[position]
        holder.binding.apply {
            Glide.with(randomMealImage).load(itemPos.strMealThumb).into(randomMealImage)

            randomMealCard.setOnClickListener {
                setItemClickListener?.itemId(itemPos.idMeal)
            }
        }

    }
    var setItemClickListener : SetItemClickListener ?=null
    interface SetItemClickListener{
        fun itemId(itemId:String)
    }

    fun itemClickListener(listener: SetItemClickListener){
        this.setItemClickListener=listener
    }

    override fun getItemCount(): Int {
        return randomList.size
    }
}