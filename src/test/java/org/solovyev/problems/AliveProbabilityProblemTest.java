package org.solovyev.problems;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.solovyev.problems.AliveProbabilityProblem.solve;

public class AliveProbabilityProblemTest {

	public static final double ERROR = 0.001;

	@Test
	public void testSolve() throws Exception {
		assertEquals(0, solve(0, 0, 1, 1), ERROR);
		assertEquals(0, solve(0, 0, 1, 2), ERROR);
		assertEquals(0.5, solve(0, 0, 2, 1), ERROR);
		assertEquals(0.5, solve(0, 0, 4, 1), ERROR);
		assertEquals(0.75, solve(0, 1, 4, 1), ERROR);
		assertEquals(1, solve(1, 1, 4, 1), ERROR);
	}
}
