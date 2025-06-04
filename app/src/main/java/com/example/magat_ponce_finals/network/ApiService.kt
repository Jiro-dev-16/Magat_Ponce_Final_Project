package com.example.magat_ponce_finals.network

import com.example.magat_ponce_finals.data.TableSample // Import your TableSample data class
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Retrofit API service interface for interacting with the Django backend.
 * Defines the HTTP methods and endpoints for the application's network requests.
 */
interface ApiService {

    /**
     * Sends a POST request to the Django API to create a new TableSample entry.
     * The endpoint "api/tablesample/" should match the URL configured in your
     * Django project's urls.py for TableSampleCreateAPIView.
     *
     * @param tableSample The TableSample object containing the name and age to be sent.
     * @return A Retrofit Response object containing the created TableSample on success.
     */
    @POST("api/tablesample/")
    suspend fun createTableSample(@Body tableSample: TableSample): Response<TableSample>
}