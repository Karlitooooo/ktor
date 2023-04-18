package com.tournament.data.model

import org.bson.types.ObjectId

data class Player(
  var _id: String = ObjectId().toString(),
  val pseudo: String,
  val points: Number,
)


