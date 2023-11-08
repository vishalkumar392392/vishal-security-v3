package com.vishal.security.vishalsecurity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.security.vishalsecurity.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	List<Customer> findByEmail(String username);

}
