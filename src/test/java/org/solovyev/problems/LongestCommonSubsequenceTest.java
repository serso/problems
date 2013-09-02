package org.solovyev.problems;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.solovyev.problems.LongestCommonSubsequence.findLcs;

/**
 * User: serso
 * Date: 9/2/13
 * Time: 9:39 PM
 */
public class LongestCommonSubsequenceTest {
	@Test
	public void testFindLcs() throws Exception {
		assertEquals(3, findLcs("test", "est"));
		assertEquals(4, findLcs("ABCDEFG", "BCDGK"));
		assertEquals(43, findLcs("sdfaeorgeirjghduifhg diufohgaeorghaodhg dfjh dajfghajd hajghler aerga'gaegpergqe[ adl ndfnblr", "dfgljhguidfh goheariguhldjhgadfkjg;aeoirgh dfbgahdjbgerlgjeoighdfib cjnbldfgn eriugndafk g"));
	}
}
