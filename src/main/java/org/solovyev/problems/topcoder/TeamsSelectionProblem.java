package org.solovyev.problems.topcoder;

import javax.annotation.Nonnull;

// http://community.topcoder.com/stat?c=problem_statement&pm=12696
public class TeamsSelectionProblem {

	@Nonnull
	public static String simulate(int[] preference1, int[] preference2) {
		final int[] chosen = new int[preference1.length];

		int i = 0;
		int j = 0;
		while (i < preference1.length && j < preference2.length) {
			int p1 = -1;
			while (i < preference1.length) {
				p1 = preference1[i];
				i++;
				if (chosen[p1-1] == 0) {
					break;
				} else {
					p1 = -1;
				}
			}

			if(p1 != -1) {
				chosen[p1-1] = 1;
			}

			int p2 = -1;
			while (j < preference2.length) {
				p2 = preference2[j];
				j++;
				if (chosen[p2-1] == 0) {
					break;
				} else {
					p2 = -1;
				}
			}

			if(p2 != -1) {
				chosen[p2-1] = 2;
			}
		}

		final StringBuilder s = new StringBuilder();


		for (int i1 : chosen) {
			s.append(i1);
		}


		return s.toString();
	}
}
