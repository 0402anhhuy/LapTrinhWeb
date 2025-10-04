package com.ltw.bt02_springsecurity.controller;

import com.ltw.bt02_springsecurity.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    private final List<Customer> customers = List.of(
            Customer.builder().id("23110229").name("Trần Anh Huy").phoneNumber("0337686255").email("23110229@student.hcmute.edu.vn").build(),
            Customer.builder().id("23110201").name("Anh Huy").phoneNumber("0337686256").email("anhhuyrubic@gmail.com").build()
    );

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Home");
    }


    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello Guest");
    }

    @GetMapping("/customer/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Customer>> customers() {
        List<Customer> customers = this.customers;
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Customer> getCustomerList(@PathVariable("id") String id) {
        Optional<Customer> found = this.customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
        return found.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
