package com.example.magat_ponce_finals.network

import retrofit2.Retrofit // 2. Import Retrofit library
import retrofit2.converter.gson.GsonConverterFactory // 3. Import GSON converter for JSON

/**
 * Singleton object for providing a configured Retrofit instance. // 4. KDoc for the object
 * This ensures that only one Retrofit client is created and used throughout the app,
 * which is efficient for network operations.
 */
object RetrofitClient { // 5. Object Declaration - Singleton Pattern

    /**
     * The base URL for your Django backend API. // 6. KDoc for BASE_URL
     *
     * IMPORTANT:
     * - If your Django server is running on your local machine, and you are using an Android Emulator,
     * use "http://10.0.2.2:8000/" to access your host machine's localhost.
     * - If you are using a physical Android device, you must replace "10.0.2.2" with your
     * local machine's actual IP address (e.g., "http://192.168.1.XXX:8000/").
     * - Ensure your Django development server is running (python manage.py runserver 0.0.0.0:8000).
     */
    private const val BASE_URL = "http://10.0.2.2:8000/" // 7. BASE_URL Constant

    /**
     * Lazily initialized instance of ApiService. // 8. KDoc for apiService
     * This means the ApiService will only be created when it's first accessed.
     * It uses Retrofit to build the service with the defined BASE_URL and GSON for JSON conversion.
     */
    val apiService: ApiService by lazy { // 9. Lazily Initialized Property (apiService)
        Retrofit.Builder() // 10. Start building Retrofit instance
            .baseUrl(BASE_URL) // 11. Set the base URL
            .addConverterFactory(GsonConverterFactory.create()) // 12. Add JSON Converter
            .build() // 13. Build the Retrofit instance
            .create(ApiService::class.java) // 14. Create API Service Implementation
    }
}