package com.tournament.plugins

import com.tournament.data.database
import com.tournament.data.model.Player
import com.tournament.data.model.PlayerDTO
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import org.litote.kmongo.*

fun Application.configureRouting() {
    routing {
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
        post("/players") {
            val payload  = call.receive<PlayerDTO>()
            database.getCollection<Player>().insertOne(Player(pseudo = payload.pseudo, points = 0))
            call.response.status(HttpStatusCode.Created)
        }
    }
}
