package com.tournament

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.tournament.plugins.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

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

    install(Koin) {
        slf4jLogger()
        modules()
    }

    install(ContentNegotiation) {
        jackson()
    }
}



