package org.solovyev.problems.topcoder;

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
		int[][] depths = new int[heights.length][heights.length];
		for (int i = 0; i < depths.length; i++) {
			for (int j = 0; j < depths[i].length; j++) {
				depths[i][j] = -1;
			}
		}

		for (int i = 0; i < heights.length; i++) {
			for (int j = 0; j < heights[i].length; j++) {
				depth = Math.max(depth, getDepth(heights, i, j, depths));
			}
		}

		return depth;
	}

	private static int getDepth(int[][] heights, int i, int j, int[][] depths) {
		if(depths[i][j] >= 0) {
			return depths[i][j];
		}

		final int height = heights[i][j];
		int depth = 0;

		// left
		if(i > 0) {
		}

		depths[i][j] = depth;
		return depth;
	}

	private static int[][] calculateHeights(String[] plot) {
		final int[][] heights = new int[plot.length][plot[0].length()];
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[i].length(); j++) {
				  heights[i][j] = plot[i].charAt(j);
			}
		}
		return heights;
	}
}
