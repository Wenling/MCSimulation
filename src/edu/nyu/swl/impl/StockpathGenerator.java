package edu.nyu.swl.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import edu.nyu.swl.obj.DatePrice;
import edu.nyu.swl.obj.RandomVectorGenerator;
import edu.nyu.swl.obj.StockPath;

/**
 * Used Geometric Brownian Motion to calculate series data of options
 * @author wenlingshi
 *
 */
public class StockpathGenerator implements StockPath {
	
	private static final double default_r = 0.0001; 
	private static final double default_sigma = 0.01; // Volatility per day
	private double r;
	private double sigma;
	private double initPrice;
	private RandomVectorGenerator rand;
	private RandomVectorGenerator anti;
	
	public StockpathGenerator(double price) {
		this.r = default_r;
		this.sigma = default_sigma;
		this.initPrice = price;
		this.rand = new NormalRandomVectorGenerator(0, 1);
		this.anti = new AntiTheticRandomVectorGenerator(rand);
//		this.anti = new NormalRandomVectorGenerator(0, 1);
	}
	
	public StockpathGenerator(double price, double r, double sigma) {
		this.r = r;
		this.sigma = sigma;
		this.initPrice = price;
		this.rand = new NormalRandomVectorGenerator(0, 1);
		this.anti = new AntiTheticRandomVectorGenerator(rand);
//		this.anti = new NormalRandomVectorGenerator(0, 1);
	}

	@Override
	public List<DatePrice> getPrices() {
		
		double[] randVec = anti.getVector();
		
		List<DatePrice> prices = new ArrayList<DatePrice>();
		prices.add(new DatePrice(new DateTime(0L), initPrice));
		for (int i = 1; i <= randVec.length; ++ i) {
			double price = generateGBM(prices.get(i - 1).getPrice(), randVec[i - 1]);
			prices.add(new DatePrice(new DateTime(i), price));
		}
		return prices;
	}
	
	/**
	 * Generate ith stock prices using the i-1th price prev and the random variable Z. The prices follow geometric brownian motion.
	 * @param prev
	 * @param Z
	 * @return
	 */
	private double generateGBM (double prev, double Z) {
		double St = prev * Math.exp(r - sigma * sigma / 2 + sigma * Z);
		return St;
	}
}
