package com.movile.playkids.graphql.model

/**
 * @author Júlio Moreira Blás de Barros (julio.barros@movile.com)
 * @since 2/7/19
 */
typealias ChildId = String

data class Child (
    val id: ChildId,
    val name: String,
    val age: Int,
    val parentId: PersonId,
    val favoriteCartoonId: Int
)