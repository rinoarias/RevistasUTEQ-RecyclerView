package Clases

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

class Volume (a: JSONObject) : Serializable {

    var issueID : String
    var volume : String
    var number : String
    var year : String
    var datePublished : String
    var title : String
    var doi : String
    var cover : String

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): ArrayList<Volume> {
            val volumes : ArrayList<Volume> =  ArrayList<Volume>()
            for(i in 0 until datos.length()) {
                volumes.add(Volume(datos.getJSONObject(i)))
            }
            return volumes
        }
    }

    init {
        issueID = a.getString("issue_id").toString()
        volume = a.getString("volume").toString()
        number = a.getString("number").toString()
        year = a.getString("year").toString()
        datePublished = a.getString("date_published").toString()
        title = a.getString("title").toString()
        doi = a.getString("doi").toString()
        cover = a.getString("cover").toString()
    }
}