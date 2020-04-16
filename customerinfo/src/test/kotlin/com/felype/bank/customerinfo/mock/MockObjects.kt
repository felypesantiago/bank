package com.felype.bank.customerinfo.mock

import com.felype.bank.customerinfo.model.Customer

class MockObjects {
	
	companion object {
		fun customer() = Customer(1, "Fulano", "de Tal", "201087-2020")
	}
	
}