package com.example.wally

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wally.databinding.ActivityHomeBinding
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONException
import java.io.IOException

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        val client = OkHttpClient()
        val url = "https://api.slingacademy.com/v1/sample-data/photos?offset=5&limit=30"
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {

                if(response.isSuccessful){
                    runOnUiThread {
                        try{
                            val jsonString= response.body?.string()
                            val gson= Gson()
                            val photoResponse: Wallpapers= gson.fromJson(jsonString, Wallpapers::class.java)


                            //
                            val layoutManager = LinearLayoutManager(this@HomeActivity)
                            binding.photoRecyclerView.layoutManager = layoutManager
                            binding.photoRecyclerView.adapter = PhotoAdapter(photoResponse.photos)
                        }catch (e:JSONException){
                            e.printStackTrace()
                        }
                    }
                }
            }
        })
    }
    class PhotoAdapter(private val photo: List<Photo>):
        RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
            val itemView= LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo, parent,false)
            return PhotoViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
            val photo = photo[position]

            //
            Glide.with(holder.itemView.context)
                .load(photo.url)
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(holder.photoImageView)
        }

        override fun getItemCount(): Int {
           return photo.size
        }

        class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val photoImageView: ImageView = itemView.findViewById(R.id.photoImageView)
        }
    }
}