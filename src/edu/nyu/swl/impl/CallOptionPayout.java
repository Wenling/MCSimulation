package edu.nyu.swl.impl;

import java.util.List;

import edu.nyu.swl.obj.DatePrice;
import edu.nyu.swl.obj.StockPath;

/**
 * Payout of Europian call is (S(T)-K)+
 * @author wenlingshi
 *
 */
public class CallOptionPayout extends AbstractPayout{
	
	public CallOptionPayout(double strikePrice) {
		super(strikePrice);
	}

	/**
	 * When path size is zero, throws exception
	 */
	@Override
	public double getPayout(StockPath path) {
		List<DatePrice> prices = path.getPrices();
		
		if (prices.size() <= 0) throw new ArithmeticException();
		
		double lastPrice = prices.get(prices.size() - 1).getPrice();
		double payout = lastPrice - strikePrice;
		if (payout < 0)
			return 0;
		return payout;
	}

}
