package org.solovyev.problems.googlecodejam.q2013;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * User: serso
 * Date: 9/11/13
 * Time: 12:22 AM
 */
public class BullseyeTest {
	@Test
	public void testSolve() throws Exception {
		final Bullseye p = new Bullseye();
		assertEquals("1", p.solve(BigInteger.valueOf(1L), BigInteger.valueOf(9L)).toString());
		assertEquals("1", p.solve(BigInteger.valueOf(1L), BigInteger.valueOf(10L)).toString());
		assertEquals("3", p.solve(BigInteger.valueOf(1L), BigInteger.valueOf(40L)).toString());
	}
}
