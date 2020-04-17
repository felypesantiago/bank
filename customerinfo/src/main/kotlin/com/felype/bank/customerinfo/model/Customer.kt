package com.felype.bank.customerinfo.model

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDate
import javax.persistence.*

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
@Table(name = "customers")
data class Customer(@GeneratedValue(strategy = GenerationType.IDENTITY) @Id val id: Long,
                    val firstName: String, val lastName: String, val ssn: String, val birthDate: LocalDate)
