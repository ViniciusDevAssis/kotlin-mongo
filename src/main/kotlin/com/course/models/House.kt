package com.course.models

import com.course.controllers.dto.CityDto
import com.course.controllers.dto.HouseDto
import com.course.controllers.dto.PersonDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class House(
    @Id
    val id: String?,
    val address: String,
    @DBRef
    val owner: MutableList<PersonDto> = mutableListOf() //Reference of a house relationship to a person
)

fun convertHouseToHouseDto(house: House): HouseDto {  //Function that converts a House into a HouseDto
    return HouseDto(
        id = house.id,
        address = house.address)
}
