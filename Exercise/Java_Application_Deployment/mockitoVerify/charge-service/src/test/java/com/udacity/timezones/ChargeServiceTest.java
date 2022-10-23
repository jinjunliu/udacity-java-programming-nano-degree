package com.udacity.timezones;

import com.udacity.timezones.client.ChargeUserApiHttpClient;
import com.udacity.timezones.model.TicketItem;
import com.udacity.timezones.service.ChargeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ChargeServiceTest {

    private ChargeService chargeService;

    @Mock
    private ChargeUserApiHttpClient chargeUserApiHttpClient;

    @BeforeEach
    void init() {
        chargeService = new ChargeService(chargeUserApiHttpClient);
    }


    @Test
    void chargeUser() {
        chargeService.chargeUser(
                "UserId123",
                List.of(
                        new TicketItem("Soda", new BigDecimal("10.0"), new BigDecimal("1.0")),    // 11.00
                        new TicketItem("Soda", new BigDecimal("12.0"), new BigDecimal("1.2"))     // 13.20
                ),
                new BigDecimal("1.50"),                                                       //+ 1.50
                new BigDecimal("0.25")                                                        //- 0.25
        );

        verify(chargeUserApiHttpClient, times(1)).charge(eq("UserId123"), eq(new BigDecimal("25.45")));
    }
}

