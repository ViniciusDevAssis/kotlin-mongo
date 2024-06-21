package com.course.controllers

import com.course.controllers.dto.HouseDto
import com.course.controllers.request.PostHouseRequest
import com.course.models.House
import com.course.services.HouseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/houses")
class HouseController(private val houseService: HouseService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): Iterable<House> {
        return houseService.findAll()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: String): House {
        return houseService.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody postHouseRequest: PostHouseRequest): ResponseEntity<House> {
        val house = postHouseRequest.toHouse()
        val createdHouse = houseService.createHouse(house)
        return ResponseEntity.ok().body(createdHouse)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteHouseById(@PathVariable id: String): ResponseEntity<Unit> {
        houseService.deleteHouseById(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateHouse(@PathVariable id: String, @RequestBody dto: HouseDto): ResponseEntity<House> {
        val updatedHouse = houseService.updateHouse(id, dto)
        return ResponseEntity.ok(updatedHouse)
    }
}
