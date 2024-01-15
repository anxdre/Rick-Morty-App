package com.takehomechallenge.ahmadi.data.api

import kotlinx.coroutines.flow.Flow

interface ApiHelper<T,Q> {
    fun exec(): Flow<T>
    fun buildQuery(hashMap: HashMap<String,String>): Q
}