package com.udacity.timezones.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class TimeZoneServiceTest {

    static WireMockServer wireMock = new WireMockServer(wireMockConfig().port(8089));

    private TimeZoneService timeZoneService;

    @BeforeAll
    static void setup() {
        wireMock.start();
    }
    @AfterAll
    static void cleanup() {
        wireMock.stop();
    }

    @BeforeEach
    void init() {
        wireMock.resetAll();
        timeZoneService = new TimeZoneService("http://localhost:8089");
    }

    @Test
    void getAvailableTimezoneText_timeApiReturnsStringList_returnsCountriesAsString() {
        wireMock.stubFor(
                get(urlEqualTo("/api/timezone/Europe"))
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withBody("[\"Amsterdam\", \"Andorra\", \"Astrakhan\", \"Athens\"]")
                        )
        );

        String availableTimezoneText = timeZoneService.getAvailableTimezoneText("Europe");

        assertTrue(availableTimezoneText.contains("Available timezones in Europe are Amsterdam, Andorra"));
    }
}
