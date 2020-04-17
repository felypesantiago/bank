package com.felype.bank.customerinfo.service

import com.felype.bank.customerinfo.exception.BackendServiceException
import com.felype.bank.customerinfo.exception.CustomerNotFoundException
import com.felype.bank.customerinfo.mock.MockObjects
import com.felype.bank.customerinfo.resource.CustomerRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.junit.MockitoJUnitRunner
import reactor.test.StepVerifier
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class CustomerServiceTest {

    @InjectMocks
    private lateinit var customerService: CustomerService

    @Mock
    private lateinit var customerRepository: CustomerRepository

    @Test
    fun testGetCustomer() {
        val customer = MockObjects.customer()

        `when`(customerRepository.findById(any())).thenReturn(Optional.of(customer))

        StepVerifier.create(customerService.getCustomer(customer.id)).expectNext(customer).verifyComplete()
    }

    @Test
    fun testGetCustomerNotFound() {
        `when`(customerRepository.findById(any())).thenThrow(CustomerNotFoundException::class.java)

        StepVerifier.create(customerService.getCustomer(5L)).expectError(CustomerNotFoundException::class.java).verify()
    }

    @Test
    fun testGetCustomerUnexpectedError() {
        `when`(customerRepository.findById(any())).thenThrow(RuntimeException::class.java)

        StepVerifier.create(customerService.getCustomer(5L)).expectError(BackendServiceException::class.java).verify()
    }

}
