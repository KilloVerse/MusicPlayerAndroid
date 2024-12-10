package com.example.try10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.try10.adapter.CategoryAdapter
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
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
}