package com.tournament.domain.useCases

import com.tournament.domain.interfaces.repositories.PlayerRepository
import com.tournament.domain.interfaces.useCases.GetOnePlayerUseCase
import com.tournament.domain.models.PlayerWithRank


class GetOnePlayer(private val playerRepository: PlayerRepository) : GetOnePlayerUseCase {
  override fun execute(id: String?): PlayerWithRank? {
    val players = this.playerRepository.getPlayers()
    val index = players.indexOfFirst { player -> player._id == id }

    if (index == -1) return null

    val (_id, pseudo, points) = players[index]

    return PlayerWithRank(pseudo = pseudo, points = points, _id = _id, rank = index + 1)
  }

}
