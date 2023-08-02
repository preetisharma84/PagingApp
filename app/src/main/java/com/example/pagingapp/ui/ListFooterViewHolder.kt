package com.example.pagingapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingapp.R
import com.example.pagingapp.model.State
import kotlinx.android.synthetic.main.adapter_item_footer.view.*

class ListFooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(status: State?) {
        itemView.progressBarFooter.visibility =
            if (status == State.LOADING) View.VISIBLE else View.INVISIBLE
        itemView.tvErrorFooter.visibility =
            if (status == State.ERROR) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_item_footer, parent, false)
            view.tvErrorFooter.setOnClickListener { retry() }
            return ListFooterViewHolder(view)
        }
    }
}