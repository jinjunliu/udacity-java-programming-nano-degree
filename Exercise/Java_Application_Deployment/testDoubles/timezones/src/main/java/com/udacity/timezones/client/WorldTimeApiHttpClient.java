package com.udacity.timezones.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorldTimeApiHttpClient implements WorldTimeApiClient{
	private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
	private final Pattern r = Pattern.compile("/(.*?)\"");

	public List<String> getValidTimeZones(String area) {

		HttpRequest req = HttpRequest.newBuilder(URI.create("http://worldtimeapi.org/api/timezone/" + area))
			.GET().build();
		try {
			HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());

			Matcher m = r.matcher(response.body());

			List<String> matches = new ArrayList<>();
			while (m.find()) {
				matches.add(m.group(1));
			}

			return matches;
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error getting the timezones for " + area, e);
		}
	}
}
