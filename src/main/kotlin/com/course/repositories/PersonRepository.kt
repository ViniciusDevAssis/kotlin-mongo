package com.course.repositories

import com.course.models.Person
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: MongoRepository<Person, String> {
}