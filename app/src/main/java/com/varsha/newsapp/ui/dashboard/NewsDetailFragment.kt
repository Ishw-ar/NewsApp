package com.varsha.newsapp.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.varsha.newsapp.R
import com.varsha.newsapp.databinding.FragmentNewsDetailBinding
import com.varsha.newsapp.model.Article
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsDetailFragment : Fragment() {
    private lateinit var binding: FragmentNewsDetailBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsDetailBinding.inflate(layoutInflater, container, false)
        val article = arguments?.getParcelable<Article>("article")
        setData(article)
        return binding.root
    }

    private fun setData(article: Article?) {
        if (article?.isLike == true) {
            binding.ivFavoriteNews.load(
                R.drawable.ic_favorite_selected
            )
        } else {
            binding.ivFavoriteNews.load(
                R.drawable.ic_favorite_unselected
            )
        }

        binding.tvTitle.text = article?.title ?: ""
        if (article?.urlToImage != null) {
            binding.ivArticleImage.visibility = View.VISIBLE
            binding.ivArticleImage.load(
                article.urlToImage
            )

        } else {
            binding.ivArticleImage.visibility = View.GONE
        }
        binding.tvDescription.text = article?.description ?: ""
        binding.tvContent.text = article?.content ?: ""
        binding.tvPublishedAt.text = article?.publishedAt ?: ""
        binding.tvSource.text = article?.source?.name ?: ""
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            NewsDetailFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}