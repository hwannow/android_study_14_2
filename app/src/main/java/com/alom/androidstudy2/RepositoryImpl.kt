package com.alom.androidstudy2

import com.alom.androidstudy2.data.Item
import com.alom.androidstudy2.data.Request
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import retrofit2.await
import retrofit2.awaitResponse

class RepositoryImpl: Repository {
    override suspend fun getItem(): Flow<List<Item>> = flow {
        try {
            val response = Retrofit.instance.getItem().awaitResponse()
            if (response.isSuccessful) {
                emit(response.body()?.data ?: emptyList())
            } else {
                emit(emptyList())
            }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    override suspend fun addItem(request: Request): Result<Unit> {
        return try {
            val response = Retrofit.instance.addItem(request).awaitResponse()
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to add Item"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}