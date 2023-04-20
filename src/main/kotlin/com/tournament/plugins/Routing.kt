package com.tournament.plugins

import com.tournament.presentation.routes.playerRoutes
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
  routing {
    playerRoutes()
  }
}
