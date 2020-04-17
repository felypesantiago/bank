package com.felype.bank.customerinfo.resource

import com.felype.bank.customerinfo.model.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : CrudRepository<Customer, Long>