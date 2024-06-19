package com.course.models

import com.course.controllers.dto.CityDto
import com.course.controllers.dto.PersonDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class City(
    @Id
    val id: String?,
    val name: String,
    val country: String,
    val population: String,
    @DBRef
    val people: MutableList<PersonDto>? = mutableListOf() //List of people in the city
)

fun convertCityToCityDto(city: City): CityDto {  //Function that converts a City into a CityDto
    return CityDto(
        id = city.id,
        name = city.name,
        country = city.country,
        population = city.population)
}
