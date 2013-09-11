package org.solovyev.problems.googlecodejam.q2009;

import org.junit.Test;
import org.solovyev.common.text.Strings;

import static org.junit.Assert.assertEquals;
import static org.solovyev.problems.googlecodejam.q2009.Watersheds.solve;

/**
 * User: serso
 * Date: 9/1/13
 * Time: 2:46 PM
 */
public class WatershedsTest {

	private static final String lineSeparator = System.getProperty("line.separator");

	@Test
	public void testSolve() throws Exception {
		assertEquals("a b b" + lineSeparator +
				"a a b" + lineSeparator +
				"a a a", solve(new int[][]{
				{9, 6, 3},
				{5, 9, 6},
				{3, 5, 9}}, 3, 3));
		assertEquals("a a a a a a a a a b", solve(new int[][]{
				{0, 1, 2, 3, 4, 5, 6, 7, 8, 7}}, 1, 10));
		assertEquals("a a a" + lineSeparator +
				"b b b", solve(new int[][]{
				{7, 6, 7},
				{7, 6, 7}}, 2, 3));
		assertEquals("a b c d e f g h i j k l m" + lineSeparator +
				"n o p q r s t u v w x y z", solve(new int[][]{
				{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
				{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}}, 2, 13));

		assertEquals("a a a a a" + lineSeparator+
				"a a b b a" + lineSeparator+
				"a b b b a" + lineSeparator+
				"a b b b a" + lineSeparator+
				"a a a a a", solve(new int[][]{
				{1, 2, 3, 4, 5},
				{2, 9, 3, 9, 6},
				{3, 3, 0, 8, 7},
				{4, 9, 8, 9, 8},
				{5, 6, 7, 8, 9}}, 5, 5));
		assertEquals("a a a a a" + lineSeparator +
				"a a a a a" + lineSeparator +
				"a a a a a" + lineSeparator +
				"a a a a a" + lineSeparator +
				"a a a a a" + lineSeparator +
				"a a a a a" + lineSeparator +
				"a a a a a" + lineSeparator +
				"a a a a a", solve(new int[][]{
				{7, 6, 5, 4, 5},
				{6, 5, 4, 3, 4},
				{5, 4, 3, 2, 3},
				{4, 3, 2, 1, 2},
				{3, 2, 1, 0, 1},
				{4, 3, 2, 1, 2},
				{5, 4, 3, 2, 3},
				{6, 5, 4, 3, 4}}, 8, 5));
		assertEquals(Strings.convertStream(getClass().getResourceAsStream("B-small-practice.out")), solve(getClass().getResourceAsStream("B-small-practice.in")));
		assertEquals(Strings.convertStream(getClass().getResourceAsStream("B-large-practice.out")), solve(getClass().getResourceAsStream("B-large-practice.in")));
	}
}
