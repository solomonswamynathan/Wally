package com.example.wally

import android.app.WallpaperManager
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.database.Cursor
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.wally.databinding.ActivityPhotoBinding
import java.io.IOException

class PhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoBinding
    private lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        dbHelper = DBHelper(this)

        //get intent passed from home_activity
        val photo = intent.getParcelableExtra<Photo>("photo")
        if (photo != null) {
            Glide.with(this).load(photo.url).into(binding.imageView)
        }

        //Set wallpaper
        binding.button.setOnClickListener {
            val wallManager = WallpaperManager.getInstance(applicationContext)
            val bmpImg = (binding.imageView.drawable as BitmapDrawable).bitmap
            try {
                wallManager.setBitmap(bmpImg)
                Toast.makeText(this@PhotoActivity, "Wallpaper set successfully", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.stackTraceToString()
            }
        }

        val photoList = getFav()
        val isFavourite = photoList.contains(photo?.id ?: -1)

        updateFavouritesView(isFavourite)

        binding.imageView2.setOnClickListener {
            val photoList = getFav()
            val isCurrentlyFavourite = photoList.contains(photo?.id ?: -1)
            if (isCurrentlyFavourite) {
                removeFav(photo?.id ?: -1)
            } else {
                insertFav(photo?.id ?: -1)
            }
            //change that didn't update the view in the class
            //just added ! to isCurrentlyFavourite
            updateFavouritesView(!isCurrentlyFavourite)
        }

    }

    private fun updateFavouritesView(isFavourite: Boolean) {
        if (isFavourite) {
            binding.imageView2.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.imageView2.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    //insert fav wallpapers to sqlite db using DBHelper
    private fun insertFav(id: Int) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DBHelper.PHOTO_ID, id)
        }
        db?.insert(DBHelper.TABLE_NAME, null, values)
        Log.i(TAG, "Added to favorites")
    }

    //remove fav wallpapers from sqlite db using DBHelper
    private fun removeFav(id: Int) {
        val db = dbHelper.writableDatabase
        val selection = "${DBHelper.PHOTO_ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        db?.delete(DBHelper.TABLE_NAME, selection, selectionArgs)
    }


    //get list of all wallpapers from sqlite db using DBHelper
    private fun getFav(): List<Int> {
        val photoList = mutableListOf<Int>()
        val db = dbHelper.readableDatabase

        val cursor: Cursor? = db?.query(
            DBHelper.TABLE_NAME,
            arrayOf(DBHelper.PHOTO_ID),
            null,
            null,
            null,
            null, null
        )

        cursor?.use {
            while (it.moveToNext()) {
                val photoId = it.getInt(it.getColumnIndexOrThrow(DBHelper.PHOTO_ID))
                photoList.add(photoId)
            }
        }
        return photoList
    }
}