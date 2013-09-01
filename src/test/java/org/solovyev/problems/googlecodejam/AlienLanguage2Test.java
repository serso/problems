package org.solovyev.problems.googlecodejam;

import org.junit.Assert;
import org.junit.Test;
import org.solovyev.common.text.Strings;

import javax.annotation.Nonnull;

import static org.solovyev.problems.googlecodejam.AlienLanguage2.getPattern;
import static org.solovyev.problems.googlecodejam.AlienLanguage2.solve;

/**
 * User: serso
 * Date: 9/1/13
 * Time: 1:49 PM
 */
// http://code.google.com/codejam/contest/dashboard?c=90101#s=p0&a=1
public class AlienLanguage2Test {
	@Test
	public void testSolve() throws Exception {
		System.out.println(toString(getPattern("(ab)c(de)", 3)));
		System.out.println(toString(getPattern("nwlr(nqxb)bm(dgqw)bh", 10)));
		Assert.assertEquals(Strings.convertStream(AlienLanguage2Test.class.getResourceAsStream("A-small-practice.out")), solve(AlienLanguage2Test.class.getResourceAsStream("A-small-practice.in")));
		Assert.assertEquals(Strings.convertStream(AlienLanguage2Test.class.getResourceAsStream("A-large-practice.out")), solve(AlienLanguage2Test.class.getResourceAsStream("A-large-practice.in")));
	}

	@Nonnull
	private static String toString(boolean[][] pattern) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pattern.length; i++) {
			sb.append(i).append(":\t");
			boolean[] booleans = pattern[i];
			for (boolean aBoolean : booleans) {
				sb.append(aBoolean ? 1 : " ").append(" ");
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}
