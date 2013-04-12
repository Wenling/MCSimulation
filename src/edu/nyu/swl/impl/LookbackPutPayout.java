package edu.nyu.swl.impl;

import edu.nyu.swl.obj.DatePrice;
import edu.nyu.swl.obj.StockPath;

public class LookbackPutPayout extends AbstractPayout {

	public LookbackPutPayout(double strikePrice) {
		super(strikePrice);
	}

	@Override
	public double getPayout(StockPath path) {
		double min = Double.POSITIVE_INFINITY;
		for (DatePrice dp : path.getPrices()) {
			if (min < dp.getPrice()) {
				min = dp.getPrice();
			}
		}
		double payout = strikePrice - min;
		return payout < 0 ? 0 : payout;
	}

}
