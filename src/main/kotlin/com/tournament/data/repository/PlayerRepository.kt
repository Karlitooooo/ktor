package com.tournament.data.repository

import com.mongodb.client.model.Field
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import com.tournament.data.database
import com.tournament.data.model.Player
import com.tournament.data.model.PlayerWithRank
import org.litote.kmongo.*

interface PlayerRepository {
  fun findAllSorted(): List<Player>
  fun findOne(id: String): PlayerWithRank?
  fun updatePoints(playerId: String?, points: Number): UpdateResult
  fun addPlayer(pseudo: String): Player?
  fun deleteAll(): DeleteResult
}

class PlayerRepositoryImpl : PlayerRepository {
  private val _playerCollection = database.getCollection<Player>()

  override fun findAllSorted(): List<Player> {
    return this._playerCollection.aggregate<Player>(
      sort(
        descending(
          Player::points
        )
      )
    ).toList()
  }

  override fun findOne(id: String): PlayerWithRank? {
    return this._playerCollection.aggregate<PlayerWithRank>(
      match(Player::_id eq id), sort(descending(Player::points)), addFields(
        Field("rank", this._playerCollection.countDocuments(Player::points gt 21))
      )
    ).first()
  }

  override fun updatePoints(playerId: String?, points: Number): UpdateResult {
    return this._playerCollection.updateOne(Player::_id eq playerId, setValue(Player::points, points))
  }

  override fun addPlayer(pseudo: String): Player? {
    this._playerCollection.insertOne(Player(pseudo = pseudo, points = 0));
    return this._playerCollection.findOne { Player::pseudo eq pseudo }
  }

  override fun deleteAll(): DeleteResult {
    return this._playerCollection.deleteMany();
  }
}
