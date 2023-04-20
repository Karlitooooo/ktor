package com.tournament.domain.models

import org.bson.types.ObjectId

data class PlayerWithRank(
  var _id: String = ObjectId().toString(),
  val pseudo: String,
  val points: Number,
  val rank: Int
)


