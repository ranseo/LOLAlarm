package com.ranseo.lolalarm.util

object ProfileImage {
    private const val LOL_PROFILE_URL = "http://ddragon.leagueoflegends.com/cdn/10.11.1/img/profileicon/"

    fun getProfileImageUrl(profileIconId:String) : String {
        return "$LOL_PROFILE_URL$profileIconId.png"
    }
}