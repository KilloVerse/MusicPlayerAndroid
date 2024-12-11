package com.example.try10.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.try10.MyExoplayer
import com.example.try10.PlayerActivity
import com.example.try10.SongsListActivity
import com.example.try10.databinding.SongListItemRecyclerRowBinding
import com.example.try10.models.SongModel
import com.google.firebase.firestore.FirebaseFirestore

class SongsListAdapter(private val songIdList:List<String>):
    RecyclerView.Adapter<SongsListAdapter.MyViewHolder>() {
    class MyViewHolder(private val binding:SongListItemRecyclerRowBinding):RecyclerView.ViewHolder(binding.root){
        fun bindingData(songId:String){
            FirebaseFirestore.getInstance().collection("songs")
                .document(songId).get()
                .addOnSuccessListener {
                    val song= it.toObject(SongModel::class.java)
                    song?.apply {
                        binding.songTitleTextView.text=title
                        binding.songSubtitleTextView.text=subtitle
                        Glide.with(binding.songCoverImageView).load(coverUrl).into(binding.songCoverImageView)
                        binding.root.setOnClickListener{
                            MyExoplayer.startPlaying(binding.root.context,song)
                            it.context.startActivity(Intent(it.context,PlayerActivity::class.java))
                        }
                    }
                }

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