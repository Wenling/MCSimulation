package edu.nyu.swl.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;

import edu.nyu.swl.impl.AntiTheticRandomVectorGenerator;
import edu.nyu.swl.impl.NormalRandomVectorGenerator;
import edu.nyu.swl.obj.RandomVectorGenerator;

public class TestAntiTheticRandomVector {

	@Test
	public void testGetVector() {
//		double[] vectors = {0, 0, 0};
//		double[] vectors_neg = {0, 0, 0};
		double[] vectors = {1, -2, 3};
		double[] vectors_neg = {-1, 2, -3};
		RandomVectorGenerator vec = mock(NormalRandomVectorGenerator.class);
		when(vec.getVector()).thenReturn(Arrays.copyOf(vectors, vectors.length));
		RandomVectorGenerator antiVec = new AntiTheticRandomVectorGenerator(vec);

		double[] firstTime = antiVec.getVector();
		for (int i = 0; i < firstTime.length; ++ i) {
			System.out.println(firstTime[i]);
		}
		assertArrayEquals(vectors, firstTime, 0);
		double[] secondTime = antiVec.getVector();
		for (int i = 0; i < secondTime.length; ++ i) {
			System.out.println(secondTime[i]);
		}
		assertArrayEquals(vectors_neg, secondTime, 0);
		firstTime = antiVec.getVector();
		for (int i = 0; i < firstTime.length; ++ i) {
			System.out.println(firstTime[i]);
		}
		assertArrayEquals(vectors, firstTime, 0);
	}

}
