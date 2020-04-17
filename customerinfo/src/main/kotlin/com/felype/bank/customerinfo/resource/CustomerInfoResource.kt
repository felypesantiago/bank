package com.felype.bank.customerinfo.resource

import com.felype.bank.customerinfo.model.Customer
import com.felype.bank.customerinfo.service.CustomerService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/customers", produces = [MediaType.APPLICATION_JSON_VALUE])
class CustomerInfoResource(private val customerService: CustomerService) {

    @GetMapping("/{customerId}")
    fun getCustomer(@PathVariable("customerId") id: Long): Mono<Customer> {
        return customerService.getCustomer(id)
    }

}
