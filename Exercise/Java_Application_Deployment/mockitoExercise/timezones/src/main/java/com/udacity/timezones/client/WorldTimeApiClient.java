package com.udacity.timezones.client;

import java.util.List;

public interface WorldTimeApiClient {
    List<String> getValidTimeZones(String area);
}
