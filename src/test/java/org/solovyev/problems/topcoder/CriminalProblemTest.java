package org.solovyev.problems.topcoder;

import org.junit.Assert;
import org.junit.Test;

import static org.solovyev.problems.topcoder.CriminalProblem.numPseudonyms;

/**
 * User: serso
 * Date: 8/20/13
 * Time: 11:46 PM
 */
public class CriminalProblemTest {

	@Test
	public void testSolve() throws Exception {
		Assert.assertEquals(3, numPseudonyms(new String[]{"FRANK BOB", "FRANK GEORGE"}, new String[]{"WILLARD GREG", "GEORGE WILLARD"}));
		Assert.assertEquals(-1, numPseudonyms(new String[]{"ADAM FRANK","BOB SUZY"}, new String[]{"BRETT GEORGE", "BRETT TOM"}));
		Assert.assertEquals(-1, numPseudonyms(new String[]{"HARRY LLOYD","GEORGE BILL"}, new String[]{"FRANK THOMAS","GEORGE WILL","WILL FRANK"}));
		Assert.assertEquals(8, numPseudonyms(new String[] 	{"H G", "E F", "D A", "C G", "B A", "A G", "E G"}, new String[]{"A F", "B F", "C F", "C E", "C H", "G H", "C D"}));

	}
}
