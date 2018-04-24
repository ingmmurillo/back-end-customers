package ingmmurillo.customer;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Optional<Customer> findByLastName(String lastName);

}
