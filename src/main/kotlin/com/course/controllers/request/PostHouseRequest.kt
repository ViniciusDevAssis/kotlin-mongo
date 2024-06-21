package com.course.controllers.request

import com.course.models.House
import com.course.models.Person
import jakarta.validation.constraints.NotEmpty

data class PostHouseRequest (

    @field:NotEmpty(message = "The field address cannot be empty")
    val address: String
) {
    fun toHouse(): House {
        return House(
            id = null,
            address = this.address
        )
    }
}
