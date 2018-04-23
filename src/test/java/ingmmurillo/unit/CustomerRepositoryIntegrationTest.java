package ingmmurillo.unit;

import ingmmurillo.customer.Customer;
import ingmmurillo.customer.CustomerRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @After
    public void tearDown() throws Exception {
        customerRepository.deleteAll();
    }

    @Test
    public void shouldFindACustomerByLastName() throws Exception {
        Customer customer = new Customer("Mauricio", "Murillo");
        customerRepository.save(customer);

        Optional<Customer> customerFound = customerRepository.findByLastName("Murillo");

        assertThat(customerFound, is(Optional.of(customer)));
    }
}