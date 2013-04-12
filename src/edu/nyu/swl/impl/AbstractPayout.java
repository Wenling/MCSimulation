package edu.nyu.swl.impl;

import edu.nyu.swl.obj.Payout;

/**
 * AbstractPayout has common variable strikePrice for each payout descendant to use.
 * @author wenlingshi
 *
 */
public abstract class AbstractPayout implements Payout {
	
	protected double strikePrice;
	
	public AbstractPayout(double strikePrice) {
		this.strikePrice = strikePrice;
	}
	
//	public abstract double getPayout(StockPath path);
}
