package com.tournament.domain.interfaces.useCases

import com.mongodb.client.result.UpdateResult

interface UpdateOnePlayerUseCase {
  fun execute(playerId: String?, points: Number): UpdateResult
}
