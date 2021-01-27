package com.furkanguven.derslistemapp.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.furkanguven.derslistemapp.model.Ders
@Dao
interface DersDAO {
    @Insert
    suspend fun insertAll(vararg ders :Ders) : List<Long>
    @Query("SELECT * FROM ders")
    suspend fun  getAllDers() : List<Ders>
    @Query("SELECT * FROM ders WHERE uuid= :dersId ")
    suspend fun getDers(dersId: Int) : Ders
    @Query("DELETE FROM ders")
    suspend fun deleteAllDers()
}