package com.takehomechallenge.ahmadi.data.api.service

import com.takehomechallenge.ahmadi.data.model.BaseResponse
import com.takehomechallenge.ahmadi.data.model.Character
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyService {
    @GET("character")
    suspend fun getAllCharacter(@Query("name") name: String): BaseResponse<Character>
}