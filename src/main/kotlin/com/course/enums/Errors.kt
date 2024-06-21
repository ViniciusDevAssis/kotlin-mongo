package com.course.enums

enum class Errors(
    val code: String,
    val message: String
) {
    //C1XX for errors in the Person entity
    C101("C-0001", "Person [%s] not exists"),

    //C2XX for errors in the City entity
    C201("C-0002", "City [%s] not exists"),

    //C3XX for errors in the House entity
    C301("C-0003", "House [%s] not exists")
}