package com.example.eziketobenna.bakingapp.recipe.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eziketobenna.bakingapp.core.ext.inflate
import com.example.eziketobenna.bakingapp.core.ext.notEmpty
import com.example.eziketobenna.bakingapp.core.imageLoader.ImageLoader
import com.example.eziketobenna.bakingapp.model.RecipeModel
import com.example.eziketobenna.bakingapp.recipe.R
import com.example.eziketobenna.bakingapp.recipe.databinding.ContentMainBinding
import javax.inject.Inject

typealias RecipeClickListener = (RecipeModel) -> Unit

class RecipeAdapter @Inject constructor(private val imageLoader: ImageLoader) :
    ListAdapter<RecipeModel, RecipeAdapter.RecipeViewHolder>(diffUtilCallback) {

    var clickListener: RecipeClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(ContentMainBinding.bind(parent.inflate(R.layout.content_main)))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position), imageLoader, clickListener)
    }

    class RecipeViewHolder(private val binding: ContentMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            model: RecipeModel,
            imageLoader: ImageLoader,
            clickListener: RecipeClickListener?
        ) {
            binding.supportingText.text = model.name
            model.image.notEmpty { url ->
                imageLoader.loadImage(binding.recipeImage, url)
            }

            binding.root.setOnClickListener {
                clickListener?.invoke(model)
            }
        }
    }

    companion object {
        val diffUtilCallback: DiffUtil.ItemCallback<RecipeModel>
            get() = object : DiffUtil.ItemCallback<RecipeModel>() {
                override fun areItemsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
                    return oldItem === newItem && oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: RecipeModel,
                    newItem: RecipeModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
