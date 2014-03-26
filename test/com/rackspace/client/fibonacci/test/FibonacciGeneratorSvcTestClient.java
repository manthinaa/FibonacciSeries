package com.rackspace.client.fibonacci.test;

import org.junit.Assert;
import org.junit.Test;

import com.rackspace.fibonacci.client.FibonacciGeneratorSvcClient;

public class FibonacciGeneratorSvcTestClient {

	@Test
	public void testFibonacciGeneratorSvcSuccess() {
		FibonacciGeneratorSvcClient client = new FibonacciGeneratorSvcClient();
		int status = client.generateFibonacciSeries(15);
		Assert.assertTrue(status == 200);
	}

	@Test
	public void testFibonacciGeneratorSvcFailRangeNegative() {
		FibonacciGeneratorSvcClient client = new FibonacciGeneratorSvcClient();
		int status = client.generateFibonacciSeries(-1);
		Assert.assertTrue(status == 400);
	}

	@Test
	public void testFibonacciGeneratorSvcFailMaxRangeError() {
		FibonacciGeneratorSvcClient client = new FibonacciGeneratorSvcClient();
		int status = client.generateFibonacciSeries(58);
		Assert.assertTrue(status == 400);
	}
}
