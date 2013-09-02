package org.solovyev.problems.googlecodejam.q2013;

import org.junit.Test;
import org.solovyev.common.text.Strings;

import static org.junit.Assert.assertEquals;

/**
 * User: serso
 * Date: 9/2/13
 * Time: 10:59 PM
 */
public class TicTacToeTomekTest {

	@Test
	public void testSolve() throws Exception {
		TicTacToeTomek p = new TicTacToeTomek();
		assertEquals("X won", p.solve(new char[][]{
				{'X', 'X', 'X', 'T'},
				{'.', '.', '.', '.'},
				{'O', 'O', '.', '.'},
				{'.', '.', '.', '.'}}));

		assertEquals(Strings.convertStream(getClass().getResourceAsStream("A-small-practice.out")), p.solve(getClass().getResourceAsStream("A-small-practice.in")));
		assertEquals(Strings.convertStream(getClass().getResourceAsStream("A-large-practice.out")), p.solve(getClass().getResourceAsStream("A-large-practice.in")));
	}
}
