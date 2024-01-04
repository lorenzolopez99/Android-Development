package com.codepath.articlesearch
import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Keep
@Serializable
data class SearchNewsResponse(
    @SerialName("results")
    val results: List<Article>?
)
@Keep
@Serializable
data class Article(
    @SerialName("known_for_department")
    val abstract: String?,
    @SerialName("popularity")
    val byline: String?,
    @SerialName("name")
    val headline: String?,
    @SerialName("id")
    val known_for: String?,
    @SerialName("profile_path")
    val multimedia: String?,

) : java.io.Serializable {
    //val known = {known_for?.firstOrNull()}
    val mediaImageUrl = "https://image.tmdb.org/t/p/w500/"+multimedia
}
