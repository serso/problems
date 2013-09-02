package org.solovyev.problems.googlecodejam.q2009;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.solovyev.problems.googlecodejam.q2009.AlienLanguage.getWords;

/**
 * User: serso
 * Date: 9/1/13
 * Time: 1:16 PM
 */
public class AlienLanguageTest {

	@Test
	public void testSolve() throws Exception {
		assertEquals("[acd, bcd, ace, bce]", getWords("(ab)c(de)").toString());
		assertEquals("[nwlrnbmdbh, nwlrqbmdbh, nwlrxbmdbh, nwlrbbmdbh, nwlrnbmgbh, nwlrqbmgbh, nwlrxbmgbh, nwlrbbmgbh, nwlrnbmqbh, nwlrqbmqbh, nwlrxbmqbh, nwlrbbmqbh, nwlrnbmwbh, nwlrqbmwbh, nwlrxbmwbh, nwlrbbmwbh]", getWords("nwlr(nqxb)bm(dgqw)bh").toString());
		//System.out.println(solve(AlienLanguageTest.class.getResourceAsStream("A-small-practice.in")));
		//System.out.println(solve(AlienLanguageTest.class.getResourceAsStream("A-large-practice.in")));
	}
}
