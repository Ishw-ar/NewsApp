package com.varsha.newsapp.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.varsha.newsapp.databinding.ItemNewsBinding
import com.varsha.newsapp.model.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Article) {
            binding.tvTitle.text = data.title
            if (data.urlToImage != null) {
                binding.ivArticleImage.visibility = View.VISIBLE
                binding.ivArticleImage.load(
                    data.urlToImage
                )

            } else {
                binding.ivArticleImage.visibility = View.GONE
            }
            binding.tvDescription.text = data.description
            binding.tvPublishedAt.text = data.publishedAt
            binding.tvSource.text = data.source?.name

            binding.cardView.setOnClickListener {
                onItemClickListener?.let { it(data) }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}
