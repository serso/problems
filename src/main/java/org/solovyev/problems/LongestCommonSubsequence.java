package org.solovyev.problems;

import javax.annotation.Nonnull;

/**
 * User: serso
 * Date: 9/2/13
 * Time: 9:34 PM
 */
public class LongestCommonSubsequence {

	public static int findLcs(@Nonnull String l, @Nonnull String r) {
		return findLcs(l, r, 0, 0, new Integer[l.length()][r.length()]);
	}

	private static int findLcs(String l, String r, int li, int ri, @Nonnull Integer[][] memo) {
		if(li == l.length() || ri == r.length()) {
			return 0;
		}

		if(memo[li][ri] != null) {
			return memo[li][ri];
		}

		final int result;
		if(l.charAt(li) == r.charAt(ri)) {
			result = findLcs(l, r, li + 1, ri + 1, memo) + 1;
		} else {
			final int lCount = findLcs(l, r, li + 1, ri, memo);
			final int rCount = findLcs(l, r, li, ri + 1, memo);
			result = Math.max(lCount, rCount);
		}

		memo[li][ri] = result;

		return result;
	}
}
