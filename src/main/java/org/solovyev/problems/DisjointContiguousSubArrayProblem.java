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

import javax.annotation.Nonnull;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * User: serso
 * Date: 8/5/13
 * Time: 12:22 AM
 */

// see http://www.codechef.com/JUNE13/problems/DELISH
// or
// http://www.careercup.com/question?id=19286747
final class DisjointContiguousSubArrayProblem {

	static int solve(final int[] a) {
		final int n = a.length;
		if (n == 0) {
			return 0;
		}

		final Sums sums = calculateSums(a);
		final MaxsMins maxsMins = calculateMaxsMins(sums, n);
		return calculateMaxDiff(maxsMins, n);
	}

	private static int calculateMaxDiff(@Nonnull MaxsMins maxsMins, int n) {
		int result = Integer.MIN_VALUE;
		for (int i = 1; i < n - 1; i++) {
			final int max1 = abs(maxsMins.rightMaxs[i + 1] - maxsMins.leftMins[i - 1]);
			final int max2 = abs(maxsMins.rightMins[i + 1] - maxsMins.leftMaxs[i - 1]);
			result = max(result, max(max1, max2));
		}
		return result;
	}

	@Nonnull
	static MaxsMins calculateMaxsMins(@Nonnull Sums sums, int n) {
		final MaxsMins result = new MaxsMins(n);
		result.leftMaxs[0] = sums.left[0];
		result.leftMins[0] = sums.left[0];
		result.rightMaxs[n - 1] = sums.right[n - 1];
		result.rightMins[n - 1] = sums.right[n - 1];
		for (int i = 1; i < n; i++) {
			result.leftMaxs[i] = max(result.leftMaxs[i - 1], sums.left[i]);
			result.leftMins[i] = min(result.leftMins[i - 1], sums.left[i]);
			result.rightMaxs[n - i - 1] = max(result.rightMaxs[n - i], sums.right[n - i - 1]);
			result.rightMins[n - i - 1] = min(result.rightMins[n - i], sums.right[n - i - 1]);
		}
		return result;
	}

	@Nonnull
	static Sums calculateSums(int[] a) {
		final int n = a.length;
		final Sums sums = new Sums(n);
		sums.left[0] = a[0];
		sums.right[n - 1] = a[n - 1];

		for (int i = 1; i < n; i++) {
			sums.left[i] = sums.left[i - 1] + a[i];
			sums.right[n - i - 1] = sums.right[n - i] + a[n - i - 1];
		}

		return sums;
	}

	static final class Sums {
		final int[] left;
		final int[] right;

		private Sums(int n) {
			this.left = new int[n];
			this.right = new int[n];
		}
	}

	static final class MaxsMins {
		final int[] leftMaxs;
		final int[] rightMaxs;
		final int[] leftMins;
		final int[] rightMins;

		private MaxsMins(int n) {
			leftMaxs = new int[n];
			rightMaxs = new int[n];
			leftMins = new int[n];
			rightMins = new int[n];
		}
	}
}
