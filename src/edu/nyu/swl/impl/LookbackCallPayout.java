package edu.nyu.swl.impl;

import edu.nyu.swl.obj.DatePrice;
import edu.nyu.swl.obj.StockPath;

public class LookbackCallPayout extends AbstractPayout {
	

	public LookbackCallPayout(double strikePrice) {
		super(strikePrice);
	}

	@Override
	public double getPayout(StockPath path) {
		double max = Double.NEGATIVE_INFINITY;
		for (DatePrice dp : path.getPrices()) {
			if (max < dp.getPrice()) {
				max = dp.getPrice();
			}
		}
		double payout = max - strikePrice;
		return payout < 0 ? 0 : payout;
	}

}
