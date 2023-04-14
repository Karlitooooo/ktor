package com.tournament.data
import io.github.cdimascio.dotenv.dotenv
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.*

val dotenv = dotenv()

val client =
    KMongo.createClient(dotenv["DATABASE_URL"])
val database: MongoDatabase = client.getDatabase("kotlin-testing")



