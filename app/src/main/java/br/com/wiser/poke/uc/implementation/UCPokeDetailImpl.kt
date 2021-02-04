package br.com.wiser.poke.uc.implementation

import br.com.wiser.poke.data.domain.entity.PokeDetailEntity
import br.com.wiser.poke.data.repository.IPokeRepository
import br.com.wiser.poke.uc.IUCPokeDetail

class UCPokeDetailImpl(private val repository: IPokeRepository) : IUCPokeDetail {

    override suspend fun load(name: String): PokeDetailEntity {
        return repository.getDetailByName(name)
    }
}