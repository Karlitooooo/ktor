package com.tournament.domain.repositories

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import com.tournament.data.interfaces.PlayerDataSource
import com.tournament.domain.models.Player
import com.tournament.domain.models.PlayerWithRank
import com.tournament.domain.interfaces.repositories.PlayerRepository


class PlayerRepositoryImpl(private val playerDataSource: PlayerDataSource) : PlayerRepository {

  override fun getPlayers(): List<Player> {
    return this.playerDataSource.getAllSorted()
  }

  override fun getPlayer(id: String): PlayerWithRank? {
    return this.playerDataSource.getOne(id)
  }

  override fun updatePlayer(playerId: String?, points: Number): UpdateResult {
    return this.playerDataSource.updateOne(playerId, points)
  }

  override fun createPlayer(pseudo: String): Player? {
    return this.playerDataSource.create(pseudo)
  }

  override fun deleteAllPlayers(): DeleteResult {
    return this.playerDataSource.deleteAll()
  }
}
