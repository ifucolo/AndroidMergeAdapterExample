package com.ifucolo.mergeadapterexample.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifucolo.mergeadapterexample.LoadState
import com.ifucolo.mergeadapterexample.R
import com.ifucolo.mergeadapterexample.extesions.inflate
import com.ifucolo.mergeadapterexample.extesions.visible
import kotlinx.android.synthetic.main.item_load_state.view.*


class LoadStateAdapter : RecyclerView.Adapter<LoadStateAdapter.LoadStateViewHolder>() {

    var loadState: LoadState = LoadState.Done
        set(value) {
            when (field) {
                value -> {
                    notifyItemChanged(0)
                }
                is LoadState.Loading -> {
                    itemsCount = 0
                    notifyItemRemoved(0)
                }
                is LoadState.Done -> {
                    itemsCount = 1
                    notifyItemInserted(1)
                }
            }

            field = value
        }

    private var itemsCount: Int = 0


    inner class LoadStateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(VIEW_ID)) {
        fun bind(loadState: LoadState) = with(itemView) {
            progress_bar.visible(
                visible = LoadState.Loading == loadState
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LoadStateViewHolder(parent)
    override fun getItemViewType(position: Int): Int = VIEW_ID
    override fun getItemCount(): Int = itemsCount
    override fun onBindViewHolder(holder: LoadStateViewHolder, position: Int) {
        holder.bind(loadState)
    }

    companion object {
        private const val VIEW_ID =
            R.layout.item_load_state
    }
}