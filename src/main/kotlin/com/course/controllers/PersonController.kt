package com.course.controllers

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
    fun create(@RequestBody person: Person): ResponseEntity<Person> {
        val createdPerson = personService.createPerson(person)
        return ResponseEntity.ok().body(createdPerson)
    }

    @DeleteMapping("/{id}")
    fun deletePersonById(@PathVariable id: String): ResponseEntity<Unit> {
        personService.deletePersonById(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}")
    fun updatePerson(@PathVariable id: String, @RequestBody updatedFields: Map<String, Any?>): ResponseEntity<Person> {
        val updatedPerson = personService.updatePerson(id, updatedFields)
        return ResponseEntity.ok(updatedPerson)
    }

    @PatchMapping("/{personId}/addCity/{cityId}")
    fun addCityToPerson(@PathVariable personId: String, @PathVariable cityId: String): ResponseEntity<Person> {
        val updatedPerson = personService.addCityToPerson(cityId, personId)
        return ResponseEntity.ok(updatedPerson)
    }

    @PatchMapping("/{personId}/addHouse/{houseId}")
    fun addHouseToPerson(@PathVariable personId: String, @PathVariable houseId: String): ResponseEntity<Person> {
        val updatedPerson = personService.addCityToPerson(houseId, personId)
        return ResponseEntity.ok(updatedPerson)
    }
}