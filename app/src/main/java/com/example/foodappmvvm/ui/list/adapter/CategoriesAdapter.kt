package com.example.foodappmvvm.ui.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foodappmvvm.R
import com.example.foodappmvvm.data.model.ResponseCategoriesList
import com.example.foodappmvvm.databinding.ItemCategoriesBinding
import javax.inject.Inject

class CategoriesAdapter @Inject constructor() : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    //Binding
    private lateinit var binding: ItemCategoriesBinding
    private var categoryList = emptyList<ResponseCategoriesList.Category>()
    private var onItemClickListener: ((ResponseCategoriesList.Category) -> Unit)? = null
    private var selectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //getItem from PagingDataAdapter
        holder.bind(categoryList[position])
        //Not duplicate items
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = categoryList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        fun bind(item: ResponseCategoriesList.Category) {
            binding.apply {
                itemCategoriesImg.load(item.strCategoryThumb) {
                    crossfade(true)
                    crossfade(500)
                }
                itemCategoriesTxt.text = item.strCategory
                //Click
                root.setOnClickListener {
                    selectedItem = adapterPosition
                    notifyDataSetChanged()
                    onItemClickListener?.let {
                        it(item)
                    }
                }
                //Change color
                if (selectedItem == adapterPosition) {
                    root.setBackgroundResource(R.drawable.bg_rounded_selcted)
                } else {
                    root.setBackgroundResource(R.drawable.bg_rounded_white)
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (ResponseCategoriesList.Category) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<ResponseCategoriesList.Category>) {
        val moviesDiffUtil = MoviesDiffUtils(categoryList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        categoryList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class MoviesDiffUtils(
        private val oldItem: List<ResponseCategoriesList.Category>,
        private val newItem: List<ResponseCategoriesList.Category>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }
    }
}