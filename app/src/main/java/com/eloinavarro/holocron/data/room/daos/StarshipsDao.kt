package com.eloinavarro.holocron.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eloinavarro.holocron.data.room.model.RoomStarship

@Dao
interface StarshipsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: RoomStarship)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<RoomStarship>)

    @Query("SELECT * FROM SWStarship LIMIT :limit OFFSET :offset")
    suspend fun getAll(limit: Int, offset: Int): List<RoomStarship>

    @Query("SELECT * FROM SWStarship WHERE id = :id")
    suspend fun getStarshipById(id: Int): RoomStarship?
}