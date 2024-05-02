package com.course.services

import com.course.models.City
import com.course.repositories.CityRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CityService (private val cityRepository: CityRepository){

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

}