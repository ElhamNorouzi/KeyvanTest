package com.app.keyvantest.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.keyvantest.api.models.response.MovieListResult
import com.app.keyvantest.databinding.MovieItemBinding

class MoviesAdapter(
    private val context: Context,
    private var onclick: ((MovieListResult) -> Unit)?
) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    init {
        setHasStableIds(true)
    }

    private var moviesResults = mutableListOf<MovieListResult>()

    class MoviesViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: MovieListResult, context: Context, onclick: ((MovieListResult) -> Unit)?) {
            binding.data = item

            binding.rate.rating = item.vote_average.toFloat()
            binding.playersCount.text = "vote counts: ${item.vote_count}"

            binding.item.setOnClickListener {
                onclick.let { click ->
                    click?.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater)
        return MoviesViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(moviesResults[position], context, onclick)
    }

    override fun getItemViewType(position: Int): Int = position
    override fun getItemCount(): Int = moviesResults.size
//    override fun getItemId(position: Int): Long = storeData[position].id

    fun addItem(items: List<MovieListResult>) {
        val discoverDiffUtilCallback = DiscoverDiffUtilCallback(this.moviesResults, items)
        val diffResult = DiffUtil.calculateDiff(discoverDiffUtilCallback)
        this.moviesResults.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateItem(items: List<MovieListResult>) {
        val discoverDiffUtilCallback = DiscoverDiffUtilCallback(this.moviesResults, items)
        val diffResult = DiffUtil.calculateDiff(discoverDiffUtilCallback)
        this.moviesResults = items as ArrayList<MovieListResult>
        diffResult.dispatchUpdatesTo(this)
    }

    class DiscoverDiffUtilCallback(
        private val oldList: List<MovieListResult>,
        private val newList: List<MovieListResult>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == newList[newItemPosition].id


        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}
