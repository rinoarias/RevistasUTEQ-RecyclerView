package Clases

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Author (a: JSONObject) {
    var nombres : String
    var filiacion : String
    var email : String

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray) : ArrayList<Author> {
            val authors : ArrayList<Author> = ArrayList<Author>()
            for(i in 0 until datos.length()){
                authors.add(Author(datos.getJSONObject(i)))
            }
            return authors
        }
    }

    init {
        nombres = a.getString("nombres")
        filiacion = a.getString("filiacion")
        email = a.getString("email")
    }
}
