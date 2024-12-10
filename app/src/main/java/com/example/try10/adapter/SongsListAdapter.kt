package com.example.try10.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.try10.databinding.SongListItemRecyclerRowBinding

class SongsListAdapter(private val songIdList:List<String>):
    RecyclerView.Adapter<SongsListAdapter.MyViewHolder>() {
    class MyViewHolder(private val binding:SongListItemRecyclerRowBinding):RecyclerView.ViewHolder(binding.root){
        fun bindingData(songId:String){
            binding.songTitleTextView.text=songId

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=SongListItemRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return songIdList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindingData(songIdList[position])
    }
}