package org.solovyev.problems.googlecodejam.q2013.round1a;

import org.junit.Test;
import org.solovyev.problems.googlecodejam.GoogleCodeJamTestCase;
import org.solovyev.problems.googlecodejam.q2013.round1a.Bullseye;

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
		assertEquals("707106780", p.solve(BigInteger.valueOf(1L), BigInteger.valueOf(1000000000000000000L)).toString());
		assertEquals("49", p.solve(BigInteger.valueOf(10000000000000000L), BigInteger.valueOf(1000000000000000000L)).toString());
		assertEquals("3", p.solve(BigInteger.valueOf(3L), BigInteger.valueOf(40L)).toString());

		GoogleCodeJamTestCase.assertEquals(p, "A-small-practice.out", "A-small-practice.in");
		GoogleCodeJamTestCase.printResultToFile(p, "A-large-practice.out", "A-large-practice.in");
	}
}
