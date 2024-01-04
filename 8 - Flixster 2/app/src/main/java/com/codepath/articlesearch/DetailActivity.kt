package com.codepath.articlesearch

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var bylineTextView: TextView
    private lateinit var abstractTextView: TextView
    private lateinit var knownTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mediaImageView = findViewById(R.id.mediaImage)
        titleTextView = findViewById(R.id.mediaTitle)
        bylineTextView = findViewById(R.id.mediaByline)
        abstractTextView = findViewById(R.id.mediaAbstract)
        knownTextView = findViewById(R.id.mediaKnown)

        val article = intent.getSerializableExtra(ARTICLE_EXTRA) as Article

        titleTextView.text = article.headline
        bylineTextView.text = "Popularity:"+article.byline
        abstractTextView.text = "Known For:"+article.abstract
        knownTextView.text = "ID:"+article.known_for

        Glide.with(this)
            .load(article.mediaImageUrl)
            .into(mediaImageView)
    }
}