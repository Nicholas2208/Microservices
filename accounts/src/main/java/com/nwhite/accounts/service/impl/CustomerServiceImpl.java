package com.nwhite.accounts.service.impl;

import com.nwhite.accounts.dto.AccountDto;
import com.nwhite.accounts.dto.CardDto;
import com.nwhite.accounts.dto.CustomerDetailsDto;
import com.nwhite.accounts.dto.LoanDto;
import com.nwhite.accounts.entity.Account;
import com.nwhite.accounts.entity.Customer;
import com.nwhite.accounts.exception.ResourceNotFoundException;
import com.nwhite.accounts.mapper.AccountMapper;
import com.nwhite.accounts.mapper.CustomerMapper;
import com.nwhite.accounts.repository.AccountRepository;
import com.nwhite.accounts.repository.CustomerRepository;
import com.nwhite.accounts.service.ICustomerService;
import com.nwhite.accounts.service.client.CardFeignClient;
import com.nwhite.accounts.service.client.LoanFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardFeignClient cardsFeignClient;
    private LoanFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

        ResponseEntity<LoanDto> loanDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        if(null != loanDtoResponseEntity) {
            customerDetailsDto.setLoanDto(loanDtoResponseEntity.getBody());
        }

        ResponseEntity<CardDto> cardDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        if(null != cardDtoResponseEntity) {
            customerDetailsDto.setCardDto(cardDtoResponseEntity.getBody());
        }

        return customerDetailsDto;
    }
}
