package ingmmurillo.integration;

import ingmmurillo.CustomerController;
import ingmmurillo.customer.Customer;
import ingmmurillo.customer.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class CustomerControllerTest {

    private CustomerController subject;

    @Mock
    private CustomerRepository customerRepository;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        subject = new CustomerController(customerRepository);
    }

    @Test
    public void debeRetornarOK() throws Exception {
        assertThat(subject.getCustomer(), is("OK"));
    }

    @Test
    public void shouldReturnTheCustomerFullName() throws Exception {
        Customer customer = new Customer("Mauricio", "Murillo");
        given(customerRepository.findByLastName("Murillo")).willReturn(Optional.of(customer));

        String customerFullName = subject.getCustomer("Mur");

        assertThat(customerFullName, is("Mauricio Murillo"));
    }

    @Test
    public void shouldReturnNotFound() throws Exception {
        given(customerRepository.findByLastName(anyString())).willReturn(Optional.empty());

        String customer = subject.getCustomer("Murillo");

        assertThat(customer, is("'Murillo' was not found"));
    }
}