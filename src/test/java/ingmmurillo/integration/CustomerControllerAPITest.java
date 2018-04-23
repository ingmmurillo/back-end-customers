package ingmmurillo.integration;

import ingmmurillo.CustomerController;
import ingmmurillo.customer.Customer;
import ingmmurillo.customer.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;


    @Test
    public void shouldReturnOKByDefault() throws Exception {
        mockMvc.perform(get("/customer"))
                .andExpect(content().string("OK"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnFullName() throws Exception {
        Customer customer = new Customer("Mauricio", "Murillo");
        given(customerRepository.findByLastName("Murillo")).willReturn(Optional.of(customer));

        mockMvc.perform(get("/customer/Murillo"))
                .andExpect(content().string("Mauricio Murillo"))
                .andExpect(status().is2xxSuccessful());
    }
}
