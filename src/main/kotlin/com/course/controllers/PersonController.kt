package com.course.controllers

import com.course.controllers.dto.PersonDto
import com.course.controllers.request.PostPersonRequest
import com.course.models.Person
import com.course.services.PersonService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/people"])
class PersonController (private val personService: PersonService){

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(@RequestParam name: String?): List<Person> {
        name?.let {
            return personService.findAll().filter { it.name.contains(name, ignoreCase = true) }
        }
        return personService.findAll()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: String): Person {
        return personService.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid postPersonRequest: PostPersonRequest): ResponseEntity<Person> {
        val person = postPersonRequest.toPerson()
        val createdPerson = personService.createPerson(person)
        return ResponseEntity.ok().body(createdPerson)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePersonById(@PathVariable id: String){
        personService.deletePersonById(id)
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updatePerson(@PathVariable id: String, @RequestBody dto: PersonDto): ResponseEntity<Person> {
        val updatedPerson = personService.updatePerson(id, dto)
        return ResponseEntity.ok(updatedPerson)
    }

    @PatchMapping("/{personId}/addCity/{cityId}")
    @ResponseStatus(HttpStatus.OK)
    fun addCityToPerson(@PathVariable personId: String, @PathVariable cityId: String): ResponseEntity<Person> {
        val updatedPerson = personService.addCityToPerson(personId, cityId)
        return ResponseEntity.ok(updatedPerson)
    }

    @PatchMapping("/{personId}/addHouse/{houseId}")
    @ResponseStatus(HttpStatus.OK)
    fun addHouseToPerson(@PathVariable personId: String, @PathVariable houseId: String): ResponseEntity<Person> {
        val updatedPerson = personService.addHouseToPerson(personId, houseId)
        return ResponseEntity.ok(updatedPerson)
    }
}