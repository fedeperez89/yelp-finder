package com.xteam.sonytakehome.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xteam.sonytakehome.model.Business

@Dao
interface BusinessDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg businesses: Business)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(businesses: List<Business>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createBusinessIfNotExists(business: Business): Long

    @Query("SELECT * FROM business WHERE name like :name")
    suspend fun load(name: String): List<Business>
}