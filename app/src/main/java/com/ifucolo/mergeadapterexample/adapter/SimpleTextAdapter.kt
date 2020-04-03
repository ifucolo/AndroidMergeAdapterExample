package com.ifucolo.mergeadapterexample.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifucolo.mergeadapterexample.R
import com.ifucolo.mergeadapterexample.extesions.inflate
import kotlinx.android.synthetic.main.item_text.view.*

class SimpleTextAdapter(
): RecyclerView.Adapter<SimpleTextAdapter.SimpleTextViewHolder>() {

    var list: ArrayList<String> = arrayListOf()
        set(value) {
            val oldSize = field.size
            val newSize = value.size
            field.addAll(value)

            notifyItemRangeInserted(oldSize, newSize)
        }

    inner class SimpleTextViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(VIEW_ID)) {

        fun bind(data: String) = with(itemView) {
            txt_simple_text.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SimpleTextViewHolder(parent)
    override fun getItemViewType(position: Int): Int = VIEW_ID
    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: SimpleTextViewHolder, position: Int) {
        holder.bind(list[position])
    }

    companion object {
        private const val VIEW_ID =
            R.layout.item_text
    }
}