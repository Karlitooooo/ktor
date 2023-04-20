package com.tournament.modules

import com.tournament.data.dataSources.mongodb.MongoDBPlayerDataSource
import com.tournament.domain.repositories.PlayerRepositoryImpl
import org.koin.dsl.module

val appModule = module {
  single { params -> PlayerRepositoryImpl(playerDataSource = params.get()) }
  single { MongoDBPlayerDataSource() }
}
