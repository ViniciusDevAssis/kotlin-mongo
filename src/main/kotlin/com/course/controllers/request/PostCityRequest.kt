package com.course.controllers.request

import com.course.models.City
import com.course.models.Person
import jakarta.validation.constraints.NotEmpty

data class PostCityRequest (

    @field:NotEmpty(message = "The field name cannot be empty")
    val name: String,

    @field:NotEmpty(message = "The field country cannot be empty")
    val country: String,

    @field:NotEmpty(message = "The field population cannot be empty")
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
