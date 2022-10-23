package com.udacity.timezones.service;

import com.udacity.timezones.client.WorldTimeApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TimeZoneServiceTest {

    private TimeZoneService timeZoneService;

    @Mock
    private WorldTimeApiClient worldTimeApiClient;

    @BeforeEach
    void init() {
        timeZoneService = new TimeZoneService(worldTimeApiClient);
    }

    @Test
    void getAvailableTimezoneText_timeApiReturnsStringList_returnsCountriesAsString() {
        List<String> validTimeZones = List.of("Amsterdam", "Andorra", "Astrakhan", "Athens");
        when(worldTimeApiClient.getValidTimeZones(anyString())).thenReturn(validTimeZones);

        String expectedReturn = "Amsterdam, Andorra, Astrakhan, Athens";

        String availableTimezoneText = timeZoneService.getAvailableTimezoneText("Europe");

        assertTrue(availableTimezoneText.contains(expectedReturn));
    }
}
