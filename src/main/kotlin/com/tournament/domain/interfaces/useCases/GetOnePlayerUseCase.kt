package com.tournament.domain.interfaces.useCases

import com.tournament.domain.models.PlayerWithRank

interface GetOnePlayerUseCase {
  fun execute(id: String?): PlayerWithRank?
}
