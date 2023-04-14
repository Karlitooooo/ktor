package com.tournament.data.model

import org.bson.types.ObjectId

data class PlayerWithRank(
    var _id: String = ObjectId().toString(),
    val pseudo: String,
    val points: Number,
    val rank: Int
)


