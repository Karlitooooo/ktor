package com.tournament.domain.interfaces.useCases

import com.mongodb.client.result.DeleteResult

interface DeleteAllPlayersUseCase {
  fun execute(): DeleteResult
}
