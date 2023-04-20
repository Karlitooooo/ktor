package com.tournament.data.interfaces

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import com.tournament.domain.models.Player
import com.tournament.domain.models.PlayerWithRank

interface PlayerDataSource {
  fun getAllSorted(): List<Player>
  fun getOne(id: String): PlayerWithRank?
  fun updateOne(playerId: String?, points: Number): UpdateResult
  fun create(pseudo: String): Player?
  fun deleteAll(): DeleteResult
}

