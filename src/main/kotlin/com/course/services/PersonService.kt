package com.course.services

import com.course.controllers.dto.PersonDto
import com.course.controllers.request.PostPersonRequest
import com.course.enums.Errors
import com.course.exception.NotFoundException
import com.course.models.*
import com.course.repositories.CityRepository
import com.course.repositories.HouseRepository
import com.course.repositories.PersonRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PersonService (private val personRepository: PersonRepository, private val cityRepository: CityRepository, private val houseRepository: HouseRepository){

    fun findAll(): List<Person> {
        return personRepository.findAll()
    }

    fun findById(id: String): Person {
        return personRepository.findById(id).orElseThrow { NotFoundException(Errors.C101.message.format(id), Errors.C101.code) }
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

        val updatedCityList = person.city.toMutableList().apply { add(cityDto) }
        val updatedPerson = person.copy(
            city = updatedCityList ?: person.city
        )

        val updatedPeopleList = city.people.toMutableList().apply { add(personDto) }
        val updatedCity = city.copy(
            people = updatedPeopleList ?: city.people
        )

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
        val updatedPerson = person.copy(
            house = updatedHouses ?: person.house
        )

        val updatedPeople = house.owner.toMutableList().apply { add(personDto) }
        val updatedHouse = house.copy(
            owner = updatedPeople ?: house.owner
        )

        houseRepository.save(updatedHouse)
        return personRepository.save(updatedPerson)
    }

    fun emailAvailable(email: String): Boolean {
        return !personRepository.existsByEmail(email)
    }
}