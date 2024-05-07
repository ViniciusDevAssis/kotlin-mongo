package com.course.services

import com.course.controllers.dto.CityDto
import com.course.models.City
import com.course.models.Person
import com.course.models.convertCityToCityDto
import com.course.models.convertPersonToPersonDto
import com.course.repositories.CityRepository
import com.course.repositories.PersonRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class CityService (private val cityRepository: CityRepository, private val personRepository: PersonRepository){

    fun findAll(): Iterable<City>{
        return cityRepository.findAll()
    }

    fun findById(id: String): City{
        return cityRepository.findById(id).orElseThrow{
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    fun createCity(city: City): City {
        return cityRepository.save(city)
    }

    fun deleteCityById(id: String) {
        if (!cityRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        cityRepository.deleteById(id)
    }

    fun updateCity(id: String, updatedFields: Map<String, Any?>): City {
        val existingCity = findById(id)

        return existingCity.copy(
            name = updatedFields["name"] as? String ?: existingCity.name,
            country = updatedFields["country"] as? String ?: existingCity.country,
            population = updatedFields["population"] as? String ?: existingCity.population
        ).also { updatedCity ->
            cityRepository.save(updatedCity)
        }
    }

    @Transactional
    fun addPersonToCity(cityId: String, personId: String): City {
        val city = cityRepository.findById(cityId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        val person = personRepository.findById(personId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        val personDto = convertPersonToPersonDto(person)
        city.people.add(personDto)
        return cityRepository.save(city)
    }
}