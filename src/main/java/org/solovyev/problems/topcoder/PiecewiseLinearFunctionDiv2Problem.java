package org.solovyev.problems.topcoder;

// http://community.topcoder.com/stat?c=problem_statement&pm=12698
public class PiecewiseLinearFunctionDiv2Problem {

	public static final int INFINITE_SOLUTIONS = -1;

	public static int[] countSolutions(int[] ys, int[] queryYs) {
		final int[] result = new int[queryYs.length];

		for (int i = 0; i < ys.length; i++) {
			final int y = ys[i];
			for (int j = 0; j < queryYs.length; j++) {
				if (result[j] != INFINITE_SOLUTIONS) {
					final int queryY = queryYs[j];

					if(y == queryY) {
						result[j]++;
					}

					if(i > 0) {
						if (y == ys[i-1] && y == queryY) {
							result[j] = INFINITE_SOLUTIONS;
						} else if (y > queryY && queryY > ys[i-1]) {
							result[j]++;
						} else if (ys[i-1] > queryY && queryY > y) {
							result[j]++;
						}
					}
				}
			}

		}

		return result;
	}
}
