package com.takehomechallenge.ahmadi.data.api.request

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.takehomechallenge.ahmadi.data.api.ApiFactory
import com.takehomechallenge.ahmadi.data.model.BaseResponse
import com.takehomechallenge.ahmadi.data.model.Character
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertThrows


class CharacterApiRequestTest {
    private lateinit var apiRequest: CharacterApiRequest
    private val dataNoQuery: MutableLiveData<BaseResponse<Character>> =
        MutableLiveData<BaseResponse<Character>>()
    private val dataWithQuery: MutableLiveData<BaseResponse<Character>> =
        MutableLiveData<BaseResponse<Character>>()

    @Before
    fun setUp() {
        apiRequest = CharacterApiRequest(ApiFactory.getRickMortyService())
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Test
    fun exec() {
        runTest {
            apiRequest.exec().collect {
                dataNoQuery.postValue(it)
                assert(dataNoQuery.value?.results!!.isNotEmpty())
            }
        }
    }

    @Test
    fun buildQuery() {
        assertThrows<NoSuchFieldException> { apiRequest.buildQuery(hashMapOf()) }
    }

    @Test
    fun search_custom_character() {
        runTest {
            apiRequest.buildQuery(hashMapOf(Pair("query", "Morty")))
            apiRequest.exec().collect {
                dataWithQuery.postValue(it)
                assert(dataWithQuery.value?.info?.next == "https://rickandmortyapi.com/api/character?page=2&name=Morty")
            }
        }
    }
}