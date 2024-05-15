package com.example.appcentdeneme2.repository

import com.example.appcentdeneme2.api.RetrofitInstance
import com.example.appcentdeneme2.db.ArticleDatabase
import com.example.appcentdeneme2.models.Article
import retrofit2.http.Query
import java.util.Locale.IsoCountryCode

class NewsRepository(val db:ArticleDatabase) {
    suspend fun getHeadlines(countryCode: String, pageNumber: Int)=
        RetrofitInstance.api.getHeadlines(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int)=
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    //suspend fun upsert(article: Article)= db.getArticleDAO().upsert(article)
    suspend fun upsert(article: Article) {
        article.url?.let { url ->
            val existingArticle = db.getArticleDAO().getArticleByUrl(url)
            if (existingArticle == null) {
                db.getArticleDAO().upsert(article)
            } else {
                // Makale zaten var, bu durumu nasıl ele almak istediğinize bağlı işlemler yapabilirsiniz.
                // Örneğin, mevcut makalenin bazı alanlarını güncelleyebilirsiniz.
            }
        } ?: run {
            // URL null ise, bu durumu handle edebilirsiniz, örneğin bir hata mesajı gösterebilirsiniz.
            println("Article URL is null and cannot be processed")
        }
    }

    fun getFavouriteNews() = db.getArticleDAO().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDAO().deleteArticle(article)

}