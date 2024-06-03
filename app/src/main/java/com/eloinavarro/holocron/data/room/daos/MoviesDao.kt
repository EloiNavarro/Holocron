package com.eloinavarro.holocron.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eloinavarro.holocron.data.room.model.RoomMovie

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: RoomMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<RoomMovie>)

    @Query("SELECT * FROM SWMovie LIMIT :limit OFFSET :offset")
    suspend fun getAll(limit: Int, offset: Int): List<RoomMovie>

    @Query("SELECT * FROM SWMovie WHERE id = :id")
    suspend fun getMovieById(id: Int): RoomMovie?
}