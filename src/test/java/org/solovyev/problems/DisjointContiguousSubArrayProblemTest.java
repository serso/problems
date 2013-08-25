/*
 * Copyright 2013 serso aka se.solovyev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Contact details
 *
 * Email: se.solovyev@gmail.com
 * Site:  http://se.solovyev.org/java/jcl
 */

package org.solovyev.problems;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.solovyev.problems.DisjointContiguousSubArrayProblem.MaxsMins;
import static org.solovyev.problems.DisjointContiguousSubArrayProblem.Sums;
import static org.solovyev.problems.DisjointContiguousSubArrayProblem.calculateMaxsMins;
import static org.solovyev.problems.DisjointContiguousSubArrayProblem.calculateSums;
import static org.solovyev.problems.DisjointContiguousSubArrayProblem.solve;

/**
 * User: serso
 * Date: 8/5/13
 * Time: 12:23 AM
 */
public class DisjointContiguousSubArrayProblemTest {

	@Test
	public void testSolve() throws Exception {
		assertEquals(16, solve(new int[]{2, -1, -2, 1, -4, 2, 8}));
		assertEquals(4, solve(new int[]{1, 1, -1, -1}));
		assertEquals(13, solve(new int[]{1, 2, 3, 4, 5}));
		assertEquals(19, solve(new int[]{1, 2, 3, 4, 5, 6}));
	}

	@Test
	public void testShouldHaveCorrectSums() throws Exception {
		assertSumsAreCorrect(new int[]{0}, new int[]{0}, new int[]{0});
		assertSumsAreCorrect(new int[]{0, 1}, new int[]{0, 1}, new int[]{1, 1});
		assertSumsAreCorrect(new int[]{-1, -2, -3}, new int[]{-1, -3, -6}, new int[]{-6, -5, -3});
		assertSumsAreCorrect(new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 3, 6, 10, 15, 21}, new int[]{21, 20, 18, 15, 11, 6});
		assertSumsAreCorrect(new int[]{2, -1, -2, 1, -4, 2, 8}, new int[]{2, 1, -1, 0, -4, -2, 6}, new int[]{6, 4, 5, 7, 6, 10, 8});
	}

	@Test
	public void testShouldHaveCorrectMaxsMins() throws Exception {
		MaxsMins maxsMins = calculateMaxsMins(calculateSums(new int[]{1, 2, 3, 4, 5, 6}), 6);
		assertArrayEquals(new int[]{1, 3, 6, 10, 15, 21}, maxsMins.leftMaxs);
		assertArrayEquals(new int[]{1, 1, 1, 1, 1, 1}, maxsMins.leftMins);
		assertArrayEquals(new int[]{21, 20, 18, 15, 11, 6}, maxsMins.rightMaxs);
		assertArrayEquals(new int[]{6, 6, 6, 6, 6, 6}, maxsMins.rightMins);

		maxsMins = calculateMaxsMins(calculateSums(new int[]{2, -1, -2, 1, -4, 2, 8}), 7);
		assertArrayEquals(new int[]{2, 2, 2, 2, 2, 2, 6}, maxsMins.leftMaxs);
		assertArrayEquals(new int[]{2, 1, -1, -1, -4, -4, -4}, maxsMins.leftMins);
		assertArrayEquals(new int[]{10, 10, 10, 10, 10, 10, 8}, maxsMins.rightMaxs);
		assertArrayEquals(new int[]{4, 4, 5, 6, 6, 8, 8}, maxsMins.rightMins);
	}

	private void assertSumsAreCorrect(int[] a, int[] expectedLeftSums, int[] expectedRightSums) {
		final Sums sums = calculateSums(a);
		assertArrayEquals(expectedLeftSums, sums.left);
		assertArrayEquals(expectedRightSums, sums.right);
	}
}
