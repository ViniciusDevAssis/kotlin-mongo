package com.course.services

import com.course.models.City
import com.course.models.House
import com.course.models.convertPersonToPersonDto
import com.course.repositories.HouseRepository
import com.course.repositories.PersonRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class HouseService(private val houseRepository: HouseRepository, private val personRepository: PersonRepository) {

    fun findAll(): Iterable<House> {
        return houseRepository.findAll()
    }

    fun findById(id: String): House {
        return houseRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    fun createHouse(house: House): House {
        return houseRepository.save(house)
    }

    fun deleteHouseById(id: String) {
        if (!houseRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        houseRepository.deleteById(id)
    }

    fun updateHouse(id: String, updatedFields: Map<String, Any?>): House {
        val existingHouse = findById(id)

        return existingHouse.copy(
            address = updatedFields["address"] as? String ?: existingHouse.address
        ).also { updatedHouse ->
            houseRepository.save(updatedHouse)
        }
    }

    @Transactional
    fun addPersonToHouse(houseId: String, personId: String): House {
        val house = houseRepository.findById(houseId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        val person = personRepository.findById(personId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        val personDto = convertPersonToPersonDto(person)
        house.owner = personDto
        return houseRepository.save(house)
    }
}
