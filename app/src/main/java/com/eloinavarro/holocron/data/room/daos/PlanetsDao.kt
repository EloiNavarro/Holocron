package com.eloinavarro.holocron.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eloinavarro.holocron.data.room.model.RoomPlanet

@Dao
interface PlanetsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: RoomPlanet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<RoomPlanet>)

    @Query("SELECT * FROM SWPlanet LIMIT :limit OFFSET :offset")
    suspend fun getAll(limit: Int, offset: Int): List<RoomPlanet>

    @Query("SELECT * FROM SWPlanet WHERE id = :id")
    suspend fun getPlanetById(id: Int): RoomPlanet?
}