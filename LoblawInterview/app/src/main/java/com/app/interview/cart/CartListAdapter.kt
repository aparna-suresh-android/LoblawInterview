package com.app.interview.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.interview.databinding.CartListItemBinding
import com.app.interview.repository.entity.Product
import com.app.interview.utils.OnListItemUpdated

class CartListAdapter(val mClickListener: OnProductClick) :
    RecyclerView.Adapter<CartListAdapter.ViewHolder>(),
    OnListItemUpdated<Product> {

    private var items: List<Product> = ArrayList()

    inner class ViewHolder(val mBinding: CartListItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(prdt: Product) {
            mBinding.product = prdt
            mBinding.root.setOnClickListener {
                mClickListener.onClick(items.get(bindingAdapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CartListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun updateItems(updatedItems: List<Product>) {
        items = updatedItems
        notifyDataSetChanged()
    }



    interface OnProductClick {
        fun onClick(item: Product)
    }

}