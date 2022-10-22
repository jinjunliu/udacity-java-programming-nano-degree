package com.udacity.timezones.client;

import java.util.List;

public class FakeWorldTimeApiClient implements WorldTimeApiClient {

    private List<String> validTimeZones;
    public FakeWorldTimeApiClient(List<String> validTimeZones) {
        this.validTimeZones = validTimeZones;
    }

    @Override
    public List<String> getValidTimeZones(String area) {
        return validTimeZones;
    }
}
