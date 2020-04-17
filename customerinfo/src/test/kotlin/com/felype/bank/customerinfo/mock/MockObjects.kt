package com.felype.bank.customerinfo.mock

import com.felype.bank.customerinfo.model.Customer
import java.time.LocalDate

class MockObjects {

    companion object {
        fun customer() = Customer(1, "Fulano", "de Tal", "201087-2020", LocalDate.parse("1987-10-20"))
    }

}
