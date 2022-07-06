package Clases

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

class Article(a: JSONObject) : Serializable {
    var section : String
    var publicationID : String
    var title : String
    var doi : String
    var abstract : String
    var datePublished : String
    var submissionID : String
    var sectionID : String
    var seq : String
    var galeys : String? = null
    var keywords : ArrayList<String>
    var authors : ArrayList<Author>

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): ArrayList<Article> {
            val articles : ArrayList<Article> =  ArrayList<Article>()
            for(i in 0 until datos.length()) {
                articles.add(Article(datos.getJSONObject(i)))
            }
            return articles
        }
    }

    init {
        section = a.getString("section").toString()
        publicationID = a.getString("publication_id").toString()
        title = a.getString("title").toString()
        doi = a.getString("doi").toString()
        abstract = a.getString("abstract").toString()
        datePublished = a.getString("date_published").toString()
        submissionID = a.getString("submission_id").toString()
        sectionID = a.getString("section_id").toString()
        seq = a.getString("seq").toString()
        galeys = a.getString("galeys")!!.toString()
        keywords = ArrayList<String>()
        val arrayKeywords = a.getJSONArray("keywords")
        for (i in 0 until arrayKeywords.length()){
            keywords.add(arrayKeywords.getJSONObject(i).getString("keyword"))
        }
        authors = ArrayList<Author>()
        authors = Author.JsonObjectsBuild(a.getJSONArray("authors"))
    }

}