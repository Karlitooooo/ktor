package com.tournament.routes

import com.tournament.data.model.CreatePlayerDTO
import com.tournament.data.model.PlayerWithRank
import com.tournament.data.model.UpdatePlayerDTO
import com.tournament.data.repository.PlayerRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.playerRoutes() {
  val playerRepository by inject<PlayerRepositoryImpl>()

  route("/players") {
    get() {
      val players = playerRepository.findAllSorted()
      call.respond(players)
    }
    get("/{id}") {
      val players = playerRepository.findAllSorted()

      val index = players.indexOfFirst { player -> player._id == call.parameters["id"] }

      if (index == -1) return@get call.response.status(HttpStatusCode.NotFound)

      val (_id, pseudo, points) = players[index]

      call.respond(PlayerWithRank(pseudo = pseudo, points = points, _id = _id, rank = index + 1))
    }
    patch("/{id}") {
      val (points) = call.receive<UpdatePlayerDTO>()
      playerRepository.updatePoints(call.parameters["id"], points)
      call.response.status(HttpStatusCode.NoContent)
    }
    post() {
      val (pseudo) = call.receive<CreatePlayerDTO>()
      val player = playerRepository.addPlayer(pseudo) ?: return@post call.response.status(HttpStatusCode.NoContent)
      call.respond(player)
    }
    delete() {
      playerRepository.deleteAll();
      call.response.status(HttpStatusCode.NoContent)
    }
  }
}
