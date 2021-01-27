package com.furkanguven.derslistemapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkanguven.derslistemapp.model.Ders
import com.furkanguven.derslistemapp.servis.DersDatabase
import kotlinx.coroutines.launch

class DersDetayiViewModel(application: Application): BaseViewModel(application) {
    val dersLiveData =MutableLiveData<Ders>()

    fun roomVerisiniAl(uuid : Int){
        launch {
            val dao = DersDatabase(getApplication()).dersDao()
            val ders = dao.getDers(uuid)
            dersLiveData.value=ders
        }

    }
}