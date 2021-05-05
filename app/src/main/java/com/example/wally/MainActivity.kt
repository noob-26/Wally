package com.example.wally

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var api: PixabayApi
    val API_KEY: String = "21480371-4e8be9a4e296da2c8c5942fae"
    lateinit var query: TextInputEditText
    lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.recycler_view)

        val search: Button = findViewById<Button>(R.id.search_btn)
        query = findViewById(R.id.category)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(PixabayApi::class.java)

        search.setOnClickListener {
            val category = query.text.toString()
            generateData(category)
        }
    }

    private fun generateData(category: String) {
        val data: MutableList<ListItem> = ArrayList()

        val call: Call<ApiModel> = api.getImages(API_KEY, category)
        call.enqueue(object: Callback<ApiModel>{
            override fun onResponse(call: Call<ApiModel>, response: Response<ApiModel>) {
                if(!response.isSuccessful){
                    query.setError("Please Enter a valid category")
                    query.requestFocus()
                    return
                } else {
                    val result = response.body()
                    if (result != null) {
                        for(image in result.images){
                            data.add(ListItem(image.url, image.likes, image.user))
                        }
                    }
                    recyclerview.adapter = ListAdapter(this@MainActivity, data)
                    recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerview.setHasFixedSize(true)
                }
            }

            override fun onFailure(call: Call<ApiModel>, t: Throwable) {
                query.setError(t.message)
                query.requestFocus()
            }
        })

    }
}