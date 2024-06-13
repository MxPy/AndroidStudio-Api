package com.example.jokeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapp.R
import com.example.jokeapp.databinding.JokeListItemBinding
import com.example.jokeapp.extensions.substringBeforeNthDelimiter
import com.example.jokeapp.model.EpisodeResponse
import com.example.jokeapp.model.Joke

class JokesAdapter(val listener: JokeClickListener): ListAdapter<EpisodeResponse, JokesAdapter.ViewHolder>(JokesDiffCallback) {

    inner class ViewHolder(private val binding: JokeListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: EpisodeResponse) {
            binding.jokeCategoryAbbr.text = episode.episode.take(3)
            binding.jokeShort.text = "${episode.name.take(15)}..."

            // Usuwamy obsługę flag, ponieważ EpisodeResponse ich nie ma

            binding.root.setOnClickListener {
                listener.onJokeClick(episode)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = JokeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val episode = getItem(position)
        holder.bind(episode)
    }

    interface JokeClickListener {
        fun onJokeClick(episode: EpisodeResponse)
    }
}

object JokesDiffCallback: DiffUtil.ItemCallback<EpisodeResponse>() {
    override fun areItemsTheSame(oldItem: EpisodeResponse, newItem: EpisodeResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EpisodeResponse, newItem: EpisodeResponse): Boolean {
        return oldItem == newItem
    }
}