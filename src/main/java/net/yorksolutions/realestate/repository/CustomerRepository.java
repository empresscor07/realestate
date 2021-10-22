package net.yorksolutions.realestate.repository;

import net.yorksolutions.realestate.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
//    void delete(Optional<Customer> customer);
}
