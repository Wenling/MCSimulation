package edu.nyu.swl.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import edu.nyu.swl.impl.AsianCallPayout;
import edu.nyu.swl.impl.StockpathGenerator;
import edu.nyu.swl.obj.DatePrice;
import edu.nyu.swl.obj.Payout;

public class TestAsianCall {

	@Test (expected = ArithmeticException.class)
	public void testGetPayout_NoPrice() {
		
		double strikePrice = 2;
		List<DatePrice> samplePath = new ArrayList<DatePrice>();
		StockpathGenerator mockedPath = mock(StockpathGenerator.class);
		when(mockedPath.getPrices()).thenReturn(samplePath);

		Payout callOp = new AsianCallPayout(strikePrice);
		double payout = callOp.getPayout(mockedPath);
		
	}
	
	@Test
	public void testGetPayout_MonoTonicIncrease() {
		
		double strikePrice = 2;
		double[] prices = {1, 3, 5};
		double payout = getValue(strikePrice, prices);
		
		assertEquals(payout, 1, 0.000001);
		
	}
	
	@Test
	public void testGetPayout_MonoTonicDecrease() {
		
		double strikePrice = 2;
		double[] prices = {5, 3, 1};
		double payout = getValue(strikePrice, prices);
		
		assertEquals(payout, 1, 0.000001);
		
	}
	
	@Test
	public void testGetPayout_Negative() {
		
		double strikePrice = 0;
		double[] prices = {1, 5, 3};
		double payout = getValue(strikePrice, prices);
		
		assertEquals(payout, 3, 0.000001);
		
	}
	
	@Test
	public void testGetPayout_Zero() {
		
		double strikePrice = 10;
		double[] prices = {-2, 0, 2};
		double payout = getValue(strikePrice, prices);
		
		assertEquals(payout, 0, 0.000001);
		
	}

	public double getValue(double strikePrice, double[] prices) {

		List<DatePrice> samplePath = new ArrayList<DatePrice>();
		for (int i = 0; i < prices.length; ++i) {
			samplePath.add(new DatePrice(new DateTime(i), prices[i]));
		}

		StockpathGenerator mockedPath = mock(StockpathGenerator.class);
		when(mockedPath.getPrices()).thenReturn(samplePath);

		Payout callOp = new AsianCallPayout(strikePrice);
		double payout = callOp.getPayout(mockedPath);
		System.out.println(payout);
		return payout;
	}

}
