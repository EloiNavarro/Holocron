package com.eloinavarro.holocron.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eloinavarro.holocron.data.room.model.RoomVehicle

@Dao
interface VehiclesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: RoomVehicle)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<RoomVehicle>)

    @Query("SELECT * FROM SWVehicle LIMIT :limit OFFSET :offset")
    suspend fun getAll(limit: Int, offset: Int): List<RoomVehicle>

    @Query("SELECT * FROM SWVehicle WHERE id = :id")
    suspend fun getVehicleById(id: Int): RoomVehicle?
}