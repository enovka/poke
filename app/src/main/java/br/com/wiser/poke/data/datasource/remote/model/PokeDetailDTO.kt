package br.com.wiser.poke.data.datasource.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResPkmnDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val stats: Array<StatOrdinal>,
    val types: Array<TypeOrdinal>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResPkmnDetail

        if (id != other.id) return false
        if (name != other.name) return false
        if (height != other.height) return false
        if (weight != other.weight) return false
        if (!stats.contentEquals(other.stats)) return false
        if (!types.contentEquals(other.types)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + height.hashCode()
        result = 31 * result + weight.hashCode()
        result = 31 * result + stats.contentHashCode()
        result = 31 * result + types.contentHashCode()
        return result
    }
}

@JsonClass(generateAdapter = true)
data class TypeOrdinal(
        val slot: Int,
        val type: Type
)

@JsonClass(generateAdapter = true)
data class Type(
        val name: String
)

@JsonClass(generateAdapter = true)
data class StatOrdinal(
    val base_stat: Int,
    val stat: Stat
)

@JsonClass(generateAdapter = true)
data class Stat(
    val name: String
)