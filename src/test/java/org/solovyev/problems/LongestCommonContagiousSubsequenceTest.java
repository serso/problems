package org.solovyev.problems;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.solovyev.problems.LongestCommonContagiousSubsequence.findLccs;

/**
 * User: serso
 * Date: 9/2/13
 * Time: 9:47 PM
 */
public class LongestCommonContagiousSubsequenceTest {
	@Test
	public void testFindLccs() throws Exception {
		assertEquals(3, findLccs("test", "est"));
		assertEquals(3, findLccs("ABCDEFG", "BCDGK"));
		assertEquals(6, findLccs("sdfaeorgeirjghduifhg diufohgaeorghaodhg dfjh qwertydajfghajd hajghler aerga'gaegpergqe[ adl ndfnblr", "dfgljhguidfh goheariguhldjhgadfkjg;aeoirgh dfbgahdjbgerlgjeoighdfib cjnbldfgn eriugndafk gqwerty"));
	}
}
