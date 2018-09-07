package utils

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser

fun formatJson(s: String): String {
    val gson = GsonBuilder().setPrettyPrinting().create()
    val je = JsonParser().parse(s)
    return gson.toJson(je)
}