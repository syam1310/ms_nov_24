package com.bank_account.accounts.service;

import com.bank_account.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Creates a new account based on the provided customer information.
     *
     * @param customerDto the customer data transfer object containing account details
     */
    void createAccount(CustomerDto customerDto);


    /**
     * Retrieves a customer account by their mobile number.
     *
     * @param mobileNumber the mobile number of the customer to search for
     * @return a CustomerDto containing the customer's account details
     */

    CustomerDto fetchAccount(String mobileNumber);

    /**
     * Updates an existing account with the provided customer information.
     *
     * @param customerDto the customer data transfer object containing the updated account details
     * @return true if the account was successfully updated, false otherwise
     */

    boolean updateAccount(CustomerDto customerDto);


    /**
     * Deletes a customer account based on their mobile number.
     *
     * @param mobileNumber the mobile number of the customer whose account is to be deleted
     * @return true if the account was successfully deleted, false otherwise
     */

    boolean deleteAccounts(String mobileNumber);
}
