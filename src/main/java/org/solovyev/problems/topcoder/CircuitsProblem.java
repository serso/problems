package org.solovyev.problems.topcoder;

import org.solovyev.common.text.Strings;

import javax.annotation.Nonnull;

// http://community.topcoder.com/stat?c=problem_statement&pm=1593
public class CircuitsProblem {

	public static int howLong(@Nonnull String[] connects, @Nonnull String[] costs) {
		int length = 0;

		final int m[][] = calculateAdjacentMatrix(connects, costs);

		for (int i = 0; i < connects.length; i++) {
			final String connect = connects[i];
			if (connect.isEmpty()) {
				// last element in the circuit => the one with probably largest path to it
				// => we need to examine al it's paths
				length = Math.max(length, howLong(m, i));
			}
		}


		return length;
	}

	private static int[][] calculateAdjacentMatrix(String[] connects, String[] costs) {
		final int m[][] = new int[connects.length][connects.length];

		for (int i = 0; i < connects.length; i++) {
			final String connect = connects[i];
			final String cost = costs[i];

			final String[] adjacentElements = connect.split(" ");
			final String[] adjacentElementCosts = cost.split(" ");

			for (int j = 0; j < adjacentElements.length; j++) {
				if (!Strings.isEmpty(adjacentElements[j])) {
					final Integer k = Integer.valueOf(adjacentElements[j]);
					m[i][k] = Integer.valueOf(adjacentElementCosts[j]);
				}
			}
		}

		return m;
	}

	private static int howLong(@Nonnull int[][] m, int i) {
		int length = 0;

		for (int j = 0; j < m.length; j++) {
			if (m[j][i] > 0) {
				length = Math.max(length, m[j][i] + howLong(m, j));
			}
		}

		return length;
	}
}
