package com.example.userpostapp.presentation.ui.userpost.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.R
import com.example.userpostapp.databinding.ItemUserBinding

class UserAdapter(
    private val widthCard: Int = CoordinatorLayout.LayoutParams.MATCH_PARENT,
    private val onItemClick: (UserModel) -> Unit
) : ListAdapter<UserModel, UserAdapter.RecenUserViewHolder>(UserDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecenUserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return RecenUserViewHolder(view, widthCard, onItemClick)
    }

    override fun onBindViewHolder(holder: RecenUserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RecenUserViewHolder(
        view: View,
        private val widthCard: Int,
        private val onClickProduct: (UserModel) -> Unit

    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemUserBinding.bind(view)
        private var currentUser: UserModel? = null

        init {
            view.setOnClickListener {
                currentUser?.let(onClickProduct)
            }
        }

        fun bind(user: UserModel) {
            currentUser = user
            binding.root.layoutParams = binding.root.layoutParams.apply {
                width = widthCard
            }
            binding.tvName.text = user.name
            binding.tvEmail.text = user.email
            binding.tvPhone.text = user.phone
        }
    }
}

object UserDiffCallback : DiffUtil.ItemCallback<UserModel>() {
    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem == newItem
    }
}
