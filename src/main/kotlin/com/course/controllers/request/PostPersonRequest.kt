package com.course.controllers.request

import com.course.models.Person
import com.course.validation.EmailAvailable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostPersonRequest (

    @field:NotEmpty(message = "The field name cannot be empty")
    val name: String,

    @field:NotEmpty(message = "The field age cannot be empty")
    val age: String,

    @field:Email(message= "You must enter a valid email")
    @EmailAvailable
    val email: String

) {
    fun toPerson(): Person {
        return Person(
            id = null,
            name = this.name,
            age = this.age,
            email = this.email
        )
    }
}
