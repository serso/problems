package org.solovyev.problems.googlecodejam.q2013;

import org.junit.Assert;
import org.junit.Test;
import org.solovyev.common.text.Strings;

/**
 * User: serso
 * Date: 9/9/13
 * Time: 9:50 PM
 */
public class LawnmowerTest {
	@Test
	public void testSolve() throws Exception {
		Lawnmower l = new Lawnmower();
		Assert.assertEquals("YES", l.solve(new int[][]{
				{2, 1, 2},
				{1, 1, 1},
				{2, 1, 2}},
				new int[][]{
						{100, 100, 100},
						{100, 100, 100},
						{100, 100, 100}}).toString());

		Assert.assertEquals("NO", l.solve(new int[][]{
				{2, 1, 2},
				{1, 2, 1},
				{2, 1, 2}},
				new int[][]{
						{100, 100, 100},
						{100, 100, 100},
						{100, 100, 100}}).toString());

		Assert.assertEquals(Strings.convertStream(getClass().getResourceAsStream("B-small-practice.out")), l.solve(getClass().getResourceAsStream("B-small-practice.in")));
		Assert.assertEquals(Strings.convertStream(getClass().getResourceAsStream("B-large-practice.out")), l.solve(getClass().getResourceAsStream("B-large-practice.in")));
	}
}
