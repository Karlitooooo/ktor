package com.tournament.plugins

import com.tournament.data.database
import com.tournament.data.model.Player
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import org.litote.kmongo.getCollection

fun Application.configureRouting() {
    routing {
        get("/players") {
            val col = database.getCollection<Player>() //KMongo extension method
            val players = col.find().toList()
            call.respond(players)
        }
    }
}
