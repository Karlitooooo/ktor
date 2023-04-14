package com.tournament.data.repository

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.InsertOneResult
import com.mongodb.client.result.UpdateResult
import com.tournament.data.database
import com.tournament.data.model.Player
import org.litote.kmongo.*

class PlayerRepository {
    private val _playerCollection = database.getCollection<Player>()

     fun findAllSorted(): List<Player> {
        return this._playerCollection.aggregate<Player>(
            sort(
                descending(
                    Player::points
                )
            )
        ).toList()
    }
    fun updatePoints(playerId: String? ,points: Number): UpdateResult{
        return this._playerCollection.updateOne(Player::_id eq playerId, setValue(Player::points, points))
    }
    fun addPlayer(pseudo: String): InsertOneResult {
        return this._playerCollection.insertOne(Player(pseudo = pseudo, points = 0));
    }

    fun deleteAll(): DeleteResult {
        return this._playerCollection.deleteMany();
    }
}