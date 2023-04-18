package com.tournament.modules

import com.tournament.data.repository.PlayerRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
  singleOf(::PlayerRepositoryImpl)
}
