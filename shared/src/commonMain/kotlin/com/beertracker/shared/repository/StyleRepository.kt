package com.beertracker.shared.repository

import com.beertracker.shared.model.Style
import com.beertracker.shared.network.BeerTrackerApi

class StyleRepository(
    private val api: BeerTrackerApi = BeerTrackerApi()
) {
    suspend fun getAllStyles(): Result<List<Style>> {
        return try {
            Result.success(api.getAllStyles())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
