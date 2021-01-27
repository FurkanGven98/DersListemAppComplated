package com.furkanguven.derslistemapp.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.furkanguven.derslistemapp.model.Ders

@Database(entities = arrayOf(Ders::class),version = 1)
abstract class DersDatabase : RoomDatabase() {
    abstract fun dersDao(): DersDAO
    //Singleton
    companion object{
        @Volatile private var instance : DersDatabase?=null
        private val lock = Any()
        operator  fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: databaseOlustur(context).also {
                instance =it
            }
        }


        private fun databaseOlustur(context: Context) = Room.databaseBuilder(context.applicationContext,
                DersDatabase::class.java,"dersdatabase").build()
    }

}