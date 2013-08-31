package org.solovyev.problems.topcoder;

/**
 * User: serso
 * Date: 8/31/13
 * Time: 10:58 PM
 */
// http://community.topcoder.com/stat?c=problem_statement&pm=12691
public class PiecewiseLinearFunctionProblem {

	public static final int INFINITE_SOLUTIONS = -1;

	public static int maximumSolutions(int[] ys) {
		for (int i = 0; i < ys.length; i++) {
			ys[i] = 2 * ys[i];
		}

		for (int i = 1; i < ys.length; i++) {
			if(ys[i] == ys[i-1]) {
				return INFINITE_SOLUTIONS;
			}
		}

		int result = 0;
		for (int y : ys) {
			result = Math.max(result, countIntersections(y, ys));
			result = Math.max(result, countIntersections(y + 1, ys));
		}

		return result;
	}

	private static int countIntersections(int y, int[] ys) {
		int result = 0;
		for (int i = 0; i < ys.length; i++) {
			if(ys[i] == y) {
				result++;
				continue;
			}

			if(i > 0) {
				if (ys[i] > y && y > ys[i-1]) {
					result++;
				} else if (ys[i-1] > y && y > ys[i]) {
					result++;
				}
			}
		}

		return result;
	}
}
