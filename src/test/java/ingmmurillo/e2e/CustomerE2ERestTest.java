package ingmmurillo.e2e;

import ingmmurillo.customer.Customer;
import ingmmurillo.customer.CustomerRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerE2ERestTest {

    @Autowired
    private CustomerRepository customerRepository;

    @LocalServerPort
    private int port;

    @After
    public void tearDown() throws Exception {
        customerRepository.deleteAll();
    }

    @Test
    public void shouldReturnOkWhenIsSentAGetRequest() throws Exception {
        when()
                .get(String.format("http://localhost:%s/customer", port))
                .then()
                .statusCode(is(200))
                .body(containsString("OK"));
    }

    @Test
    public void shouldReturnTheFullNameGivenTheLastName() throws Exception {
        Customer peter = new Customer("Mauricio", "Murillo");
        customerRepository.save(peter);

        when()
                .get(String.format("http://localhost:%s/customer/Murillo", port))
                .then()
                .statusCode(is(200))
                .body(containsString("Mauricio Murillo"));
    }
}
