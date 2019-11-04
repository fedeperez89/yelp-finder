package com.xteam.sonytakehome.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xteam.sonytakehome.model.Business

@Database(
    entities = [Business::class],
    version = 1,
    exportSchema = false
)
abstract class YelpDB : RoomDatabase() {

    abstract fun businessDao(): BusinessDao

}