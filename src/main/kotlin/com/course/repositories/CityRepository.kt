package com.course.repositories

import com.course.models.City
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CityRepository: MongoRepository<City, String> {
}