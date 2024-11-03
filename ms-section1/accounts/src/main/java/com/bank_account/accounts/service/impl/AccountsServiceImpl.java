package com.bank_account.accounts.service.impl;

import com.bank_account.accounts.constant.AccountsConstant;
import com.bank_account.accounts.dto.AccountsDto;
import com.bank_account.accounts.dto.CustomerDto;
import com.bank_account.accounts.entity.Accounts;
import com.bank_account.accounts.entity.Customer;
import com.bank_account.accounts.exception.CustomerAlreadyExitsException;
import com.bank_account.accounts.exception.ResourseNotFoundException;
import com.bank_account.accounts.mapper.AccountsMapper;
import com.bank_account.accounts.mapper.CustomerMapper;
import com.bank_account.accounts.repository.AccountsRepository;
import com.bank_account.accounts.repository.CustomerRepository;
import com.bank_account.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {


    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;


    /**
     * Creates a new account based on the provided customer information.
     *
     * @param customerDto the customer data transfer object containing account details
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (customerOptional.isPresent()) {
            throw new CustomerAlreadyExitsException("Customer already exists with given mobile number "
                    + customerDto.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);

        Accounts newAccounts = createNewAccounts(savedCustomer);
        accountsRepository.save(newAccounts);
    }

    /**
     * Retrieves a customer account by their mobile number.
     *
     * @param mobileNumber the mobile number of the customer to search for
     * @return a CustomerDto containing the customer's account details
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourseNotFoundException("Customer", "Mobile Number", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourseNotFoundException("Customer", "Mobile Number", mobileNumber));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        // You might need to add additional mapping for accounts to customerDto if required

        return customerDto;

    }

    /**
     * Updates an existing account with the provided customer information.
     *
     * @param customerDto the customer data transfer object containing the updated account details
     * @return true if the account was successfully updated, false otherwise
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(() -> new ResourseNotFoundException("Account", "Account Number",
                            accountsDto.getAccountNumber().toString()));
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts
                    .getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourseNotFoundException("Customer", "Customer Id", customerId.toString()));
            CustomerMapper.mapToCustomer(customerDto, customer);
            customer = customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;
    }

    /**
     * Deletes a customer account based on their mobile number.
     *
     * @param mobileNumber the mobile number of the customer whose account is to be deleted
     * @return true if the account was successfully deleted, false otherwise
     */
    @Override
    public boolean deleteAccounts(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourseNotFoundException("Customer", "Mobile Number", mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


    private Accounts createNewAccounts(Customer customer) {
        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerId(customer.getCustomerId());

        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccounts.setAccountNumber(randomAccNumber);
        newAccounts.setAccountType(AccountsConstant.SAVINGS);
        newAccounts.setBranchAddress(AccountsConstant.ADDRESS);
        return newAccounts;
    }
}
