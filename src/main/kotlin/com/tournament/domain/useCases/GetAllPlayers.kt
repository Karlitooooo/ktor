package com.tournament.domain.useCases

import com.tournament.domain.interfaces.repositories.PlayerRepository
import com.tournament.domain.interfaces.useCases.GetAllPlayersUseCase
import com.tournament.domain.models.Player

class GetAllPlayers(private val playerRepository: PlayerRepository) : GetAllPlayersUseCase {
  override fun execute(): List<Player> {
    return this.playerRepository.getPlayers()
  }
}
