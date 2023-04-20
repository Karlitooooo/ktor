package com.tournament.domain.useCases

import com.tournament.domain.interfaces.repositories.PlayerRepository
import com.tournament.domain.interfaces.useCases.CreatePlayerUseCase
import com.tournament.domain.models.Player

class CreatePlayer(private val playerRepository: PlayerRepository) : CreatePlayerUseCase {
  override fun execute(pseudo: String): Player? {
    return playerRepository.createPlayer(pseudo)
  }
}
