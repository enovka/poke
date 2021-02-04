package br.com.wiser.poke.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.wiser.poke.data.datasource.local.model.*
import br.com.wiser.poke.data.model.RemotePage

@Dao
interface IPokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpeciesElement(pokemon: List<SpeciesElementTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecies(speciesDataTable: SpeciesDataTable, pokemon: List<PokeTable>, varieties: List<SpeciesVarietyTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(
        detail: List<PokeDetailTable>,
        statTable: List<StatTable>,
        stat: List<PokeStatTable>,
        typeTable: List<TypeTable>,
        pokeType: List<PokeTypeTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemotePageKey(remotePage: RemotePageTable)

    @Query("SELECT * FROM species_data WHERE name = :name LIMIT 1")
    suspend fun getSpeciesByName(name: String): Species?

    @Query("SELECT * FROM detail WHERE name = :name LIMIT 1")
    suspend fun getDetailByName(name: String): PokeDetail?

    @Query("SELECT * FROM species WHERE name LIKE :query COLLATE NOCASE LIMIT :limit OFFSET :offset")
    suspend fun getPaginatedSpeciesByName(query: String, limit: Int, offset: Int): List<SpeciesElementTable>?

    @Query("SELECT * FROM species LIMIT :limit OFFSET :offset")
    suspend fun getPaginatedSpecies(limit: Int, offset: Int): List<SpeciesElementTable>?

    @Query("SELECT * FROM remote_page WHERE listName = :listName LIMIT 1")
    suspend fun getRemotePageKey(listName: String): RemotePage?

    @Query("DELETE FROM remote_page")
    suspend fun deleteRemotePageKeys()

    @Query("DELETE FROM poke_type")
    suspend fun deletePokemonTypes()

    @Query("DELETE FROM poke_stat")
    suspend fun deletePokemonStats()

    @Query("DELETE FROM type")
    suspend fun deleteTypes()

    @Query("DELETE FROM stat")
    suspend fun deleteStats()

    @Query("DELETE FROM species")
    suspend fun deleteSpeciesElements()

    @Query("DELETE FROM species_data")
    suspend fun deleteSpeciesData()

    @Query("DELETE FROM species_variety")
    suspend fun deleteSpeciesVariety()

    @Query("DELETE FROM pokemon")
    suspend fun deletePokemons()

    @Query("DELETE FROM detail")
    suspend fun deleteDetailData()
}

suspend fun IPokeDao.deleteAll(){
    deleteRemotePageKeys()
    deletePokemonTypes()
    deletePokemonStats()
    deleteTypes()
    deleteStats()
    deleteSpeciesElements()
    deleteSpeciesData()
    deleteSpeciesVariety()
    deletePokemons()
    deleteDetailData()
}