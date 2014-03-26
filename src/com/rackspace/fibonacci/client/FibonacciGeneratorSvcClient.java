package com.rackspace.fibonacci.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class FibonacciGeneratorSvcClient {

	protected final Logger log = Logger
			.getLogger(FibonacciGeneratorSvcClient.class);

	public int generateFibonacciSeries(int range) {
		int status = 0;
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		URI uri = UriBuilder.fromUri(
				"http://localhost:9090/FibonacciSeries/fibonacciGenerator")
				.build();
		WebResource service = client.resource(uri);
		service = service.queryParam("range", String.valueOf(range));
		try {
			ClientResponse response = service.type(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.get(ClientResponse.class);
			status = response.getStatus();
			if (status == 200) {
				log.info("First "
						+ range
						+ " Fibonacci numbers are: "
						+ getStringFromInputStream(response
								.getEntityInputStream()));
			} else {
				log.error("Exception occured: "
						+ getStringFromInputStream(response
								.getEntityInputStream()));
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

}
