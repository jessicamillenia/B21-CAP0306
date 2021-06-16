package com.bangkit.capstonebangkit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capstonebangkit.model.UserData
import com.bangkit.capstonebangkit.databinding.ItemUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UserAdapter (private val users:ArrayList<UserData>) :
    RecyclerView.Adapter<UserAdapter.UsersHolder>() {

    inner class UsersHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserData) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .apply(RequestOptions())
                    .into(imgUser)

                tvName.text = user.name
                tvCourse.text = user.course
                tvUniversity.text = user.university
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size
}