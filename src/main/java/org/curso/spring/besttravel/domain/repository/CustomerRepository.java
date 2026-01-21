package org.curso.spring.besttravel.domain.repository;

import org.curso.spring.besttravel.domain.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {
}
