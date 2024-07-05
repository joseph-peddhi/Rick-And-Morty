package com.example.rickymorty.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickymorty.models.Character
import com.example.rickymorty.R

class CharacterAdapter :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    private var characterList: List<Character>? = null
    private var listeners: OnItemClickedListeners? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList?.get(position)
        holder.name.text = character?.name
        holder.status.text = "Status: ${character?.status}"
        Glide.with(holder.itemView.context).load(character?.image).into(holder.image)
        holder.cardview.setOnClickListener {
            character?.let { it1 -> listeners?.onItemClicked(it1, position) }
        }
    }

    override fun getItemCount(): Int = characterList?.size ?: 0

    fun setCharacters(characters: List<Character>, onlisteners: OnItemClickedListeners) {
        this.characterList = characters
        this.listeners = onlisteners
        notifyDataSetChanged()
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.character_name)
        val cardview: CardView = itemView.findViewById(R.id.cardview)
        val status: TextView = itemView.findViewById(R.id.character_status)
        val image: ImageView = itemView.findViewById(R.id.character_image)
    }

    interface OnItemClickedListeners {
        fun onItemClicked(character: Character, position: Int)
    }
}
