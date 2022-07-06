package Clases

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

class Journal (a: JSONObject) : Serializable {
    var journalID : String
    var journalName : String
    var journalAbbreviation : String
    var journalDescription : String
    var journalPortada : String
    var journalThumbnail : String

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): ArrayList<Volume> {
            val journals : ArrayList<Volume> =  ArrayList<Volume>()
            for(i in 0 until datos.length()) {
                journals.add(Volume(datos.getJSONObject(i)))
            }
            return journals
        }
    }

    init {
        journalID = a.getString("journal_id").toString()
        journalName = a.getString("name").toString()
        journalAbbreviation = a.getString("abbreviation").toString()
        journalDescription = a.getString("description").toString()
        journalPortada = a.getString("portada").toString()
        journalThumbnail = a.getString("journalThumbnail").toString()
    }
}