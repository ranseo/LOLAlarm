package com.ranseo.lolalarm.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

object ProfileImage {
    private const val LOL_PROFILE_URL = "https://ddragon.leagueoflegends.com/cdn/12.19.1/img/profileicon/"

    fun getProfileImageUrl(profileIconId:Int) : String {
        return "$LOL_PROFILE_URL$profileIconId.png"
    }

    fun setProfileImageView(url:String, view: View, imageView: ImageView) {
        Glide.with(view)
            .load(url)
            .into(imageView)
    }
}