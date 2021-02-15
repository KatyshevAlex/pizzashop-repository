package com.pizzashop.repository;

import com.pizzashop.repository.enity.Customer;
import com.pizzashop.repository.repository.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.pizzashop.repository.prototype.Prototypes.getCustomer;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class RepositoryApplicationTests {
	@Autowired
	CustomerRepo customerRepo;

	@Test
	void saveCustomerTest() {
		assertEquals("login", customerRepo.save(getCustomer("login"))
				.getLogin());
	}

	@Test
	void updateCustomerTest() {
		Customer c = customerRepo.findByLogin("login");
		if(c != null) {
			c.setLogin("updated");
			customerRepo.save(c);
			assertNotNull(customerRepo.findByLogin("updated"));
		}
	}

	@Test
	void getCustomerTest() {
		Customer c = customerRepo.getOne(1L);
		assertNotNull(c);
	}

}
