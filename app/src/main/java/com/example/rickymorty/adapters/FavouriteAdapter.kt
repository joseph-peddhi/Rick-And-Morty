package com.example.rickymorty.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickymorty.models.FavoriteCharacter
import com.example.rickymorty.R

class FavouriteAdapter(
    characterLists: List<FavoriteCharacter>,
    private var listeners: OnItemClickedListeners
) :
    RecyclerView.Adapter<FavouriteAdapter.FavoriteViewHolder>() {

    private val characterList: ArrayList<FavoriteCharacter> = ArrayList(characterLists.distinct())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return FavoriteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val character = characterList[position]
        holder.name.text = character.name
        Glide.with(holder.itemView.context).load(character.image).into(holder.image)
        holder.favorites.visibility = View.VISIBLE
        holder.image.setOnClickListener {
            character.let { it1 ->
                listeners.itemClicked(
                    it1,
                    position
                )
            }
        }
        holder.favorites.setOnClickListener {
            removeData(position)
            listeners.removeData(character)
        }
    }

    fun removeData(position: Int) {
        characterList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = characterList.size

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.character_name)
        val status: TextView = itemView.findViewById(R.id.character_status)
        val image: ImageView = itemView.findViewById(R.id.character_image)
        val favorites: TextView = itemView.findViewById(R.id.favorites)
    }

    interface OnItemClickedListeners {
        fun itemClicked(character: FavoriteCharacter, position: Int)
        fun removeData(character: FavoriteCharacter)
    }
}