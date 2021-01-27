package com.furkanguven.derslistemapp.servis

import com.furkanguven.derslistemapp.model.Ders
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DersAPIServis {
//https://raw.githubusercontent.com/FurkanGven98/DersListemApp/main/biglisayarUc.json.txt
   private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api =Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(DersAPI::class.java)

    fun getData(): Single <List<Ders>>{
        return api.getDers()
    }
}