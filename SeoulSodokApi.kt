package com.example.bug_1128

// import android.telecom.Call
import com.example.bug_1128.data.Library

import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.GET

// val DOMAIN = "https://gist.githubusercontent.com/im-una/8663f448c83a0087132b68ac0b7e4373/raw/dd7a12eafdb50447d53554e822b3f55b54a481e6/companymap.json"

class SeoulSodokApi {
    companion object {
        val DOMAIN = "https://gist.githubusercontent.com/im-una/8663f448c83a0087132b68ac0b7e4373/raw/"
        val API_KEY = "dd7a12eafdb50447d53554e822b3f55b54a481e6"
    }
}

interface SeoulOpenService {
    @GET("{api_key}/companymap.json")
    fun getLibraries(@Path("api_key") key: String) : Call<Library>
}