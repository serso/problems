package org.solovyev.problems.topcoder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.solovyev.problems.topcoder.TeamsSelectionProblem.simulate;

/**
 * User: serso
 * Date: 8/21/13
 * Time: 11:40 PM
 */
public class TeamsSelectionProblemTest {

	@Test
	public void testSolve() throws Exception {
		assertEquals("1212", simulate(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}));
		assertEquals("211", simulate(new int[]{3, 2, 1}, new int[]{1, 3, 2}));
		assertEquals("212211", simulate(new int[]{6, 1, 5, 2, 3, 4}, new int[]{1, 6, 3, 4, 5, 2}));
		assertEquals("111122222112122222111211112221221121211", simulate(new int[]{3, 34, 11, 18, 30, 38, 13, 25, 36, 24, 21, 9, 32, 1, 23, 4, 19, 10, 26, 33, 15, 20, 8, 31, 27, 5, 14, 39, 29, 16, 22, 7, 17, 12, 35, 2, 28, 6, 37}, new int[]{9, 6, 18, 27, 29, 17, 22, 36, 32, 8, 31, 15, 12, 16, 30, 28, 35, 1, 38, 7, 23, 3, 24, 4, 34, 14, 21, 5, 37, 2, 10, 13, 39, 25, 11, 19, 26, 20, 33}));
		assertEquals("2221121121221111221211112211222", simulate(new int[]{21, 7, 27, 23, 19, 14, 26, 16, 10, 8, 28, 3, 15, 29, 13, 24, 5, 31, 1, 30, 18, 4, 22, 9, 11, 25, 12, 2, 6, 20, 17}, new int[]{30, 26, 7, 12, 3, 29, 1, 17, 11, 31, 28, 2, 10, 6, 18, 9, 16, 25, 14, 5, 24, 23, 15, 8, 21, 4, 19, 27, 20, 22, 13}));

	}
}
