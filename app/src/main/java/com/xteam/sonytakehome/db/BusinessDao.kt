package com.xteam.sonytakehome.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xteam.sonytakehome.model.Business

@Dao
abstract class BusinessDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg businesses: Business)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRepos(businesses: List<Business>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun createBusinessIfNotExists(business: Business): Long

    @Query("SELECT * FROM business WHERE name like :name")
    abstract fun load(name: String): LiveData<List<Business>>
}