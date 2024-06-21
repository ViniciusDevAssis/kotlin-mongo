package com.course.controllers.request

import com.course.models.City
import com.course.models.Person

data class PostCityRequest (
    val name: String,
    val country: String,
    val population: String,
) {
    fun toCity(): City {
        return City(
            id = null,
            name = this.name,
            country = this.country,
            population = this.population
        )
    }
}
