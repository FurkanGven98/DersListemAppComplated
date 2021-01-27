package com.furkanguven.derslistemapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkanguven.derslistemapp.model.Ders
import com.furkanguven.derslistemapp.servis.DersAPIServis
import com.furkanguven.derslistemapp.servis.DersDatabase
import com.furkanguven.derslistemapp.util.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class DersListesiViewModel(application: Application): BaseViewModel (application){
    val dersler =MutableLiveData<List<Ders>>()
    val dersHataMesaji = MutableLiveData<Boolean>()
    val dersYukleniyor = MutableLiveData<Boolean>()
    private var guncellemeZamani = 10*60*1000*1000*1000L

    private val dersApiServis =DersAPIServis()
    private val disposable =CompositeDisposable()
    private val ozelSharedPreferences=OzelSharedPreferences(getApplication())

    fun refreshData(){
        val kaydedilmeZamani =ozelSharedPreferences.zamaniAl()
        if(kaydedilmeZamani !=null && kaydedilmeZamani != 0L &&System.nanoTime()-kaydedilmeZamani <guncellemeZamani){
            verileriSQLitetanAl()
        }else{
            verileriInternettenAl()
        }
    }
    private fun verileriSQLitetanAl(){
        dersYukleniyor.value=true
        launch {
           val dersListesi= DersDatabase(getApplication()).dersDao().getAllDers()
            dersleriGoster(dersListesi)
        }
    }
    fun refreshFromInternet(){
        verileriInternettenAl()
    }
    private fun verileriInternettenAl(){
        dersYukleniyor.value = true
        disposable.add(
                dersApiServis.getData()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<Ders>>(){
                            override fun onSuccess(t: List<Ders>) {

                                sqliteSakla(t)
                            }

                            override fun onError(e: Throwable) {
                                dersHataMesaji.value=true
                                dersYukleniyor.value=false
                                e.printStackTrace()
                            }
                        })
        )

    }
    private fun dersleriGoster(derslerListesi : List<Ders>){
        dersler.value = derslerListesi
        dersHataMesaji.value =false
        dersYukleniyor.value=false
    }
    private fun sqliteSakla(derslerListesi: List<Ders>){
    launch {
        val dao = DersDatabase(getApplication()).dersDao()
        dao.deleteAllDers()
        val uuidListesi = dao.insertAll(*derslerListesi.toTypedArray())
        var i =0
        while (i<derslerListesi.size){
            derslerListesi[i].uuid = uuidListesi[i].toInt()
            i=i+1
        }
        dersleriGoster(derslerListesi)
    }
        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }
}