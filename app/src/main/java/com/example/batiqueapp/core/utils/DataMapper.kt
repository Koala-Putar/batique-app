package com.example.batiqueapp.core.utils

import com.example.batiqueapp.core.data.source.local.entity.BatikEntity
import com.example.batiqueapp.core.data.source.local.entity.CategoryEntity
import com.example.batiqueapp.core.data.source.remote.response.BatikResponse
import com.example.batiqueapp.core.data.source.remote.response.CategoryBatikResponse
import com.example.batiqueapp.core.domain.model.Batik
import com.example.batiqueapp.core.domain.model.Category

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

    fun mapCategoryResponseToEntities(input: List<CategoryBatikResponse>) : List<CategoryEntity> {
        val categoryList = ArrayList<CategoryEntity>()

        input.map {
            val batik = CategoryEntity(
                id = it.id,
                name = it.name,
                description = it.description,
            )

            categoryList.add(batik)
        }

        return categoryList
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

    fun mapCategoryEntitiesToDomain(input: List<CategoryEntity>): List<Category> =
        input.map {
            Category(
                id = it.id,
                name = it.name,
                description = it.description
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

    fun mapCategoryDomainToEntity(input: Category) = CategoryEntity(
        id = input.id,
        name = input.name,
        description = input.description,
    )

}