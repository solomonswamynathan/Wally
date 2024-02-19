package com.example.wally

data class Wallpapers(
    var success: Boolean,
    var total_photos: Int,
    var message: String,
    var offset: Int,
    var limit: Int,
    var photos: List<Photo>
)

data class Photo(
    var url: String,
    var title: String,
    var user: Int,
    var id: Int,
    var description: String
)

//example model
///{
//    "success": true,
//    "total_photos": 132,
//    "message": "Successfully fetched 30 of 132 photos",
//    "offset": 5,
//    "limit": 30,
//    "photos": [
//        {
//            "url": "https://api.slingacademy.com/public/sample-photos/6.jpeg",
//            "title": "Apply future response she reduce decide",
//            "user": 30,
//            "id": 6,
//            "description": "Training beautiful age four skin cultural hundred environmental ability blood go physical relate produce tough open police."
//        }]
//}
