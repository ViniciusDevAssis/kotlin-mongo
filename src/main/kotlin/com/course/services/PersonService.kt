package com.course.services

import com.course.models.*
import com.course.repositories.CityRepository
import com.course.repositories.HouseRepository
import com.course.repositories.PersonRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class PersonService (private val personRepository: PersonRepository, private val cityRepository: CityRepository, private val houseRepository: HouseRepository){

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

    fun addCityToPerson(cityId: String, personId: String): Person {
        val city = cityRepository.findById(cityId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        val person = personRepository.findById(personId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        val cityDto = convertCityToCityDto(city)
        val personDto = convertPersonToPersonDto(person)
        city.people.add(personDto)
        person.city.add(cityDto)
        cityRepository.save(city)
        return personRepository.save(person)
    }

    fun addHouseToPerson(houseId: String, personId: String): Person {
        val house = houseRepository.findById(houseId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        val person = personRepository.findById(personId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        val houseDto = convertHouseToHouseDto(house)
        val personDto = convertPersonToPersonDto(person)
        house.owner.add(personDto)
        person.house.add(houseDto)
        houseRepository.save(house)
        return personRepository.save(person)
    }
}