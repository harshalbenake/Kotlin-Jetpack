package com.harshalbenake.kotlinjetpack.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.harshalbenake.kotlinjetpack.data.Entity.PersonProfile
import com.harshalbenake.kotlinjetpack.data.dao.PersonProfileDAO

/**
 * This is an abstract class for database instance of person profile
 */
@Database(entities = [(PersonProfile::class)], version = 1, exportSchema = false)
abstract class PersonProfileDb : RoomDatabase() {
    companion object {
        private var INSTANCE: PersonProfileDb? = null
        fun getDataBase(context: Context): PersonProfileDb {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, PersonProfileDb::class.java, "personprofile-db")
                        .allowMainThreadQueries().build()
            }
            return INSTANCE as PersonProfileDb
        }
    }

    abstract fun personProfileDAO(): PersonProfileDAO
}