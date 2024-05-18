package com.course.controllers

import com.course.models.House
import com.course.services.HouseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/houses")
class HouseController(private val houseService: HouseService) {

    @GetMapping
    fun findAll(): Iterable<House> {
        return houseService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): House {
        return houseService.findById(id)
    }

    @PostMapping
    fun create(@RequestBody house: House): ResponseEntity<House> {
        val createdHouse = houseService.createHouse(house)
        return ResponseEntity.ok().body(createdHouse)
    }

    @DeleteMapping("/{id}")
    fun deleteHouseById(@PathVariable id: String): ResponseEntity<Unit> {
        houseService.deleteHouseById(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}")
    fun updateHouse(@PathVariable id: String, @RequestBody updatedFields: Map<String, Any?>): ResponseEntity<House> {
        val updatedHouse = houseService.updateHouse(id, updatedFields)
        return ResponseEntity.ok(updatedHouse)
    }
}
