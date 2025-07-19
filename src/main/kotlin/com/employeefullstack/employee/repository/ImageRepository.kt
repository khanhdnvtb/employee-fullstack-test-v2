package com.employeefullstack.employee.repository

import com.employeefullstack.employee.entity.Image
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository : JpaRepository<Image, Long> 