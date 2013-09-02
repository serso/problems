package org.solovyev.problems;

import javax.annotation.Nonnull;

import static java.lang.Math.max;

/**
 * User: serso
 * Date: 9/2/13
 * Time: 9:34 PM
 */
public class LongestCommonContagiousSubsequence {

	public static int findLccs(@Nonnull String l, @Nonnull String r) {
		return findLccs(l, r, 0, 0, new Integer[l.length()][r.length()]);
	}

	private static int findLccs(String l, String r, int li, int ri, @Nonnull Integer[][] memo) {
		if(li == l.length() || ri == r.length()) {
			return 0;
		}

		if(memo[li][ri] != null) {
			return memo[li][ri];
		}

		int counter = 0;
		int lik = li;
		int rik = ri;
		while(lik < l.length() && rik < r.length()) {
			if(l.charAt(lik) == r.charAt(rik)) {
				counter++;
				lik++;
				rik++;
			} else {
				break;
			}
		}
		int lCount = findLccs(l, r, li + 1, ri, memo);
		int rCount = findLccs(l, r, li, ri + 1, memo);

		memo[li][ri] = max(lCount, max(counter, rCount));
		return memo[li][ri];
	}
}
