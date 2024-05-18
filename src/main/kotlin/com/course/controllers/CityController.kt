package com.course.controllers

import com.course.models.City
import com.course.services.CityService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/cities"])
class CityController (private val cityService: CityService){

    @GetMapping
    fun findAll(): Iterable<City>{
        return cityService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): City{
        return cityService.findById(id)
    }

    @PostMapping
    fun create(@RequestBody city: City): ResponseEntity<City> {
        val createdCity = cityService.createCity(city)
        return ResponseEntity.ok().body(createdCity)
    }

    @DeleteMapping("/{id}")
    fun deleteCityById(@PathVariable id: String): ResponseEntity<Unit> {
        cityService.deleteCityById(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}")
    fun updateCity(@PathVariable id: String, @RequestBody updatedFields: Map<String, Any?>): ResponseEntity<City> {
        val updatedCity = cityService.updateCity(id, updatedFields)
        return ResponseEntity.ok(updatedCity)
    }
}