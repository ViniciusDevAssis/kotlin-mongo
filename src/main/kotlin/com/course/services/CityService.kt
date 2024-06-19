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

    fun updateCity(id: String, dto: CityDto): City {
        val existingCity = findById(id)

        val updatedCity = existingCity.copy(
            name = dto.name ?: existingCity.name,
            country = dto.country ?: existingCity.country,
            population = dto.population ?: existingCity.population
        )
        return cityRepository.save(updatedCity)
    }
}