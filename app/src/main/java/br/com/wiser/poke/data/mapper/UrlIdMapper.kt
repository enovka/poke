package br.com.wiser.poke.data.mapper

class UrlIdMapper {
    fun map(url: String?): Int? {
        return try {
            url?.split("/")?.findLast { it.isNotBlank() }?.toInt()
        } catch (ex: NumberFormatException){
            null
        }
    }
}