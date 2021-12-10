package com.pratama.baseandroid.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pratama.baseandroid.data.datasource.local.dao.NewsDao
import com.pratama.baseandroid.data.datasource.local.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
