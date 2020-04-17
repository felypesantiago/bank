package com.felype.bank.customerinfo.model

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.persistence.*

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
@Table(name = "customers")
@ApiModel(description = "Represents a bank customer.")
data class Customer(@GeneratedValue(strategy = GenerationType.IDENTITY)
                    @Id
                    @ApiModelProperty(required = true,
                            value = "Unique Customer ID.",
                            example = "1")
                    val id: Long,

                    @ApiModelProperty(required = true,
                            value = "Customer's first name.",
                            example = "Fulano")
                    val firstName: String,

                    @ApiModelProperty(required = true,
                            value = "Customer's last name.",
                            example = "de Tal")
                    val lastName: String,

                    @ApiModelProperty(required = true,
                            value = "Customer's Social Security Number.",
                            example = "201087-2020")
                    val ssn: String,

                    @ApiModelProperty(required = true,
                            value = "Customer's birth date.",
                            example = "1987-10-20")
                    val birthDate: LocalDate)
