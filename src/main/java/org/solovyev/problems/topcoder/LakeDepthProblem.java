package org.solovyev.problems.topcoder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * User: serso
 * Date: 8/21/13
 * Time: 12:25 AM
 */
// http://community.topcoder.com/stat?c=problem_statement&pm=3101&rd=5886
public class LakeDepthProblem {

	public static final int VISITING = -1;
	public static final int NOT_VISITED = -2;

	public static int depth(String[] plot) {
		int depth = 0;

		final Matrix heights = new Matrix(plot);
		final int[][] depths = calculateDepths(heights);
		print(depths, "Depths");

		for (int i = 0; i < heights.height; i++) {
			for (int j = 0; j < heights.width; j++) {
				depth = max(depth, depths[i][j]);
			}
		}

		return depth;
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

	private static int[][] calculateDepths(@Nonnull Matrix heights) {
		final int[][] depths = initWith(heights.height, heights.width, NOT_VISITED);
		final int[][] maxHeights = initWith(heights.height, heights.width, -1);

		for (int i = 0; i < heights.height; i++) {
			for (int j = 0; j < heights.width; j++) {
				maxHeights[i][j] = calculateHeight(heights, depths, maxHeights, i, j);
			}
		}

		return depths;
	}

	private static int calculateHeight(@Nonnull Matrix heights, int[][] depths, int[][] maxHeights, int i, int j) {
		if(maxHeights[i][j] >= 0) {
			return maxHeights[i][j];
		}

		if(depths[i][j] == VISITING) {
			return Integer.MAX_VALUE;
		}

		depths[i][j] = VISITING;

		if (i == 0 || j == 0 || i == heights.height - 1 || j == heights.width - 1) {
			depths[i][j] = 0;
			return heights.get(i, j);
		}

		final int leftHeight = calculateHeight(heights, depths, maxHeights, i, j - 1);
		final int upHeight = calculateHeight(heights, depths, maxHeights, i - 1, j);
		final int rightHeight = calculateHeight(heights, depths, maxHeights, i, j + 1);
		final int bottomHeight = calculateHeight(heights, depths, maxHeights, i + 1, j);

		final int height = min(min(leftHeight, rightHeight), min(bottomHeight, upHeight));

		depths[i][j] = max(0, height - heights.get(i, j));

		return max(height, heights.get(i, j));
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

	private static final class Matrix {

		@Nonnull
		private final String[] rows;

		private final int height;
		private final int width;

		private Matrix(@Nonnull String[] rows) {
			this.rows = rows;
			this.height = rows.length;
			this.width = rows[0].length();
		}

		private int get(int i, int j) {
			return rows[i].charAt(j) - 32;
		}
	}
}
