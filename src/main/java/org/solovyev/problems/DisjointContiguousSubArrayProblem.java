package org.solovyev.problems;

import javax.annotation.Nonnull;

import static java.lang.Math.abs;
import static java.lang.Math.max;

// see http://www.codechef.com/JUNE13/problems/DELISH
// or
// http://www.careercup.com/question?id=19286747
final class DisjointContiguousSubArrayProblem {

	static int solve(final int[] a) {
		final int n = a.length;
		if (n == 0) {
			return 0;
		}

		final MaxsMins maxsMins = calculateMaxsMins(a, n);
		return calculateMaxDiff(maxsMins, n);
	}

	private static int calculateMaxDiff(@Nonnull MaxsMins maxsMins, int n) {
		int result = Integer.MIN_VALUE;
		for (int i = 1; i < n; i++) {
			final int max1 = abs(maxsMins.rightMaxs[i] - maxsMins.leftMins[i - 1]);
			final int max2 = abs(maxsMins.rightMins[i] - maxsMins.leftMaxs[i - 1]);
			result = max(result, max(max1, max2));
		}
		return result;
	}

	@Nonnull
	static MaxsMins calculateMaxsMins(@Nonnull int[] a, int n) {
		final MaxsMins result = new MaxsMins(n);
		result.leftMaxs[0] = a[0];
		result.leftMins[0] = a[0];
		result.rightMaxs[n - 1] = a[n - 1];
		result.rightMins[n - 1] = a[n - 1];

		for (int i = 1; i < n; i++) {
			result.leftMaxs[i] = Math.max(0, result.leftMaxs[i - 1]) + a[i];
			result.leftMins[i] = Math.min(0, result.leftMins[i - 1]) + a[i];
			result.rightMaxs[n - i - 1] = Math.max(0, result.rightMaxs[n - i]) + a[n - i - 1];
			result.rightMins[n - i - 1] = Math.min(0, result.rightMins[n - i]) + a[n - i - 1];
		}
		return result;
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
