package edu.nyu.swl.impl;

/**
 * keep track of stats like average and standard deviation
 * @author wenlingshi
 *
 */
public class StatsCollector {
	
	private double miu;
	private double xSquare;
	private double sigmaSquare;
	private double count;
	
	public StatsCollector() {
		this.miu = 0;
		this.xSquare = 0;
		this.sigmaSquare = 0;
		this.count = 0;
	}

	public double getMiu() {
		return miu;
	}

	public double getxSquare() {
		return xSquare;
	}

	public double getSigmaSquare() {
		return sigmaSquare;
	}

	public double getCount() {
		return count;
	}

	public void update (double payout) {
		++ count;
		miu = (count - 1) / count * miu  + 1 / count * payout;
		xSquare = (count - 1) / count * xSquare + 1 / count * payout * payout;
		sigmaSquare = xSquare - miu * miu;
	}

}
