package com.rackspace.fibonacci.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/fibonacciGenerator")
public class FibonacciGeneratorSvc {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Integer> generateFibonacciSeries(@QueryParam("range") int range) {
		List<Integer> fibSeries = new ArrayList<Integer>();
		int newNumber = 0;
		fibSeries.add(0);
		if (range >= 2) {
			fibSeries.add(1);
		}

		for (int index = 2; index < range; index++) {
			newNumber = fibSeries.get(index - 1) + fibSeries.get(index - 2);
			fibSeries.add(newNumber);
		}

		return fibSeries;
	}
}
