package com.tournament.routes

import com.tournament.data.database
import com.tournament.data.model.CreatePlayerDTO
import com.tournament.data.model.Player
import com.tournament.data.model.PlayerWithRank
import com.tournament.data.model.UpdatePlayerDTO
import com.tournament.data.repository.PlayerRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.*


fun Route.playerRoutes() {
    val playerRepository = PlayerRepository()

    get("/players") {
        val players = playerRepository.findAllSorted()
        call.respond(players)
    }
    get("/players/{id}") {
        val players = playerRepository.findAllSorted()

        val index = players.indexOfFirst { player -> player._id == call.parameters["id"] }

        if(index == -1) return@get call.response.status(HttpStatusCode.NotFound)

        val player = players[index]

        call.respond(PlayerWithRank(pseudo = player.pseudo, points = player.points, _id = player._id, rank = index + 1 ))
    }
    patch("/players/{id}") {
        val payload = call.receive<UpdatePlayerDTO>()
        val response = playerRepository.updatePoints(call.parameters["id"], payload.points)
        call.response.status(HttpStatusCode.NoContent)
    }
    post("/players") {
        val payload = call.receive<CreatePlayerDTO>()
        playerRepository.addPlayer(payload.pseudo)
        call.response.status(HttpStatusCode.Created)
    }
    delete("/players") {
        playerRepository.deleteAll();
        call.response.status(HttpStatusCode.NoContent)
    }
}