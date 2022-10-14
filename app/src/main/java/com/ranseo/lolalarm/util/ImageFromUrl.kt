package com.ranseo.lolalarm.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageFromUrl {
    private const val LOL_PROFILE_URL = "https://ddragon.leagueoflegends.com/cdn/12.19.1/img/profileicon/"
    private const val LOL_MINIMAP_URL = "https://ddragon.leagueoflegends.com/cdn/6.8.1/img/map/"

    fun <T> getProfileImageUrl(profileIconId:T) : String {
        return "$LOL_PROFILE_URL$profileIconId.png"
    }

    fun getMiniMapImageUrl(mapId:Long) : String {
        return "${LOL_MINIMAP_URL}map$mapId.png"
    }

    fun setProfileImageView(url:String, view: View, imageView: ImageView) {
        Glide.with(view)
            .load(url)
            .into(imageView)
    }
}