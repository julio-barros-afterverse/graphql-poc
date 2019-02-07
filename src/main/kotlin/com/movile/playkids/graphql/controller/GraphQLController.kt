package com.movile.playkids.graphql.controller

import graphql.GraphQL
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @author Júlio Moreira Blás de Barros (julio.barros@movile.com)
 * @since 2/7/19
 */
/*
class GraphQLController(private val graphQL: GraphQL) {

    @RequestMapping("/graphql")
    fun execute(@RequestBody query: String): String {
        val executionResult = graphQL.execute(query)

        return executionResult.getData<String>()
    }

}*/