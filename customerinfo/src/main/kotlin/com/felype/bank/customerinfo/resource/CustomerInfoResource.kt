package com.felype.bank.customerinfo.resource

import com.felype.bank.customerinfo.model.Customer
import com.felype.bank.customerinfo.service.CustomerService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.net.HttpURLConnection

@Api
@RestController
@RequestMapping("/customers", produces = [MediaType.APPLICATION_JSON_VALUE])
class CustomerInfoResource(private val customerService: CustomerService) {

    @ApiOperation(value = "Get customer by ID.",
            notes = "Allows client to retrieve customer Data.",
            tags = ["customer"])
    @ApiResponses(
            ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Customer successfully retrieved.", response = Customer::class),
            ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Customer with the given ID not found."),
            ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error."),
            ApiResponse(code = HttpURLConnection.HTTP_UNAVAILABLE, message = "Service temporarily unavailable due to service maintenance, heavy load or other issues."))
    @GetMapping("/{customerId}")
    fun getCustomer(@PathVariable("customerId") id: Long): Mono<Customer> {
        return customerService.getCustomer(id)
    }

}
