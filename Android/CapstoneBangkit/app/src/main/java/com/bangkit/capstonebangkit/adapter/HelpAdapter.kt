package com.bangkit.capstonebangkit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capstonebangkit.databinding.ItemHelpBinding
import com.bangkit.capstonebangkit.model.HelpData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class HelpAdapter (private val helps:ArrayList<HelpData>) :
    RecyclerView.Adapter<HelpAdapter.HelpsHolder>() {

    inner class HelpsHolder(private val binding: ItemHelpBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(help: HelpData) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(help.avatar)
                    .apply(RequestOptions())
                    .into(imgHelp)

                tvAlphabet.text = help.alphabet
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpAdapter.HelpsHolder {
        val binding = ItemHelpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HelpsHolder(binding)
    }

    override fun onBindViewHolder(holder: HelpAdapter.HelpsHolder, position: Int) {
        holder.bind(helps[position])
    }

    override fun getItemCount() = helps.size
}