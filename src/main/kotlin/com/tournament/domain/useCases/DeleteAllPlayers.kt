package com.tournament.domain.useCases

import com.mongodb.client.result.DeleteResult
import com.tournament.domain.interfaces.repositories.PlayerRepository
import com.tournament.domain.interfaces.useCases.DeleteAllPlayersUseCase

class DeleteAllPlayers(private val playerRepository: PlayerRepository) : DeleteAllPlayersUseCase {
  override fun execute(): DeleteResult {
    return playerRepository.deleteAllPlayers()
  }
}
