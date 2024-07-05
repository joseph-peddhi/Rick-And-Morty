package com.example.rickymorty.models

import androidx.room.PrimaryKey

data class FavoriteCharacter(
    @PrimaryKey val id: Int?,
    val name: String?,
    val image: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,

    )
