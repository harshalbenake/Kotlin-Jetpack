package com.harshalbenake.kotlinjetpack.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.harshalbenake.kotlinjetpack.data.Entity.PersonProfile

/**
 * This is an DAO interface for person profiles.
 */
@Dao
interface PersonProfileDAO {
    @Query("select * from personprofile")
    fun getAllPersonProfiles(): LiveData<List<PersonProfile>>

    @Query("select * from personprofile where age>18")
    fun getAllPersonProfilesAbove18(): PersonProfile

    @Query("select * from personprofile where idPerson in (:id)")
    fun getPersonById(id: Int): PersonProfile

    @Query("delete from personprofile")
    fun deleteAllPersonProfiles()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPersonProfiles(personProfile: PersonProfile)

    @Update
    fun updatePersonProfiles(personProfile: PersonProfile)

    @Delete
    fun deletePersonProfiles(personProfile: PersonProfile)
}