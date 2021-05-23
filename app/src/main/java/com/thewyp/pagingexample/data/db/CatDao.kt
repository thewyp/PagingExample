package com.thewyp.pagingexample.data.db

import androidx.paging.PagingSource
import androidx.room.*
import com.thewyp.pagingexample.data.model.Cat

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun instertAll(cats: List<Cat>)

    @Query("DELETE FROM cats")
    suspend fun deleteAll()

    @Query("SELECT * FROM cats")
    fun getAll(): PagingSource<Int, Cat>
}