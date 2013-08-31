package org.solovyev.problems.topcoder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.solovyev.problems.topcoder.PiecewiseLinearFunctionProblem.maximumSolutions;

/**
 * User: serso
 * Date: 8/31/13
 * Time: 11:31 PM
 */
public class PiecewiseLinearFunctionProblemTest {
	@Test
	public void testMaximumSolutions() throws Exception {
		assertEquals(1, maximumSolutions(new int[]{3, 2}));
		assertEquals(-1, maximumSolutions(new int[]{4, 4}));
		assertEquals(3, maximumSolutions(new int[]{1, 4, -1, 2}));
		assertEquals(5, maximumSolutions(new int[]{2, 1, 2, 1, 3, 2, 3, 2}));
		assertEquals(6, maximumSolutions(new int[]{125612666, -991004227, 0, 6, 88023, -1000000000, 1000000000, -1000000000, 1000000000}));
		assertEquals(49, maximumSolutions(new int[]{-111240669, -178446352, 997267413, -908731303, 217227569, -593681575, 580548370, -150547761, -91653041, -948529379, 3969627, -208395497, 174274211, -344731900, 765776247, -138520470, 278212760, -728912524, 891462713, -338251392, -14909347, -268682958, 869206008, -622911867, 323860653, -594980470, 971895768, -825007935, 794241623, -724900712, 90402732, -253871453, -99480799, -741930524, -64280538, -942097373, 201000592, -558175010, 289490452, -169578013, 900963588, -849966477, 900215252, -950162027, 397616943, -335646503, 290679930, -793119579, 315018653, -111240669}));
		assertEquals(48, maximumSolutions(new int[]{0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0}));
	}
}
