package com.course.controllers.request

import com.course.models.House
import com.course.models.Person

data class PostHouseRequest (
    val address: String
) {
    fun toHouse(): House {
        return House(
            id = null,
            address = this.address
        )
    }
}
