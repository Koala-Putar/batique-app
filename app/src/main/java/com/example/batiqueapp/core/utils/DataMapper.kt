package com.example.batiqueapp.core.utils

import com.example.batiqueapp.core.data.source.local.entity.BatikEntity
import com.example.batiqueapp.core.data.source.remote.response.BatikResponse
import com.example.batiqueapp.core.domain.model.Batik

object DataMapper {

    fun mapResponsesToEntities(input: List<BatikResponse>) : List<BatikEntity> {
        val batikList = ArrayList<BatikEntity>()

        input.map {
            val batik = BatikEntity(
                id = it.id,
                name = it.name,
                description = it.description,
                image = it.image,
                category = it.category.name,
                isFavorite = false
            )

            batikList.add(batik)
        }

        return batikList
    }

    fun mapEntitiesToDomain(input: List<BatikEntity>): List<Batik> =
            input.map {
                Batik(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    image = it.image,
                    category = it.category,
                    isFavorite = it.isFavorite
                )
            }

    fun mapDomainToEntity(input: Batik) = BatikEntity(
            id = input.id,
            name = input.name,
            description = input.description,
            image = input.image,
            category = input.category,
            isFavorite = input.isFavorite
    )

}