package com.tournament.domain.interfaces.useCases

import com.tournament.domain.models.Player

interface CreatePlayerUseCase {
  fun execute(pseudo: String): Player?
}
