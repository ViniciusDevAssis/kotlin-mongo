package com.course.services

import com.course.models.Person
import com.course.repositories.PersonRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PersonService (private val personRepository: PersonRepository, private val houseService: HouseService, private val cityService: CityService){

    fun findAll(): Iterable<Person> {
        return personRepository.findAll()
    }

    fun findById(id: String): Person {
        return personRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    fun createPerson(person: Person): Person {
        return personRepository.save(person)
    }

    fun deletePersonById(id: String) {
        if (!personRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        personRepository.deleteById(id)
    }

    fun updatePerson(id: String, updatedFields: Map<String, Any?>): Person {
        val existingPerson = findById(id)

        return existingPerson.copy(
            name = updatedFields["name"] as? String ?: existingPerson.name,
            age = updatedFields["age"] as? String ?: existingPerson.age
        ).also { updatedPerson ->
            personRepository.save(updatedPerson)
        }
    }

    fun assignHouseToPerson(personId: String, houseId: String): Person {
        val person = findById(personId)
        val house = houseService.findById(houseId)
        return person.copy(house = house).also { updatedPerson ->
            personRepository.save(updatedPerson)
        }
    }

    fun assignPersonToCity(personId: String, cityId: String): Person {
        val person = findById(personId)
        val city = cityService.findById(cityId)
        return person.copy(city = city).also { updatedPerson ->
            personRepository.save(updatedPerson)
        }
    }
}