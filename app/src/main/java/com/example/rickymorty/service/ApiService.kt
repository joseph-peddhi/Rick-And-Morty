package com.example.rickymorty.service

import com.example.rickymorty.models.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    fun getCharactersList(): Call<RickAndMortyResponse>
}