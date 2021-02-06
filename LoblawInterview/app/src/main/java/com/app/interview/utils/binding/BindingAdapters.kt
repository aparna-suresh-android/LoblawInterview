package com.app.interview.utils.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.interview.utils.OnListItemUpdated
import com.squareup.picasso.Picasso

@BindingAdapter("listItems")
fun <T> updateListItems(view: RecyclerView, list: List<T>?) {
    list?.let {
        val adapter = view.adapter as OnListItemUpdated<T>
        adapter.updateItems(list)
    }
}

@BindingAdapter("imageUrl", "error", "placeholder")
fun updateImageView(view: ImageView, url: String?, error: Drawable, placeholder: Drawable) {
    url?.let {
        Picasso.get()
            .load(url)
            .error(error)
            .placeholder(placeholder)
            .fit()
            .into(view)
    }
}


