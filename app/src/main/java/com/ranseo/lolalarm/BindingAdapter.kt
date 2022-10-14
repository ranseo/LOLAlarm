package com.ranseo.lolalarm

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ranseo.lolalarm.util.ImageFromUrl

@BindingAdapter("setImage")
fun <T> setImageFromUrl(view: View, profileIconId:T) {
    val url = ImageFromUrl.getProfileImageUrl(profileIconId)
    ImageFromUrl.setProfileImageView(url, view, view as ImageView)
}
