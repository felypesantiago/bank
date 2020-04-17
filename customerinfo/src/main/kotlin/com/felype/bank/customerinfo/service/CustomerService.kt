package com.felype.bank.customerinfo.service

import com.felype.bank.customerinfo.exception.BackendServiceException
import com.felype.bank.customerinfo.exception.CustomerNotFoundException
import com.felype.bank.customerinfo.model.Customer
import com.felype.bank.customerinfo.resource.CustomerRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CustomerService(val customerRepository: CustomerRepository) {

    fun getCustomer(id: Long): Mono<Customer> {
        return Mono.fromCallable { customerRepository.findById(id).orElseThrow { CustomerNotFoundException() } }
                .onErrorMap(handleErrors())
    }

    private fun handleErrors(): (Throwable) -> Throwable {
        return { throwable ->
            when (throwable) {
                is CustomerNotFoundException -> throwable
                is EmptyResultDataAccessException -> CustomerNotFoundException()
                else -> BackendServiceException(throwable)
            }
        }
    }

}
