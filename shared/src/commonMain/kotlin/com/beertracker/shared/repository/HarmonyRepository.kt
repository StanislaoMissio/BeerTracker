package com.beertracker.shared.repository

import com.beertracker.shared.model.Harmony
import com.beertracker.shared.model.Style
import com.beertracker.shared.network.BeerTrackerApi

class HarmonyRepository(
    private val api: BeerTrackerApi = BeerTrackerApi()
) {
    suspend fun getHarmonies(style: Style): Result<List<Harmony>> {
        return try {
            Result.success(api.getHarmonies(style))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
