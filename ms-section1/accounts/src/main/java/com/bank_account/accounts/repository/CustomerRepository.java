package com.bank_account.accounts.repository;

import com.bank_account.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Finds a customer by their mobile number.
     *
     * @param mobileNumber the mobile number of the customer to search for
     * @return an Optional containing the found Customer, or an empty Optional if no customer is found
     */
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
