package com.tournament.domain.models

import org.bson.types.ObjectId

data class Player(
  var _id: String = ObjectId().toString(),
  val pseudo: String,
  val points: Number,
)


