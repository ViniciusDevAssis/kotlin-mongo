package com.course.controllers.request

import com.course.models.Person

data class PostPersonRequest (
    val name: String,
    val age: String
) {
    fun toPerson(): Person {
        return Person(
            id = null,
            name = this.name,
            age = this.age
        )
    }
}
