package com.udacity.timezones.service;

import com.udacity.timezones.client.FakeWorldTimeApiClient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeZoneServiceTest {

    @Test
    void getAvailableTimezoneText_timeApiReturnsStringList_returnsCountriesAsString() {
        List<String> validTimeZones = List.of("Amsterdam", "Andorra", "Astrakhan", "Athens");
        TimeZoneService timeZoneService = new TimeZoneService(new FakeWorldTimeApiClient(validTimeZones));

        String expectedReturn = "Amsterdam, Andorra, Astrakhan, Athens";

        String timeZoneText = timeZoneService.getAvailableTimezoneText("Europe");

        assertTrue(timeZoneText.contains(expectedReturn));
    }
}
