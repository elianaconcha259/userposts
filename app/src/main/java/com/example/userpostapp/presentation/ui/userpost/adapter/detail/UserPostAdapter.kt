package com.example.userpostapp.presentation.ui.userpost.adapter.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.userpostapp.R
import com.example.userpostapp.databinding.ItemUserPostBinding
import com.example.userpostapp.domain.model.PostModel

class UserPostAdapter(
    private val widthCard: Int = CoordinatorLayout.LayoutParams.MATCH_PARENT) : ListAdapter<PostModel, UserPostAdapter.RecentPostViewHolder>(PostDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentPostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_post, parent, false)
        return RecentPostViewHolder(view, widthCard)
    }

    override fun onBindViewHolder(holder: RecentPostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RecentPostViewHolder(
        view: View,
        private val widthCard: Int
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemUserPostBinding.bind(view)
        private var currentPost: PostModel? = null

        fun bind(post: PostModel) {
            currentPost = post
            binding.root.layoutParams = binding.root.layoutParams.apply {
                width = widthCard
            }
            binding.tvTitle.text = post.title
            binding.tvBody.text = post.body
        }
    }
}

object PostDiffCallback : DiffUtil.ItemCallback<PostModel>() {
    override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
        return oldItem == newItem
    }
}
