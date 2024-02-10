package com.nwhite.accounts.service.client;

import com.nwhite.accounts.dto.CardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardFeignClient {
    @Override
    public ResponseEntity<CardDto> fetchCardDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
