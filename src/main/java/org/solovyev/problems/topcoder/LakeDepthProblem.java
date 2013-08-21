package org.solovyev.problems.topcoder;

import javax.annotation.Nullable;

import static java.lang.Character.isDigit;

/**
 * User: serso
 * Date: 8/21/13
 * Time: 12:25 AM
 */
// http://community.topcoder.com/stat?c=problem_statement&pm=3101&rd=5886
public class LakeDepthProblem {

	public static int depth(String[] plot) {
		int depth = 0;

		int[][] heights = calculateHeights(plot);

		int[][] maxHeights = calculateMaxHeights(heights);
		int[][] minHeights = calculateMinHeights(heights);
		print(heights, "Input");
		print(maxHeights, "Max heights");
		print(minHeights, "Min heights");

		for (int i = 0; i < heights.length; i++) {
			for (int j = 0; j < heights[i].length; j++) {
				depth = Math.max(depth, maxHeights[i][j] - minHeights[i][j]);
			}
		}

		return depth;
	}

	private static int[][] calculateMinHeights(int[][] heights) {
		final int[][] minHeights = initWith(heights.length, heights[0].length, Integer.MAX_VALUE);

		for (int i = 0; i < heights.length; i++) {
			for (int j = 0; j < heights[i].length; j++) {
				calculateMinHeight(heights, minHeights, i, j);
			}
		}

		return minHeights;
	}

	private static int[][] initWith(int height, int width, int value) {
		int[][] m = new int[height][width];
		if (value != 0) {
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m[i].length; j++) {
					m[i][j] = value;
				}
			}
		}
		return m;
	}

	private static int[][] calculateMaxHeights(int[][] heights) {
		final int[][] maxHeights = initWith(heights.length, heights[0].length, -1);

		for (int i = 0; i < heights.length; i++) {
			for (int j = 0; j < heights[i].length; j++) {
				calculateMaxHeight(heights, maxHeights, i, j);
			}
		}

		return maxHeights;
	}

	private static int calculateMinHeight(int[][] heights, int[][] minHeights, int i, int j) {
		if (minHeights[i][j] != Integer.MAX_VALUE) {
			return minHeights[i][j];
		}

		minHeights[i][j] = heights[i][j];
		if (j == 0 || i == 0 || j == heights[0].length - 1 || i == heights.length - 1) {
			return minHeights[i][j];
		}

		// left
		int left = 0;
		if (j > 0) {
			left = calculateMinHeight(heights, minHeights, i, j - 1);
		}

		// up
		int up = 0;
		if (i > 0) {
			up = calculateMinHeight(heights, minHeights, i - 1, j);
		}

		// right
		int right = 0;
/*		if (j < heights[0].length - 1) {
			right = calculateMinHeight(heights, minHeights, i, j + 1);
		}*/

		// down
		int down = 0;
/*		if (i < heights.length - 1) {
			down = calculateMinHeight(heights, minHeights, i + 1, j);
		}*/

		minHeights[i][j] = Math.min(minHeights[i][j], Math.max(Math.max(left, right), Math.max(up, down)));

		return minHeights[i][j];
	}

	private static int calculateMaxHeight(int[][] heights, int[][] maxHeights, int i, int j) {
		if (maxHeights[i][j] >= 0) {
			return maxHeights[i][j];
		}

		maxHeights[i][j] = heights[i][j];
		if (j == 0 || i == 0 || j == heights[0].length - 1 || i == heights.length - 1) {
			return maxHeights[i][j];
		}

		// left
		int left = Integer.MAX_VALUE;
		if (j > 0) {
			left = calculateMaxHeight(heights, maxHeights, i, j - 1);
		}

		// up
		int up = Integer.MAX_VALUE;
		if (i > 0) {
			up = calculateMaxHeight(heights, maxHeights, i - 1, j);
		}

		// right
		int right = Integer.MAX_VALUE;
/*		if (j < heights[0].length - 1) {
			right = calculateMaxHeight(heights, maxHeights, i, j + 1);
		}*/

		// down
		int down = Integer.MAX_VALUE;
/*		if (i < heights.length - 1) {
			down = calculateMaxHeight(heights, maxHeights, i + 1, j);
		}*/

		maxHeights[i][j] = Math.max(maxHeights[i][j], Math.min(Math.min(left, right), Math.min(up, down)));

		return maxHeights[i][j];
	}

	private static int[][] calculateHeights(String[] plot) {
		final int[][] heights = new int[plot.length][plot[0].length()];
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[i].length(); j++) {
				final Character ch = plot[i].charAt(j);
				heights[i][j] = ch - 32;
			}
		}
		return heights;
	}

	private static void print(int[][] m, @Nullable String name) {
		if (name != null) {
			System.out.println(name);
		}

		for (int[] row : m) {
			for (int element : row) {
				System.out.print(element + "\t");
			}
			System.out.println();
		}
	}
}
