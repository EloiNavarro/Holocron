package com.eloinavarro.holocron.data.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eloinavarro.holocron.data.StorageDatasource
import com.eloinavarro.holocron.data.room.converters.SWTypeConverters
import com.eloinavarro.holocron.data.room.daos.CharactersDao
import com.eloinavarro.holocron.data.room.daos.MoviesDao
import com.eloinavarro.holocron.data.room.daos.PlanetsDao
import com.eloinavarro.holocron.data.room.daos.SpeciesDao
import com.eloinavarro.holocron.data.room.daos.StarshipsDao
import com.eloinavarro.holocron.data.room.daos.VehiclesDao
import com.eloinavarro.holocron.data.room.model.RoomCharacter
import com.eloinavarro.holocron.data.room.model.RoomMovie
import com.eloinavarro.holocron.data.room.model.RoomPlanet
import com.eloinavarro.holocron.data.room.model.RoomSpecie
import com.eloinavarro.holocron.data.room.model.RoomStarship
import com.eloinavarro.holocron.data.room.model.RoomVehicle
import com.eloinavarro.holocron.data.room.model.toDomainModel
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.SWMovie
import com.eloinavarro.holocron.domain.SWPlanet
import com.eloinavarro.holocron.domain.SWSpecie
import com.eloinavarro.holocron.domain.SWStarship
import com.eloinavarro.holocron.domain.SWVehicle

@Database(
    entities = [
        RoomCharacter::class,
        RoomMovie::class,
        RoomPlanet::class,
        RoomSpecie::class,
        RoomStarship::class,
        RoomVehicle::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(SWTypeConverters::class)
abstract class RoomDatasource : RoomDatabase(), StorageDatasource {
    abstract fun moviesDao(): MoviesDao
    abstract fun speciesDao(): SpeciesDao
    abstract fun planetsDao(): PlanetsDao
    abstract fun vehiclesDao(): VehiclesDao
    abstract fun starshipsDao(): StarshipsDao
    abstract fun charactersDao(): CharactersDao

    override suspend fun storeMovies(data: List<SWMovie>) {
        moviesDao().insertAll(data.map { RoomMovie.fromDomain(it) })
    }

    override suspend fun storePlanets(data: List<SWPlanet>) {
        planetsDao().insertAll(data.map { RoomPlanet.fromDomain(it) })
    }

    override suspend fun storeSpecies(data: List<SWSpecie>) {
        speciesDao().insertAll(data.map { RoomSpecie.fromDomain(it) })
    }

    override suspend fun storeVehicles(data: List<SWVehicle>) {
        vehiclesDao().insertAll(data.map { RoomVehicle.fromDomain(it) })
    }

    override suspend fun storeStarships(data: List<SWStarship>) {
        starshipsDao().insertAll(data.map { RoomStarship.fromDomain(it) })
    }

    override suspend fun storeCharacters(data: List<SWCharacter>) {
        charactersDao().insertAll(data.map { RoomCharacter.fromDomain(it) })
    }

    override suspend fun getAllCharacters(page: Int): Result<List<SWCharacter>> {
        val offset = (page-1) * RESULTS_PER_PAGE
        val response = charactersDao().getAll(RESULTS_PER_PAGE, offset)
        if(response.isNotEmpty()) {
            return Result.success(response.map { it.toDomainModel() })
        }
        return Result.failure(Exception("Error getAllCharacters($page)"))
    }

    override suspend fun getCharacterById(id: Int): Result<SWCharacter> {
        val response = charactersDao().getCharacterById(id)
        if(response != null) {
            return Result.success(response.toDomainModel())
        }
        return Result.failure(Exception("Error getCharacterById($id)"))
    }

    override suspend fun getAllPlanets(page: Int): Result<List<SWPlanet>> {
        val offset = (page-1) * RESULTS_PER_PAGE
        val response = planetsDao().getAll(RESULTS_PER_PAGE, offset)
        if(response.isNotEmpty()) {
            return Result.success(response.map { it.toDomainModel() })
        }
        return Result.failure(Exception("Error getAllCharacters($page)"))
    }

    override suspend fun getPlanetById(id: Int): Result<SWPlanet> {
        val response = planetsDao().getPlanetById(id)
        if(response != null) {
            return Result.success(response.toDomainModel())
        }
        return Result.failure(Exception("Error getPlanetById($id)"))
    }

    override suspend fun getAllStarships(page: Int): Result<List<SWStarship>> {
        val offset = (page-1) * RESULTS_PER_PAGE
        val response = starshipsDao().getAll(RESULTS_PER_PAGE, offset)
        if(response.isNotEmpty()) {
            return Result.success(response.map { it.toDomainModel() })
        }
        return Result.failure(Exception("Error getAllCharacters($page)"))
    }

    override suspend fun getStarshipById(id: Int): Result<SWStarship> {
        val response = starshipsDao().getStarshipById(id)
        if(response != null) {
            return Result.success(response.toDomainModel())
        }
        return Result.failure(Exception("Error getStarshipById($id)"))
    }

    override suspend fun getAllVehicles(page: Int): Result<List<SWVehicle>> {
        val offset = (page-1) * RESULTS_PER_PAGE
        val response = vehiclesDao().getAll(RESULTS_PER_PAGE, offset)
        if(response.isNotEmpty()) {
            return Result.success(response.map { it.toDomainModel() })
        }
        return Result.failure(Exception("Error getAllCharacters($page)"))
    }

    override suspend fun getVehicleById(id: Int): Result<SWVehicle> {
        val response = vehiclesDao().getVehicleById(id)
        if(response != null) {
            return Result.success(response.toDomainModel())
        }
        return Result.failure(Exception("Error getVehicleById($id)"))
    }

    override suspend fun getAllSpecies(page: Int): Result<List<SWSpecie>> {
        val offset = (page-1) * RESULTS_PER_PAGE
        val response = speciesDao().getAll(RESULTS_PER_PAGE, offset)
        if(response.isNotEmpty()) {
            return Result.success(response.map { it.toDomainModel() })
        }
        return Result.failure(Exception("Error getAllCharacters($page)"))
    }

    override suspend fun getSpecieById(id: Int): Result<SWSpecie> {
        val response = speciesDao().getSpecieById(id)
        if(response != null) {
            return Result.success(response.toDomainModel())
        }
        return Result.failure(Exception("Error getSpecieById($id)"))
    }

    override suspend fun getAllMovies(page: Int): Result<List<SWMovie>> {
        val offset = (page-1) * RESULTS_PER_PAGE
        val response = moviesDao().getAll(RESULTS_PER_PAGE, offset)
        if(response.isNotEmpty()) {
            return Result.success(response.map { it.toDomainModel() })
        }
        return Result.failure(Exception("Error getAllCharacters($page)"))
    }

    override suspend fun getMovieById(id: Int): Result<SWMovie> {
        val response = moviesDao().getMovieById(id)
        if(response != null) {
            return Result.success(response.toDomainModel())
        }
        return Result.failure(Exception("Error getMovieById($id)"))
    }

    companion object {
        @Volatile
        private var INSTANCE: RoomDatasource? = null
        private const val DATABASE_NAME = "the_holocron"
        private const val RESULTS_PER_PAGE = 10

        fun getDatabase(context: android.content.Context): RoomDatasource {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDatasource::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}