package com.tournament

import com.tournament.modules.appModule
import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import com.tournament.plugins.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

class ApplicationTest {
  @Test
  fun setup() = testApplication {
    application {
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
    val response = client.get("players")
    assertEquals(HttpStatusCode.OK, response.status)
  }
}

