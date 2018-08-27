package com.harshalbenake.kotlinjetpack.data.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * This class is pojo class for person profiles
 */
@Entity(tableName = "personprofile")
data class PersonProfile(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "idPerson")
        var idPerson: Int = 0,

        @ColumnInfo(name = "name")
        var name: String = "",

        @ColumnInfo(name = "age")
        var age: String = ""

)