package com.employeefullstack.employee.repository

import com.employeefullstack.employee.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> 