package com.hellodiffa.caching_coroutinexworker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hellodiffa.caching_coroutinexworker.databinding.ItemUserBinding
import com.hellodiffa.caching_coroutinexworker.persistance.User
import com.hellodiffa.caching_coroutinexworker.util.loadImage

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val dataSource = mutableListOf<User>()

    fun setDataSource(list: List<User>) {
        dataSource.clear()
        dataSource.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    override fun getItemCount(): Int = dataSource.size

    inner class UserViewHolder(private var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) = binding.apply {
            data = user

            executePendingBindings()
        }
    }
}