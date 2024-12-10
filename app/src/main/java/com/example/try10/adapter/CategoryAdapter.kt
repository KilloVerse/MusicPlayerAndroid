package com.example.try10.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.try10.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.example.try10.SongsListActivity
import com.example.try10.databinding.CategoryItemRecyclerRowBinding
import com.example.try10.models.CategoryModel

class CategoryAdapter(private val categoryList:List<CategoryModel>):
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    class  MyViewHolder(private val binding: CategoryItemRecyclerRowBinding):
        RecyclerView.ViewHolder(binding.root){
        fun binData(category:CategoryModel){
            binding.nameTextView.text=category.name
            Glide.with(binding.coverImageView).load(category.coverUrl).into(binding.coverImageView)
            Log.i("SONGS",category.songs.size.toString())
            val context=binding.root.context
            binding.root.setOnClickListener{
                SongsListActivity.category=category
                context.startActivity(Intent(context,SongsListActivity::class.java))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CategoryItemRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binData(categoryList[position])
    }
}