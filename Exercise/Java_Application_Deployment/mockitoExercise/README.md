This exercise is similar to the previous one, but here we will be using mockito to mock the class that uses a external 
service.

1. Add the mockito dependency to the `pom.xml`
    ```
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-junit-jupiter</artifactId>
        <version>3.6.0</version>
        <scope>test</scope>
    </dependency>
    ```

2. Create a test and mock the `WorldTimeApiHttpClient` class. By converting it to a field and adding the `@Mock` annotation.

3. Initialize the `TimeZoneService` with the mocked object. It can be in a method annotated with `@BeforeEach`.

3. Use an argument matcher to mock the method and return a fixed list of timezones.