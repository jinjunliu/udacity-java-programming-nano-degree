The `timezones` project is similar to what was done on exercise 1 and 2, but this time it's using the retrofit library
instead of Java's HttpClient. We want to test the integration with this library, so instead of mocking an object we'll 
mock an HTTP response using wiremock.

1. Create the test and setup the wiremock server
    1. Start it with a `@BeforeAll` method and stop it with a `@AfterAll`.
    2. Reset the wiremock server and inject its URL to the `TimeZoneService` in a`@BeforeEach` method.
2. Within the test call `stubFor` with the URL `/api/timezone/Europe` and return a JSON array response.
3. Call the `getAvailableTimezoneText` and assert it's using the response from the server to create a text.