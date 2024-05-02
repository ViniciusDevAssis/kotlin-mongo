package com.course.models

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
    val owner: PersonDto //Reference of a house relationship to a person
)
