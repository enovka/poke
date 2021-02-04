package br.com.wiser.poke.data.mapper


class SpriteMapper {
    fun map(url: String?): String? {
        return try {
            map(url?.split("/")?.findLast { it.isNotBlank() }?.toInt())
        } catch (ex: NumberFormatException) {
            null
        }
    }

    fun map(index: Int?): String? {
        return index?.let {
            "https://raw.githubusercontent.com/PokeAPI/sprites/368eb1ed07979ac00d6b91d2a5c1baaaf0e886bb/sprites/pokemon/other/official-artwork/${it}.png"
        }
    }
}