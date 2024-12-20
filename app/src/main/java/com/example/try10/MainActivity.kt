package com.example.try10

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.try10.adapter.CategoryAdapter
import com.example.try10.adapter.SectionSongListAdapter
import com.example.try10.databinding.ActivityMainBinding
import com.example.try10.models.CategoryModel
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var categoryAdapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        getCategories()
        setupSection("secstion_1",binding.section1MainLayout,binding.secsion1Title,binding.section1RecyclerView)
        setupSection("secstion_2",binding.section2MainLayout,binding.secsion2Title,binding.section2RecyclerView)
        setupSection("secstion_3",binding.section3MainLayout,binding.secsion3Title,binding.section3RecyclerView)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        showPlayerView()
    }
    fun showPlayerView(){
        binding.playerView.setOnClickListener(){
            startActivity(Intent(this,PlayerActivity::class.java))
        }
        MyExoplayer.getCurrentSong()?.let {
            binding.playerView.visibility=View.VISIBLE
            binding.songTitleTextView.text=it.title
            Glide.with(binding.songCoverImageView).load(it.coverUrl).apply(RequestOptions().transform(RoundedCorners(32))).into(binding.songCoverImageView)
        }?:run{
            binding.playerView.visibility=View.GONE
        }
    }
    //Categories
    fun  getCategories(){
        FirebaseFirestore.getInstance().collection("category").get().addOnSuccessListener {
            val categoryList=it.toObjects(CategoryModel::class.java)
            setupCategoryRecyclerView(categoryList)
        }
    }
    fun setupCategoryRecyclerView(categoryList: List<CategoryModel>){
        categoryAdapter=CategoryAdapter(categoryList)
        binding.categoriesRecyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.categoriesRecyclerView.adapter=categoryAdapter
    }
    //Sections
    fun setupSection(id:String,mainLayout:RelativeLayout,titleView:TextView,recyclerView: RecyclerView){
        FirebaseFirestore.getInstance().collection("sections").document(id).get().addOnSuccessListener {
            val section=it.toObject(CategoryModel::class.java)
            section?.apply {
                mainLayout.visibility=View.VISIBLE
                titleView.text=name
                recyclerView.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                recyclerView.adapter=SectionSongListAdapter(songs)
                mainLayout.setOnClickListener{
                    SongsListActivity.category=section
                    startActivity(Intent(this@MainActivity,SongsListActivity::class.java))
                }
            }
        }
    }

}