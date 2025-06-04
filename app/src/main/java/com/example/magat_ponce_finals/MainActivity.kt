// app/src/main/java/com/example/magat_ponce_finals/MainActivity.kt
package com.example.magat_ponce_finals

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.magat_ponce_finals.data.TableSample
import com.example.magat_ponce_finals.databinding.ActivityMainBinding
import com.example.magat_ponce_finals.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSubmit.setOnClickListener {
            val fullName = binding.editTextFullName.text.toString()
            val ageString = binding.editTextAge.text.toString()

            if (fullName.isBlank()) {
                Toast.makeText(this, "Please enter full name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age = ageString.toIntOrNull()
            if (age == null || age <= 0) {
                Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a TableSample object with the data
            val newEntry = TableSample(name = fullName, age = age)

            // Call the API to save the data
            sendDataToDjango(newEntry)
        }
    }

    private fun sendDataToDjango(tableSample: TableSample) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.apiService.createTableSample(tableSample)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val createdSample = response.body()
                        binding.textViewMessage.text = "Success! Saved: ${createdSample?.name}, ${createdSample?.age}"
                        Toast.makeText(this@MainActivity, "Data saved to Django!", Toast.LENGTH_SHORT).show()
                        // Clear inputs
                        binding.editTextFullName.text.clear()
                        binding.editTextAge.text.clear()
                    } else {
                        val errorBody = response.errorBody()?.string()
                        binding.textViewMessage.text = "Error: ${response.code()} - $errorBody"
                        Toast.makeText(this@MainActivity, "Failed to save data: ${response.code()}", Toast.LENGTH_LONG).show()
                        Log.e("DjangoApp", "Error saving data: ${response.code()}, $errorBody")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    binding.textViewMessage.text = "Network Error: ${e.message}"
                    Toast.makeText(this@MainActivity, "Network error: ${e.message}", Toast.LENGTH_LONG).show()
                    Log.e("DjangoApp", "Network error: ${e.message}", e)
                }
            }
        }
    }
}