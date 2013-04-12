package edu.nyu.swl.impl;

import edu.nyu.swl.obj.RandomVectorGenerator;

/**
 * Decorator of NormalRandomVectorGenerator.
 * If the first time vector is generated as n1, ..., nk, the next time will be -n1, ..., -nk.
 * @author wenlingshi
 *
 */
public class AntiTheticRandomVectorGenerator implements
		RandomVectorGenerator {

	private RandomVectorGenerator generator;
	private double[] currentVec; // saves the previous vector if it is newly generated
	private boolean isAntiThetic; // flag to tell if the vector is anti-thetic or not
	
	public AntiTheticRandomVectorGenerator(RandomVectorGenerator generator) {
		super();
		this.generator = generator;
		this.isAntiThetic = false;
	}


	@Override
	public double[] getVector() {
		
		if (isAntiThetic == false) {
			isAntiThetic = true;
			currentVec = generator.getVector();
			return currentVec;
		}
		else {
			isAntiThetic = false;
			// This copy vector ensures elements in the vector will not be changed by external functions.
			double[] newVec = new double[currentVec.length];
			for (int i = 0; i < currentVec.length; ++ i) {
				newVec[i] = -currentVec[i];
			}
			return newVec;
		}
	}

}
