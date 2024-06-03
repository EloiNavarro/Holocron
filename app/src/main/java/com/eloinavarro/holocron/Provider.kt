package com.eloinavarro.holocron

import com.eloinavarro.holocron.data.Datasource
import com.eloinavarro.holocron.data.StorageDatasource
import com.eloinavarro.holocron.data.repositories.SWCharacterRepository
import com.eloinavarro.holocron.data.repositories.SWMovieRepository
import com.eloinavarro.holocron.data.repositories.SWPlanetRepository
import com.eloinavarro.holocron.data.repositories.SWSpecieRepository
import com.eloinavarro.holocron.data.repositories.SWStarshipRepository
import com.eloinavarro.holocron.data.repositories.SWVehicleRepository
import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.data.room.RoomDatasource
import com.eloinavarro.holocron.domain.usecase.GetCharacterByIdUseCase
import com.eloinavarro.holocron.domain.usecase.GetCharactersUseCase
import com.eloinavarro.holocron.domain.usecase.GetMovieByIdUseCase
import com.eloinavarro.holocron.domain.usecase.GetMoviesUseCase
import com.eloinavarro.holocron.domain.usecase.GetPlanetByIdUseCase
import com.eloinavarro.holocron.domain.usecase.GetPlanetsUseCase
import com.eloinavarro.holocron.domain.usecase.GetStarshipByIdUseCase
import com.eloinavarro.holocron.domain.usecase.GetStarshipsUseCase
import com.eloinavarro.holocron.domain.usecase.GetVehicleByIdUseCase
import com.eloinavarro.holocron.domain.usecase.GetVehiclesUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class Provider {
    companion object {
        val data = module {
            single<Datasource> { SwapiRetrofitDatasource() }
            single<StorageDatasource> { RoomDatasource.getDatabase(androidContext()) }

            single { SWCharacterRepository(get(), get()) }
            single { SWMovieRepository(get(), get()) }
            single { SWPlanetRepository(get(), get()) }
            single { SWSpecieRepository(get(), get()) }
            single { SWStarshipRepository(get(), get()) }
            single { SWVehicleRepository(get(), get()) }
        }

        val domain = module {
            single { GetCharacterByIdUseCase(get()) }
            single { GetCharactersUseCase(get()) }
            single { GetMovieByIdUseCase(get()) }
            single { GetMoviesUseCase(get()) }
            single { GetPlanetByIdUseCase(get()) }
            single { GetPlanetsUseCase(get()) }
            single { GetStarshipByIdUseCase(get()) }
            single { GetStarshipsUseCase(get()) }
            single { GetVehicleByIdUseCase(get()) }
            single { GetVehiclesUseCase(get()) }
        }
    }
}