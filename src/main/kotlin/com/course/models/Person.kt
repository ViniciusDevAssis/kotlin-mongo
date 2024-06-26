package com.course.models

import com.course.controllers.dto.CityDto
import com.course.controllers.dto.HouseDto
import com.course.controllers.dto.PersonDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Person(
    @Id
    val id: String?,
    val name: String,
    val age: String,
    val email: String,

    val city: MutableList<CityDto> = mutableListOf(), //Reference of a person's relationship to a city

    val house: MutableList<HouseDto> = mutableListOf() //Reference of a person's relationship to a house
)

fun convertPersonToPersonDto(person: Person): PersonDto {  //Function that converts a Person into a PersonDto
    return PersonDto(
        id = person.id,
        name = person.name,
        age = person.age
    )
}
