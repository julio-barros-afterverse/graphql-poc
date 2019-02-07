package com.movile.playkids.graphql.fetchers

import org.checkerframework.checker.nullness.Opt.orElse
import graphql.schema.DataFetcher
import org.springframework.stereotype.Component


/**
 * @author Júlio Moreira Blás de Barros (julio.barros@movile.com)
 * @since 2/7/19
 */
@Component
class GraphQLDataFetchers {

    val personByIdDataFetcher: DataFetcher<Map<String, Any?>?> =
        DataFetcher { environment ->
            val personId = environment.getArgument<String>("id")

            people.firstOrNull {
                    person -> person.get("id") == personId
            }
        }

    val allPersonsFetcher: DataFetcher<List<Map<String, Any?>>> =
        DataFetcher { environment ->
            val last = environment.getArgument<Int?>("last")

            people.subList(0, last ?: people.size)
        }

    val createPerson: DataFetcher<List<Map<String, Any?>>> =
        DataFetcher { environment ->
            emptyList<Map<String, Any?>>()
        }

    companion object {
        private val children = listOf(
            mapOf(
                "id" to "child-1",
                "name" to "Adriano",
                "age" to 20,
                "parentId" to "person-2",
                "favoriteCartoonId" to 3
            )
        )

        private val people = mutableListOf(
            mapOf(
                "id" to "person-1",
                "name" to "Julio",
                "age" to 20,
                "children" to emptyList<Map<String, String>>()
            ),
            mapOf(
                "id" to "person-2",
                "name" to "Kiq",
                "age" to 24,
                "children" to emptyList<Map<String, String>>()
            ),
            mapOf(
                "id" to "person-3",
                "name" to "Jao",
                "age" to 22,
                "children" to children
            )
        )
    }
}