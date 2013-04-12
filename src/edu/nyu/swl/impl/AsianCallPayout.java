package edu.nyu.swl.impl;

import java.util.List;

import edu.nyu.swl.obj.DatePrice;
import edu.nyu.swl.obj.StockPath;

/**
 * Payout of Asian Call is (Avg(S)-K)+
 * @author wenlingshi
 *
 */
public class AsianCallPayout extends AbstractPayout {

	public AsianCallPayout(double strikePrice) {
		super(strikePrice);
	}

	/**
	 * When path size is zero, throws exception
	 */
	@Override
	public double getPayout(StockPath path) {
		List<DatePrice> prices = path.getPrices();
		
		if (prices.size() <= 0) throw new ArithmeticException();
		double total = 0;
		for (DatePrice dp : prices) {
			total += dp.getPrice();
		}
		double avg = total / prices.size();
		double payout = avg - this.strikePrice;
		if (payout < 0)
			return 0;
		return payout;
	}

}
