package com.thewyp.pagingexample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thewyp.pagingexample.data.model.Cat
import com.thewyp.pagingexample.data.model.RemoteKeys

@Database(entities = [Cat::class, RemoteKeys::class], version = 1)
abstract class CatDatabase : RoomDatabase() {
    abstract fun getCatDao(): CatDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}