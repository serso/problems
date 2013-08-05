package org.solovyev.problems;

public class AliveProbabilityProblem {

	public static double solve(int x, int y, int n, int steps) {
		return aliveProbability(x, y, n, steps);
	}

	private static double aliveProbability(int x, int y, int n, int steps) {
		if (steps == 0) {
			return 1d;
		}

		double probability = 0;
		if (x > 0) {
			probability += 0.25d * aliveProbability(x - 1, y, n, steps - 1);
		}
		if (y > 0) {
			probability += 0.25d * aliveProbability(x, y - 1, n, steps - 1);
		}
		if (x < n - 1) {
			probability += 0.25d * aliveProbability(x + 1, y, n, steps - 1);
		}
		if (y < n - 1) {
			probability += 0.25d * aliveProbability(x, y + 1, n, steps - 1);
		}
		return probability;
	}
}
