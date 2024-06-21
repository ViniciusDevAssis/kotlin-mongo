package com.course.controllers

import com.course.controllers.dto.PersonDto
import com.course.controllers.request.PostPersonRequest
import com.course.models.Person
import com.course.services.PersonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/people"])
class PersonController (private val personService: PersonService){

    @GetMapping
    fun findAll(): Iterable<Person> {
        return personService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): Person {
        return personService.findById(id)
    }

    @PostMapping
    fun create(@RequestBody postPersonRequest: PostPersonRequest): ResponseEntity<Person> {
        val person = postPersonRequest.toPerson()
        val createdPerson = personService.createPerson(person)
        return ResponseEntity.ok().body(createdPerson)
    }

    @DeleteMapping("/{id}")
    fun deletePersonById(@PathVariable id: String): ResponseEntity<Unit> {
        personService.deletePersonById(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}")
    fun updatePerson(@PathVariable id: String, @RequestBody dto: PersonDto): ResponseEntity<Person> {
        val updatedPerson = personService.updatePerson(id, dto)
        return ResponseEntity.ok(updatedPerson)
    }

    @PatchMapping("/{personId}/addCity/{cityId}")
    fun addCityToPerson(@PathVariable personId: String, @PathVariable cityId: String): ResponseEntity<Person> {
        val updatedPerson = personService.addCityToPerson(personId, cityId)
        return ResponseEntity.ok(updatedPerson)
    }

    @PatchMapping("/{personId}/addHouse/{houseId}")
    fun addHouseToPerson(@PathVariable personId: String, @PathVariable houseId: String): ResponseEntity<Person> {
        val updatedPerson = personService.addHouseToPerson(personId, houseId)
        return ResponseEntity.ok(updatedPerson)
    }
}