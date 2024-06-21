package com.course.services

import com.course.controllers.dto.HouseDto
import com.course.enums.Errors
import com.course.exception.NotFoundException
import com.course.models.House
import com.course.repositories.HouseRepository
import com.course.repositories.PersonRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class HouseService(private val houseRepository: HouseRepository, private val personRepository: PersonRepository) {

    fun findAll(): Iterable<House> {
        return houseRepository.findAll()
    }

    fun findById(id: String): House {
        return houseRepository.findById(id).orElseThrow { NotFoundException(Errors.C301.message.format(id), Errors.C301.code) }
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

    fun updateHouse(id: String, dto: HouseDto): House {
        val existingHouse = findById(id)

        val updatedHouse = existingHouse.copy(
            address = dto.address ?: existingHouse.address
        )

        return houseRepository.save(updatedHouse)
    }
}
