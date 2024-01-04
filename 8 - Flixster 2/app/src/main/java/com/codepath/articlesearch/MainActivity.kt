package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
private const val API_KEY = "5c263cc1edef4f9ae8b479e9e4999603"
class MainActivity : AppCompatActivity() {
    private lateinit var articlesRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val articles = mutableListOf<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        articlesRecyclerView = findViewById(R.id.articles)
        val articleAdapter = ArticleAdapter(this, articles)
        articlesRecyclerView.adapter = articleAdapter

        articlesRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            articlesRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        val client = AsyncHttpClient()
        val params = RequestParams()
        val url = "https://api.themoviedb.org/3/person/popular"
        params["api_key"] = API_KEY
        client[
            url,
            params,
            object: JsonHttpResponseHandler()
            {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JSON
                ) {
                    Log.i(TAG, "Successfully fetched articles: $json")
                    Log.d("debuger",json.jsonObject.toString())
                    try {
                        val parsedJson = createJson().decodeFromString(
                            SearchNewsResponse.serializer(),
                            json.jsonObject.toString()
                        )
                        parsedJson.results?.let { list ->
                            articles.addAll(list)
                        }
                        parsedJson.results?.let { list ->
                            articles.addAll(list)
                        // Reload the screen
                        articleAdapter.notifyDataSetChanged()
                        }
                    } catch (e: JSONException) {
                        Log.e(TAG, "Exception: $e")
                    }
                }
                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    response: String?,
                    throwable: Throwable?
                ){
                    Log.e(TAG, "Failed to fetch articles: $statusCode")
                }
            }]
    }
}