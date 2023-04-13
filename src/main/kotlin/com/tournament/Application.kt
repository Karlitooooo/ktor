package com.tournament

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.tournament.plugins.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    configureRouting()
    install(ContentNegotiation) {
        jackson()
    }
}



