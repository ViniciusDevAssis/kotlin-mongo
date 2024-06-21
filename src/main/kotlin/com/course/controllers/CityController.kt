package com.course.controllers

import com.course.controllers.dto.CityDto
import com.course.controllers.request.PostCityRequest
import com.course.models.City
import com.course.services.CityService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/cities"])
class CityController (private val cityService: CityService){

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): Iterable<City>{
        return cityService.findAll()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: String): City{
        return cityService.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody postCityRequest: PostCityRequest): ResponseEntity<City> {
        val city = postCityRequest.toCity()
        val createdCity = cityService.createCity(city)
        return ResponseEntity.ok().body(createdCity)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCityById(@PathVariable id: String): ResponseEntity<Unit> {
        cityService.deleteCityById(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCity(@PathVariable id: String, @RequestBody dto: CityDto): ResponseEntity<City> {
        val updatedCity = cityService.updateCity(id, dto)
        return ResponseEntity.ok(updatedCity)
    }
}