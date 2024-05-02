package com.course.repositories

import com.course.models.House
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface HouseRepository: MongoRepository<House, String> {
}