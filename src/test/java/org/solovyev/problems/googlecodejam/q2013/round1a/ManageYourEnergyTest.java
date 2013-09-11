package org.solovyev.problems.googlecodejam.q2013.round1a;

import org.junit.Assert;
import org.junit.Test;

public class ManageYourEnergyTest {

	@Test
	public void testSolve() throws Exception {
		final ManageYourEnergy p = new ManageYourEnergy();
		Assert.assertEquals(12, p.solve(5, 2, new long[]{2, 1}));
		Assert.assertEquals(12, p.solve(5, 2, new long[]{1, 2}));
		Assert.assertEquals(39, p.solve(3, 3, new long[]{4, 1, 3, 5}));
	}
}
