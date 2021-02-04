package br.com.wiser.poke.data.datasource.remote.mapper
import br.com.wiser.poke.data.datasource.remote.model.ResPkmnSpecies
import br.com.wiser.poke.data.model.SpecieModel
import br.com.wiser.poke.data.model.VarietyModel

class RemotePokeSpecieMapperRemote : RemoteResponseMapper<ResPkmnSpecies, SpecieModel> {

    override fun map(response: ResPkmnSpecies): SpecieModel {
        val remotePokeMapperRemote = RemotePokeMapperRemote()
        val varieties = response.varieties.map { VarietyModel(remotePokeMapperRemote.map(it.pokemon), it.is_default) }
        val description = response.flavor_text_entries.first { it.language.name == "en" }.flavor_text
        return SpecieModel(response.id, response.name, description, varieties, response.evolution_chain.url)
    }
}