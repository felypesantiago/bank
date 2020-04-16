package com.felype.bank.customerinfo.resource

import org.mockito.junit.MockitoJUnitRunner
import org.junit.runner.RunWith
import com.felype.bank.customerinfo.service.CustomerService
import org.mockito.Mock
import org.mockito.InjectMocks
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyLong
import org.springframework.test.web.reactive.server.WebTestClient
import org.junit.Before
import org.junit.Test
import com.felype.bank.customerinfo.mock.MockObjects
import reactor.core.publisher.Mono
import org.springframework.http.MediaType
import com.felype.bank.customerinfo.model.Customer
import com.felype.bank.customerinfo.exception.CustomerNotFoundException
import org.mockito.Mockito
import org.junit.Assert.assertEquals

@RunWith(MockitoJUnitRunner::class)
class CustomerInfoResourceTest {

	@Mock
	private lateinit var customerService: CustomerService

	@InjectMocks
	private lateinit var customerInfoResource: CustomerInfoResource

	private lateinit var webTestClient: WebTestClient

	@Before
	fun setup() {
		webTestClient = WebTestClient.bindToController(customerInfoResource)
			.controllerAdvice(ResourceErrorHandler())
			.configureClient()
			.build()
	}

	@Test
	fun testGetCustomer() {
		val customer = MockObjects.customer()

		`when`(customerService.getCustomer(anyLong())).thenReturn(Mono.just(customer))

		val receivedCustomer = webTestClient.get().uri("/customers/{customerId}", 1)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBody<Customer>(Customer::class.java).returnResult().getResponseBody()
		
		assertEquals(receivedCustomer, customer)
	}

	@Test
	fun testGetCustomerUnexpectedException() {
		`when`(customerService.getCustomer(anyLong())).thenThrow(RuntimeException::class.java)

		webTestClient.get().uri("/customers/{customerId}", 1)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().is5xxServerError()
	}

	@Test
	fun testGetCustomerNotFound() {
		`when`(customerService.getCustomer(anyLong())).thenReturn(Mono.error(CustomerNotFoundException()))

		webTestClient.get().uri("/customers/{customerId}", 1)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isNotFound()
	}

}
