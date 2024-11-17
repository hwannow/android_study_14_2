package com.alom.androidstudy2

import com.alom.androidstudy2.data.Item
import com.alom.androidstudy2.data.Request
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getItem(): Flow<List<Item>>
    suspend fun addItem(request: Request): Result<Unit>
}