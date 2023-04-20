package com.tournament.domain.interfaces.repositories

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import com.tournament.domain.models.Player
import com.tournament.domain.models.PlayerWithRank

interface PlayerRepository {
  fun getPlayers(): List<Player>
  fun getPlayer(id: String): PlayerWithRank?
  fun updatePlayer(playerId: String?, points: Number): UpdateResult
  fun createPlayer(pseudo: String): Player?
  fun deleteAllPlayers(): DeleteResult
}
