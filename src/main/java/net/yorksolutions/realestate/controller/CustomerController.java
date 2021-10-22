package net.yorksolutions.realestate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.yorksolutions.realestate.model.Customer;
import net.yorksolutions.realestate.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/customers")
@RestController
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/all")
    String getAllCustomers() throws JsonProcessingException {
        return objectMapper.writeValueAsString(customerRepository.findAll());
    }

    // customers/getByRowAmount?rows=2
    @GetMapping("/getByRowAmount")
    String getCustomersByRows(@RequestParam("rows") String rows) throws JsonProcessingException {
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        customerList = customerList.stream().limit(Long.parseLong(rows)).collect(Collectors.toList());
        return objectMapper.writeValueAsString(customerList);
    }

    @PostMapping("/add")
    String putCustomer(@RequestBody String body) {
        Customer customer = null;
        try {
            customer = objectMapper.readValue(body, Customer.class);
            customerRepository.save(customer);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
        return "success";
    }

    @DeleteMapping("/delete")
    String deleteCustomer(@RequestParam("id") Long id) {
//        Optional<Customer> customer = customerRepository.findById(id);
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException | MethodArgumentTypeMismatchException e) {
            return "No such id you fool!!!";
        }

        return "Deleted Customer";
    }
}
