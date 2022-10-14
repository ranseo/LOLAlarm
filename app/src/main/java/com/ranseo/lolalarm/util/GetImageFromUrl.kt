package com.ranseo.lolalarm.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

object GetImageFromUrl {
    private const val LOL_PROFILE_URL = "https://ddragon.leagueoflegends.com/cdn/12.19.1/img/profileicon/"
    private const val LOL_MINIMAP_URL = "http://ddragon.leagueoflegends.com/cdn/6.8.1/img/map/"

    fun getProfileImageUrl(profileIconId:Int) : String {
        return "$LOL_PROFILE_URL$profileIconId.png"
    }

    fun getMiniMapImageUrl(mapId:Int) : String {
        return "$LOL_MINIMAP_URL$mapId.png"
    }

    fun setProfileImageView(url:String, view: View, imageView: ImageView) {
        Glide.with(view)
            .load(url)
            .into(imageView)
    }
}