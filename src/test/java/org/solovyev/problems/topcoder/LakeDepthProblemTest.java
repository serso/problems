package org.solovyev.problems.topcoder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.solovyev.problems.topcoder.LakeDepthProblem.depth;

public class LakeDepthProblemTest {

	@Test
	public void testSolve() throws Exception {
		assertEquals(1, depth(new String[]{"222", "212", "222"}));
		assertEquals(0, depth(new String[]{"222", "232", "222"}));
		assertEquals(4, depth(new String[]{"555", "515", "525", "555"}));
		assertEquals(1, depth(new String[]{"5255", "5225", "5525", "5515", "5555"}));
		assertEquals(8, depth(new String[]{"55555","59995","59595","59195","59995","55555"}));
		assertEquals(0, depth(new String[]{"55555", "59995", "59A95", "59A95", "59995", "55555"}));

	}
}
