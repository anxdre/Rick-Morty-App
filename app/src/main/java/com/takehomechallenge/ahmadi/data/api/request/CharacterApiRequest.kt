package com.takehomechallenge.ahmadi.data.api.request

import com.takehomechallenge.ahmadi.data.api.ApiHelper
import com.takehomechallenge.ahmadi.data.api.service.RickMortyService
import com.takehomechallenge.ahmadi.data.model.BaseResponse
import com.takehomechallenge.ahmadi.data.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterApiRequest(private val service: RickMortyService) :
    ApiHelper<BaseResponse<Character>, Unit> {

    private var query = hashMapOf<String, String>()

    override fun exec(): Flow<BaseResponse<Character>> {
        return flow { emit(service.getAllCharacter(query)) }
    }

    override fun buildQuery(hashMap: HashMap<String, String>) {
        query.clear()
        query.putAll(hashMap)
    }
}