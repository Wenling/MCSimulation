package edu.nyu.swl.impl;

import org.apache.commons.math3.distribution.NormalDistribution;

import edu.nyu.swl.obj.RandomVectorGenerator;

/**
 * Generates a vector distributed by Gaussian distribution with 252 elements by default 
 * @author wenlingshi
 *
 */
public class NormalRandomVectorGenerator implements RandomVectorGenerator {
	
	private static final int defaultLength = 252;
	private NormalDistribution nd;
	
	public NormalRandomVectorGenerator(double expectation, double variance) {
		super();
		this.nd = new NormalDistribution(expectation, variance);
	}

	/*
	 * Adapter to make safer use of generating vector
	 * @see edu.nyu.swl.obj.RandomVectorGenerator#getVector()
	 */
	@Override
	public double[] getVector() {
		
		return getVector(defaultLength);
	}
	
	public double[] getVector(int n) {
		
		double[] vec = new double[n];
		for (int i = 0; i < n; ++ i) {
			vec[i] = nd.sample();
		}
		return vec;
	}

}
