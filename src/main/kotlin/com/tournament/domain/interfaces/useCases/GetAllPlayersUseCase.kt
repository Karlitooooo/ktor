package com.tournament.domain.interfaces.useCases

import com.tournament.domain.models.Player

interface GetAllPlayersUseCase {
  fun execute(): List<Player>
}
