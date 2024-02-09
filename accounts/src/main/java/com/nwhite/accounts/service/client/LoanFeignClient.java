package com.nwhite.accounts.service.client;

import com.nwhite.accounts.dto.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoanFeignClient {
    @GetMapping(value = "/api/fetch",consumes = "application/json")
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestHeader("whitebank-correlation-id") String correlationId,
                                                    @RequestParam String mobileNumber);
}
