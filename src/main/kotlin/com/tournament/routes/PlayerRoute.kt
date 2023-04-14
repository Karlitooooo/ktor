package com.tournament.routes

import com.tournament.data.database
import com.tournament.data.model.CreatePlayerDTO
import com.tournament.data.model.Player
import com.tournament.data.model.PlayerWithRank
import com.tournament.data.model.UpdatePlayerDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.*


fun Route.playerRoutes() {
    get("/players") {
        val col = database.getCollection<Player>()

        val players = col.aggregate<Player>(
            sort(
                descending(
                    Player::points
                )
            )
        ).toList()

        call.respond(players)
    }
    get("/players/{id}") {
        val col = database.getCollection<Player>()

        val players = col.aggregate<Player>(
            sort(
                descending(
                    Player::points
                )
            )
        ).toList()

        val index = players.indexOfFirst { player -> player._id == call.parameters["id"] }

        if(index == -1) return@get call.response.status(HttpStatusCode.NotFound)

        call.respond(PlayerWithRank(pseudo = players[index].pseudo, points = players[index].points, _id = players[index]._id, rank = index + 1 ))
    }
    patch("/players/{id}") {
        val col = database.getCollection<Player>()
        val payload = call.receive<UpdatePlayerDTO>()
        val response = col.updateOne(Player::_id eq call.parameters["id"], setValue(Player::points, payload.points))
        call.response.status(HttpStatusCode.NoContent)
    }
    post("/players") {
        val payload = call.receive<CreatePlayerDTO>()
        database.getCollection<Player>().insertOne(Player(pseudo = payload.pseudo, points = 0));
        call.response.status(HttpStatusCode.Created)
    }
    delete("/players") {
        val col = database.getCollection<Player>()
        col.deleteMany()
        call.response.status(HttpStatusCode.NoContent)
    }
}