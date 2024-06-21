package com.course.services

import com.course.controllers.dto.CityDto
import com.course.enums.Errors
import com.course.exception.NotFoundException
import com.course.models.City
import com.course.repositories.CityRepository
import com.course.repositories.PersonRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CityService (private val cityRepository: CityRepository, private val personRepository: PersonRepository){

    fun findAll(): Iterable<City>{
        return cityRepository.findAll()
    }

    fun findById(id: String): City{
        return cityRepository.findById(id).orElseThrow{ NotFoundException(Errors.C201.message.format(id), Errors.C201.code) }
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