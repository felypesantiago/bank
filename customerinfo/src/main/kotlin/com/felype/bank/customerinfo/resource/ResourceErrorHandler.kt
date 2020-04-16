package com.felype.bank.customerinfo.resource

import com.felype.bank.customerinfo.exception.BackendServiceException
import com.felype.bank.customerinfo.exception.CustomerNotFoundException
import com.felype.bank.customerinfo.util.logger
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ResourceErrorHandler {
	val log = logger()
	
	@ExceptionHandler(value = [CustomerNotFoundException::class, EmptyResultDataAccessException::class])
	fun handleCustomerNotFoundException(e : CustomerNotFoundException) = ResponseEntity<Any>(HttpStatus.NOT_FOUND)
	
	@ExceptionHandler(value = [BackendServiceException::class])
	fun handleBackendServiceException(backendServiceException : BackendServiceException) : ResponseEntity<Any> {
		// Don't expose implementation details to client when failing. Lot it instead.
		log.error("Unexpected Error", backendServiceException.cause)

		return ResponseEntity<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
	}	
	
}