package com.tournament.domain.useCases

import com.mongodb.client.result.UpdateResult
import com.tournament.domain.interfaces.repositories.PlayerRepository
import com.tournament.domain.interfaces.useCases.UpdateOnePlayerUseCase

class UpdateOnePlayer(private val playerRepository: PlayerRepository) : UpdateOnePlayerUseCase {
  override fun execute(playerId: String?, points: Number): UpdateResult {
    return playerRepository.updatePlayer(playerId, points)
  }
}
