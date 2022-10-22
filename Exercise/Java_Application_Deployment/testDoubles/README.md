# Test doubles

The folder `timezones` contains a simple project that gets timezone information from the [World Time API](http://worldtimeapi.org/).

The class `TimeZoneService` depends on `WorldTimeApiHttpClient` that consults the API, we need to test `TimeZoneService` but `WorldTimeApiHttpClient` relies on the external API.

1. Create an interface for `WorldTimeApiHttpClient`, name it just `WorldTimeApiClient`.
2. Use the new interface to create a fake class.
3. Use the fake class to create a unit test testing `TimeZoneService`.