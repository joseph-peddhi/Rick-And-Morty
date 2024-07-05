package com.example.rickymorty.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickymorty.models.Character
import com.example.rickymorty.adapters.CharacterAdapter
import com.example.rickymorty.service.RetrofitClient
import com.example.rickymorty.models.RickAndMortyResponse
import com.example.rickymorty.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), CharacterAdapter.OnItemClickedListeners {

    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        characterAdapter = CharacterAdapter()
        binding.recyclerView.adapter = characterAdapter
        fetchCharacters()
    }

    private fun fetchCharacters() {

        RetrofitClient.api.getCharactersList().enqueue(object : Callback<RickAndMortyResponse> {
            override fun onResponse(
                call: Call<RickAndMortyResponse>,
                response: Response<RickAndMortyResponse>
            ) {
                if (response.isSuccessful) {
                    val characters = response.body()?.results
                    characters?.forEach { _ ->
                        characterAdapter.setCharacters(characters, this@MainActivity)
                    }
                } else {
                    showError("Failed to fetch the data")
                }
            }

            override fun onFailure(call: Call<RickAndMortyResponse>, t: Throwable) {
                showError(t.message.toString())
            }
        })
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClicked(character: Character, position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", character.id)
        intent.putExtra("name", character.name)
        intent.putExtra("status", character.status)
        intent.putExtra("gender", character.gender)
        intent.putExtra("type", character.type)
        intent.putExtra("species", character.species)
        intent.putExtra("image", character.image)
        intent.putExtra("pos", position)
        startActivity(intent)
    }
}