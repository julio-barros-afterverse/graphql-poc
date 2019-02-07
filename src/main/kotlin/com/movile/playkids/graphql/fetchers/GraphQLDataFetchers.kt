package com.movile.playkids.graphql.fetchers

import com.movile.playkids.graphql.model.Child
import com.movile.playkids.graphql.model.Person
import com.movile.playkids.graphql.model.PersonId
import graphql.schema.DataFetcher
import org.springframework.stereotype.Component
import java.util.*


/**
 * @author Júlio Moreira Blás de Barros (julio.barros@movile.com)
 * @since 2/7/19
 */
@Component
class GraphQLDataFetchers {

    val personByIdDataFetcher: DataFetcher<Person?> =
        DataFetcher { environment ->
            val personId = environment.getArgument<String>("id")

            people.firstOrNull {
                    person -> person.id == personId
            }
        }

    val allPersonsFetcher: DataFetcher<List<Person>> =
        DataFetcher { environment ->
            val last = environment.getArgument<Int?>("last")

            people.subList(0, last ?: people.size)
        }

    val createPerson: DataFetcher<Person> =
        DataFetcher { environment ->
            Person(
                id = UUID.randomUUID().toString(),
                name = environment.getArgument<String>("name"),
                age = environment.getArgument<Int>("age"),
                children = emptyList()
            ).also {
                people.add(it)
            }
        }

    val newChild: DataFetcher<Person> =
        DataFetcher { env ->
            val parent = people.first {
                it.id == env.getArgument<PersonId>("parentId")
            }

            val child = Child(
                id = UUID.randomUUID().toString(),
                name = env.getArgument<String>("name"),
                age = env.getArgument<Int>("age"),
                parentId = parent.id,
                favoriteCartoonId = env.getArgument<Int>("favoriteCartoonId")
            )

            parent.copy(
                children = parent.children + child
            ).also {
                people.removeIf { p -> p.id == parent.id }
                people.add(it)
            }
        }

    companion object {
        private val children = listOf(
            Child(
                id = "child-1",
                name = "Adriano",
                age = 20,
                parentId = "person-2",
                favoriteCartoonId = 3
            )
        )

        private val people = mutableListOf(
            Person(
                id = "person-1",
                name = "Julio",
                age = 20,
                children = emptyList()
            ),
            Person(
                id = "person-2",
                name = "Kiq",
                age = 24,
                children = emptyList()
            ),
            Person(
                id = "person-3",
                name = "Jao",
                age = 22,
                children = children
            )
        )
    }
}