package com.udacity.timezones.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public interface WorldTimeApiHttpClient {

	@GET("/api/timezone/{area}")
	Call<List<String>> getValidTimeZones(@Path("area") String area);
}
