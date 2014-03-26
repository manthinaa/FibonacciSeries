package com.rackspace.fibonacci.service.filter;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.rackspace.fibonacci.exception.InvalidMaxRangeException;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class FibonacciGeneratorSvcFilter implements ContainerRequestFilter {
	private static int maxRangeLimit = 50;

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		MultivaluedMap<String, String> map = request.getQueryParameters();
		List<String> rangeList = map.get("range");
		String rangeString = null;
		int range = 0;
		if (rangeList != null && rangeList.size() > 0) {
			rangeString = rangeList.get(0);
			if (rangeString != null) {
				range = Integer.valueOf(rangeString);
			} else {
				throw new InvalidMaxRangeException();
			}
		} else {
			throw new InvalidMaxRangeException();
		}

		if (range < 1) {
			throw new InvalidMaxRangeException("Range given is:" + range
					+ ". And it should not be less than or equal to 0");
		}

		if (range > maxRangeLimit) {
			throw new InvalidMaxRangeException(
					"Range given is:"
							+ range
							+ ". And it should not be less than or equal to Maximum range limit: "
							+ maxRangeLimit);
		}
		return request;
	}
}
