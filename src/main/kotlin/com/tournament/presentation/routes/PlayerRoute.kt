package com.tournament.presentation.routes

import com.tournament.data.dataSources.mongodb.MongoDBPlayerDataSource
import com.tournament.presentation.models.CreatePlayerDTO
import com.tournament.presentation.models.UpdatePlayerDTO
import com.tournament.domain.repositories.PlayerRepositoryImpl
import com.tournament.domain.useCases.CreatePlayer
import com.tournament.domain.useCases.DeleteAllPlayers
import com.tournament.domain.useCases.GetAllPlayers
import com.tournament.domain.useCases.UpdateOnePlayer
import com.tournament.domain.useCases.GetOnePlayer
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.parameter.parametersOf
import org.koin.ktor.ext.inject

fun Route.playerRoutes() {

  route("/players") {
    val mongoDBPlayerDataSource = MongoDBPlayerDataSource()

    val playerRepositoryImpl: PlayerRepositoryImpl by inject {
      parametersOf(
        mongoDBPlayerDataSource
      )
    }
    get() {
      val players = GetAllPlayers(playerRepositoryImpl).execute()
      call.respond(players)
    }
    get("/{id}") {
      val playerWithRank = GetOnePlayer(playerRepositoryImpl).execute(call.parameters["id"])
        ?: return@get call.response.status(HttpStatusCode.NotFound)

      call.respond(playerWithRank)
    }
    patch("/{id}") {
      val (points) = call.receive<UpdatePlayerDTO>()
      UpdateOnePlayer(playerRepositoryImpl).execute(call.parameters["id"], points)
      call.response.status(HttpStatusCode.NoContent)
    }
    post() {
      val (pseudo) = call.receive<CreatePlayerDTO>()
      val player = CreatePlayer(playerRepositoryImpl).execute(pseudo)
      player ?: return@post call.response.status(HttpStatusCode.NoContent)
      call.respond(player)
    }
    delete() {
      DeleteAllPlayers(playerRepositoryImpl).execute()
      call.response.status(HttpStatusCode.NoContent)
    }
  }
}
