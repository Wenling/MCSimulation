package edu.nyu.swl.obj;

/**
 * The interface for calculating the payout function
 * @author wenlingshi
 *
 */
public interface Payout {
	public double getPayout(StockPath path);

}
