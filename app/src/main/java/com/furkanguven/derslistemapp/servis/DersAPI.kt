package com.furkanguven.derslistemapp.servis

import com.furkanguven.derslistemapp.model.Ders
import io.reactivex.Single
import retrofit2.http.GET

interface DersAPI {
    //https://raw.githubusercontent.com/FurkanGven98/DersListemApp/main/dersler.json
    @GET("FurkanGven98/DersListemApp/main/dersler.json")
    fun getDers() : Single<List<Ders>>
}