package com.takehomechallenge.ahmadi.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takehomechallenge.ahmadi.data.api.ApiFactory
import com.takehomechallenge.ahmadi.data.api.request.CharacterApiRequest
import com.takehomechallenge.ahmadi.data.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _listOfCharacter: MutableLiveData<List<Character>> = MutableLiveData()
    val listOfCharacter: LiveData<List<Character>> get() = _listOfCharacter

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    private val apiRequest = CharacterApiRequest(ApiFactory.getRickMortyService())

    private var page = 1

    var query:String = ""

    val warnMessage = MutableLiveData("")

    fun fetchNextPage() {
        page++
        viewModelScope.launch { fetchData() }
    }

    fun fetchRefresh() {
        page = 1
        viewModelScope.launch { fetchData() }
    }


    private suspend fun fetchData() {
        apiRequest.buildQuery(hashMapOf(Pair("name",query),Pair("page", page.toString())))
        apiRequest.exec()
            .flowOn(Dispatchers.IO)
            .onStart { _loading.value = true }
            .onCompletion { _loading.value = false }
            .catch { _loading.value = false
                if (it.message.toString().contains("404")){
                    warnMessage.value = "Item Not Found"
                    return@catch
                }
                warnMessage.value = "Unexpected Error"
            }
            .collect {
                _listOfCharacter.postValue(it.results)
            }
    }
}