package edu.nyu.swl.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;

import org.apache.commons.math3.distribution.NormalDistribution;

import edu.nyu.swl.obj.Payout;
import edu.nyu.swl.obj.StockPath;

public class PriceSimulator {
	
	

	/**
	 * The current market conditions are: the price of IBM is 152.35, the volatility sigma is 0.01 per day, and r = 0.0001. 
	 * What is the price of a call option that ends 252 days into the future and the strike price is 165.
	 * @param criteria is used to calculate the current error 
	 * @param error is the absolute error to determins when to stop simulation
	 * @return the average payout as the option price
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	
	public static double simulate(double initPrice, double strikePrice, double criteria, double error, Class<? extends AbstractPayout> payoutClazz) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		StockPath path = new StockpathGenerator(initPrice);
		Constructor<? extends AbstractPayout> payoutCon = payoutClazz.getConstructor(double.class);
		Payout payout = payoutCon.newInstance(strikePrice);
		StatsCollector sc = new StatsCollector();
		
		double calPrice = payout.getPayout(path);
		double converged = 1.0;
		sc.update(calPrice);
		
		do {
			calPrice = payout.getPayout(path);
			sc.update(calPrice);
//			System.out.println("#" + sc.getCount() + ":  " + sc.getMiu() + " variance: " + sc.getSigmaSquare());
			converged = criteria * Math.sqrt(sc.getSigmaSquare() / sc.getCount());
		} while (converged > error || sc.getCount() < 10000);
		System.out.println("Final option value: " + sc.getMiu() + " variance: " + sc.getSigmaSquare() + " # of iterations: " + sc.getCount());
		double price = sc.getMiu() * Math.exp(-0.0001 * 252);
		System.out.println("Final option price: " + price);
		return sc.getMiu();
	}
	
	
	/*
	 * Scenario 1: 
	 * Final option value: 6.373568401407995 variance: 169.51207422722717 
	 * Final option price: 6.214961320312217 
	 * Scenario 2:
	 * Final option value: 2.2367321328115874 variance: 32.06617438669908 
	 * Final option price: 2.1810707619067093 
	 * 191736 millisecond used..
	 */
	public static void main(String[] args) throws SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		
		long start = System.currentTimeMillis();
		double probability = 0.96;
		double criteria = - new NormalDistribution().inverseCumulativeProbability( (1 - probability) / 2 ) ;
		double error = 0.01;
		
		double initPrice = 152.35;
		double strikePrice = 165;
		
		System.out.println("Scenario 1:");
		simulate(initPrice, strikePrice, criteria, error, CallOptionPayout.class);
		
		initPrice = 152.35;
		strikePrice = 164;

		System.out.println("Scenario 2:");
		simulate(initPrice, strikePrice, criteria, error, AsianCallPayout.class);
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println(duration + " millisecond used..");
	}
}
