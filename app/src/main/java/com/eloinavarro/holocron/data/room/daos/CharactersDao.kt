package com.eloinavarro.holocron.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eloinavarro.holocron.data.room.model.RoomCharacter

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: RoomCharacter)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<RoomCharacter>)

    @Query("SELECT * FROM SWCharacter LIMIT :limit OFFSET :offset")
    suspend fun getAll(limit: Int, offset: Int): List<RoomCharacter>

    @Query("SELECT * FROM SWCharacter WHERE id = :id")
    suspend fun getCharacterById(id: Int): RoomCharacter?
}