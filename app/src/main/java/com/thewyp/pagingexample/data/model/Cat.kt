package com.thewyp.pagingexample.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class Cat(@PrimaryKey val id: String, val url: String)