package com.employeefullstack.employee.repository

import com.employeefullstack.employee.entity.Variant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VariantRepository : JpaRepository<Variant, Long> 