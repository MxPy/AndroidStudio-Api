package com.example.jokeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapp.R
import com.example.jokeapp.databinding.CategoryListItemBinding
import com.example.rickandmortyapp.model.CharactersResponse

//// Adapter for the list of categories using ListAdapter class for simplified list updates
//class CategoriesAdapter: ListAdapter<String, CategoriesAdapter.ViewHolder>(CategoriesDiffCallback){
//    // ViewHolder class for the adapter representing the individual items in the list
//    inner class ViewHolder(private val binding: CategoryListItemBinding): RecyclerView.ViewHolder(binding.root){
//        // function to bind the data to the ViewHolder and set the click listener
//        fun bind(category: String) {
//// Treat the favorites category differently by
//// hiding the abbreviation and showing the favorite icon
//            if (category == getString(binding.root.context, R.string.favorites)) {
//                binding.jokeCategoryAbbr.visibility = View.GONE
//                binding.favCategoryImg.visibility = View.VISIBLE
//            } else {
//                binding.jokeCategoryAbbr.visibility = View.VISIBLE
//                binding.favCategoryImg.visibility = View.GONE
//            }
//// Set the first two letters of the category as the abbreviation
//            binding.jokeCategoryAbbr.text = category.substring(0, 2)
//// Set the category name
//            binding.jokeCategory.text = category
//// Set the click listener for the item
//            binding.root.setOnClickListener {
//                onCategoryClickListener?.onCategoryClick(category)
//            }
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = CategoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)
//    }
//    // Bind the data to the ViewHolder
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val category = getItem(position)
//        holder.bind(category)
//    }
//    // Define a listener for the category click event
//    private var onCategoryClickListener: CategoryClickListener? = null
//    // Method to set the listener
//    fun setOnCategoryClickListener(listener: CategoryClickListener){
//        onCategoryClickListener = listener
//    }
//    // Definition of the interface for the category click listener
//    interface CategoryClickListener {
//        fun onCategoryClick(category: String)
//    }
//}
//// DiffCallback for the ListAdapter to calculate the difference between the old and new items
//// to handle updates efficiently
//object CategoriesDiffCallback: DiffUtil.ItemCallback<String>() {
//    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
//        return oldItem == newItem
//    }
//
//    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
//        return oldItem == newItem
//    }
//
//}

class CharactersAdapter : ListAdapter<CharactersResponse.Character, CharactersAdapter.ViewHolder>(CharactersDiffCallback) {

    inner class ViewHolder(private val binding: CategoryListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharactersResponse.Character) {
            binding.jokeCategory.text = character.name

            binding.root.setOnClickListener {
                onCharacterClickListener?.onCharacterClick(character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    private var onCharacterClickListener: CharacterClickListener? = null

    fun setOnCharacterClickListener(listener: CharacterClickListener) {
        onCharacterClickListener = listener
    }

    interface CharacterClickListener {
        fun onCharacterClick(character: CharactersResponse.Character)
    }
}

object CharactersDiffCallback : DiffUtil.ItemCallback<CharactersResponse.Character>() {
    override fun areItemsTheSame(oldItem: CharactersResponse.Character, newItem: CharactersResponse.Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharactersResponse.Character, newItem: CharactersResponse.Character): Boolean {
        return oldItem == newItem
    }
}