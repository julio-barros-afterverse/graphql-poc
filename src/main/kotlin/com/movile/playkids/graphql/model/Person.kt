package com.movile.playkids.graphql.model

/**
 * @author Júlio Moreira Blás de Barros (julio.barros@movile.com)
 * @since 2/7/19
 */

typealias PersonId = String

data class Person(
    val id: PersonId,
    val name: String,
    val age: Int,
    val children: List<Child>
)