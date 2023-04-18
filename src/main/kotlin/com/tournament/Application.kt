package com.tournament

import com.tournament.modules.appModule
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.tournament.plugins.*
import io.ktor.http.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import io.ktor.server.plugins.cors.routing.*

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
    modules(appModule)
  }

  install(CORS) {
    anyHost()
    allowHeader(HttpHeaders.ContentType)
    allowMethod(HttpMethod.Patch)
    allowMethod(HttpMethod.Post)
    allowMethod(HttpMethod.Delete)
  }

  install(ContentNegotiation) {
    jackson()
  }
}



