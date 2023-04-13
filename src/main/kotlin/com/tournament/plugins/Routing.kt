package com.tournament.plugins

import com.tournament.data.database
import com.tournament.data.model.Player
import com.tournament.data.model.CreatePlayerDTO
import com.tournament.data.model.UpdatePlayerDTO
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
        put("/players/{id}") {
            val col = database.getCollection<Player>()
            val payload  = call.receive<UpdatePlayerDTO>()
            val response = col.updateOne(Player::_id eq call.parameters["id"], setValue(Player::points, payload.points))
            call.response.status(HttpStatusCode.NoContent)
        }
        post("/players") {
            val payload  = call.receive<CreatePlayerDTO>()
            database.getCollection<Player>().insertOne(Player(pseudo = payload.pseudo, points = 0));
            call.response.status(HttpStatusCode.Created)
        }
        delete("/players") {
            val col = database.getCollection<Player>()
            col.deleteMany()
            call.response.status(HttpStatusCode.NoContent)
        }
    }
}
