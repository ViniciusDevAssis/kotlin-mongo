package com.course.services

import com.course.controllers.dto.PersonDto
import com.course.models.*
import com.course.repositories.CityRepository
import com.course.repositories.HouseRepository
import com.course.repositories.PersonRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
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

    fun updatePerson(id: String, dto: PersonDto): Person {
        val existingPerson = findById(id)

        val updatePerson = existingPerson.copy(
            name = dto.name ?: existingPerson.name,
            age = dto.age ?: existingPerson.age
        )

        return personRepository.save(updatePerson)
    }

    fun addCityToPerson(personId: String, cityId: String): Person {
        val city = cityRepository.findById(cityId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        val person = personRepository.findById(personId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }

        val cityDto = convertCityToCityDto(city)
        val personDto = convertPersonToPersonDto(person)

        val updatedCities = person.city.toMutableList().apply { add(cityDto) }
        val updatedPerson = person.copy(city = updatedCities)

        val updatedPeople = city.people.toMutableList().apply { add(personDto) }
        val updatedCity = city.copy(people = updatedPeople)

        cityRepository.save(updatedCity)
        return personRepository.save(updatedPerson)
    }

    fun addHouseToPerson(personId: String, houseId: String): Person {
        val house = houseRepository.findById(houseId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        val person = personRepository.findById(personId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }

        val houseDto = convertHouseToHouseDto(house)
        val personDto = convertPersonToPersonDto(person)

        val updatedHouses = person.house.toMutableList().apply{ add(houseDto) }
        val updatedPerson = person.copy(house = updatedHouses)

        val updatedPeople = house.owner.toMutableList().apply { add(personDto) }
        val updatedHouse = house.copy(owner = updatedPeople)

        houseRepository.save(updatedHouse)
        return personRepository.save(updatedPerson)
    }
}