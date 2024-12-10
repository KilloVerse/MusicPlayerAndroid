package com.example.try10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.try10.adapter.SongsListAdapter
import com.example.try10.databinding.ActivitySongsListBinding
import com.example.try10.models.CategoryModel

class SongsListActivity : AppCompatActivity() {
    companion object{
        lateinit var category:CategoryModel
        lateinit var songsListAdapter: SongsListAdapter
    }
    lateinit var  binding:ActivitySongsListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySongsListBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        binding.nameTextView.text= category.name
        Glide.with(binding.coverImageView).load(category.coverUrl).into(binding.coverImageView)
        setupSongsListRecyclerView()
    }
    fun setupSongsListRecyclerView(){
        songsListAdapter= SongsListAdapter(category.songs)
        binding.songsListRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.songsListRecyclerView.adapter= songsListAdapter
    }
}