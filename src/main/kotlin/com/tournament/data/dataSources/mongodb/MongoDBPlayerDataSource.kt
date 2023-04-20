package com.tournament.data.dataSources.mongodb

import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Field
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import com.tournament.data.interfaces.PlayerDataSource
import com.tournament.domain.models.Player
import com.tournament.domain.models.PlayerWithRank
import io.github.cdimascio.dotenv.dotenv
import org.litote.kmongo.*

class MongoDBPlayerDataSource() : PlayerDataSource {

  private val _client = KMongo.createClient(dotenv()["DATABASE_URL"])
  private val _database: MongoDatabase = _client.getDatabase("kotlin-testing")

  private val _playerCollection = this._database.getCollection<Player>()
  override fun getAllSorted(): List<Player> {
    return this._playerCollection.aggregate<Player>(
      sort(
        descending(
          Player::points
        )
      )
    ).toList()
  }

  override fun getOne(id: String): PlayerWithRank? {
    return this._playerCollection.aggregate<PlayerWithRank>(
      match(Player::_id eq id), sort(descending(Player::points)), addFields(
        Field("rank", this._playerCollection.countDocuments(Player::points gt 21))
      )
    ).first()
  }

  override fun updateOne(playerId: String?, points: Number): UpdateResult {
    return this._playerCollection.updateOne(Player::_id eq playerId, setValue(Player::points, points))
  }

  override fun create(pseudo: String): Player? {
    val defaultPoints = 0;
    this._playerCollection.insertOne(Player(pseudo = pseudo, points = defaultPoints));
    return this._playerCollection.findOne { Player::pseudo eq pseudo }
  }

  override fun deleteAll(): DeleteResult {
    return this._playerCollection.deleteMany();
  }
}
