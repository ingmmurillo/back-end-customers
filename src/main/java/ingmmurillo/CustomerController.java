package ingmmurillo;

import ingmmurillo.customer.Customer;
import ingmmurillo.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customer")
    public String getCustomer() {
        return "OK";
    }

    @GetMapping("/customer/{lastName}")
    public String getCustomer(@PathVariable final String lastName) {
        Optional<Customer> customer = customerRepository.findByLastName(lastName);

        return customer
                .map(person -> String.format("%s %s", person.getFirstName(), person.getLastName()))
                .orElse(String.format("'%s' was not found", lastName));
    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        List<Customer> customersList = new ArrayList<Customer>();
        Iterable<Customer> iterator = customerRepository.findAll();
        iterator.iterator().forEachRemaining(customer -> customersList.add(customer));
        return customersList;
    }
}
