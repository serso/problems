package org.solovyev.problems;

import javax.annotation.Nonnull;

public class GoldPotsProblem {

	static int solve(@Nonnull int[] pots) {
		return findMaxCoins(pots, 0, pots.length - 1);
	}

	private static int findMaxCoins(int[] pots, int start, int end) {
		if(start > end) {
			return 0;
		} else {
			final int a = pots[start] + Math.min(findMaxCoins(pots, start + 2, end), findMaxCoins(pots, start + 1, end - 1));
			final int b = pots[end] + Math.min(findMaxCoins(pots, start + 1, end - 1), findMaxCoins(pots, start, end - 2));

			return Math.max(a, b);
		}
	}
}
