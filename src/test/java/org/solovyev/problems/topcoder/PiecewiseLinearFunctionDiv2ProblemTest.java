package org.solovyev.problems.topcoder;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.solovyev.problems.topcoder.PiecewiseLinearFunctionDiv2Problem.countSolutions;

/**
 * User: serso
 * Date: 8/31/13
 * Time: 11:07 PM
 */
public class PiecewiseLinearFunctionDiv2ProblemTest {

	@Test
	public void testCountSolutions() throws Exception {
		assertArrayEquals(new int[]{0, 1, 2, 3}, countSolutions(new int[]{1, 4, -1, 2}, new int[]{-2, -1, 0, 1}));
		assertArrayEquals(new int[]{0, -1, 0}, countSolutions(new int[]{0, 0}, new int[]{-1, 0, 1}));
		assertArrayEquals(new int[]{3, 4, 5, 4, 3, 3, 0 }, countSolutions(new int[]{2, 4, 8, 0, 3, -6, 10}, new int[]{0, 1, 2, 3, 4, 0, 65536}));
		assertArrayEquals(new int[]{8, 6, 3, 0, 7, 1, 4, 8, 3, 4 }, countSolutions(new int[]{-178080289, -771314989, -237251715, -949949900, -437883156, -835236871, -316363230, -929746634, -671700962}, new int[]{-673197622, -437883156, -251072978, 221380900, -771314989, -949949900, -910604034, -671700962, -929746634, -316363230}));
	}
}
