package org.solovyev.problems;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.solovyev.problems.GoldPotsProblem.solve;

public class GoldPotsProblemTest {

	@Test
	public void testSolve() throws Exception {
		assertEquals(4, solve(new int[]{1, 2, 3}));
		assertEquals(7, solve(new int[]{4, 2, 3, 4}));
		assertEquals(23, solve(new int[]{9, 19, 3, 4}));
	}
}
