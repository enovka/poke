package br.com.wiser.poke.uc.implementation

import br.com.wiser.poke.data.domain.entity.PokeSpecieEntity
import br.com.wiser.poke.data.repository.IPokeRepository
import br.com.wiser.poke.uc.IUCPokeSpecie

class UCPokeSpecieImpl(private val repository: IPokeRepository) : IUCPokeSpecie {

    override suspend fun load(name: String): PokeSpecieEntity {
        return repository.getSpeciesByName(name)
    }
}