package com.example.rickymorty.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rickymorty.R
import com.example.rickymorty.databinding.CharacterDetailsBinding

class DetailsActivity : AppCompatActivity() {

    var id: Int? = null
    var name: String? = null
    var status: String? = null
    private var gender: String? = null
    private var type: String? = null
    private var species: String? = null
    private var image: String? = null
    private var postions: Int? = null
    private var isFavorite: Boolean = false


    private lateinit var binding: CharacterDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CharacterDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var intent = intent

        id = intent?.getIntExtra("id", 0)
        name = intent?.getStringExtra("name")
        status = intent?.getStringExtra("status")
        gender = intent?.getStringExtra("gender")
        type = intent?.getStringExtra("type")
        species = intent?.getStringExtra("species")
        image = intent?.getStringExtra("image")
        image = intent?.getStringExtra("image")
        isFavorite = intent?.getBooleanExtra("remove", false) ?: true
        postions = intent?.getIntExtra("pos", 0)

        binding.textView.text = name
        binding.characterName.text = status
        binding.gender.text = gender
        binding.none.text = applicationContext.getString(R.string.None)
        binding.humam.text = species

        Glide.with(binding.characterImage.context).load(image).into(binding.characterImage)

        updateFavoriteButton()

        binding.textView.setOnClickListener {

            intent = Intent(this@DetailsActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.fevorites.setOnClickListener {
            isFavorite = !isFavorite
            intent = Intent(this@DetailsActivity, FavouriteActivity::class.java)
            if (isFavorite) {
                intent?.putExtra("id", id)
                intent?.putExtra("name", name)
                intent?.putExtra("status", status)
                intent?.putExtra("gender", gender)
                intent?.putExtra("type", type)
                intent?.putExtra("species", species)
                intent?.putExtra("image", image)
            } else {
                intent?.putExtra("isRemoves", true)
                intent?.putExtra("pos", postions)
            }
            startActivity(intent)

            updateFavoriteButton()

        }

    }

    private fun updateFavoriteButton() {
        if (isFavorite) {
            binding.fevorites.text = applicationContext.getString(R.string.Remove)
        } else {
            binding.fevorites.text = applicationContext.getString(R.string.Add)
        }

    }
}