package org.solovyev.problems.googlecodejam.q2009;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.solovyev.common.Charsets.UTF_8;

/**
 * User: serso
 * Date: 9/1/13
 * Time: 2:24 PM
 */

// http://code.google.com/codejam/contest/dashboard?c=90101#s=a&a=1
public class Watersheds {

	static String solve(@Nonnull InputStream is) throws IOException {
		final StringBuilder result = new StringBuilder();

		final BufferedReader sr = new BufferedReader(new InputStreamReader(is, UTF_8));

		final String firstLine = sr.readLine();
		final int testCasesCount = Integer.valueOf(firstLine);
		int testCaseCounter = 0;

		String line = sr.readLine();
		while (line != null) {
			if (testCaseCounter < testCasesCount) {
				final String[] arguments = line.split(" ");
				final Integer rows = Integer.valueOf(arguments[0]);
				final Integer cols = Integer.valueOf(arguments[1]);
				result.append("Case #");
				result.append(testCaseCounter + 1);
				result.append(":\n");
				result.append(solve(sr, rows, cols));
				result.append("\n");
				testCaseCounter++;
			} else {
				break;
			}

			line = sr.readLine();
		}

		return result.toString();
	}

	@Nonnull
	private static String solve(BufferedReader sr, int rows, int cols) throws IOException {
		final int[][] map = new int[rows][cols];
		int row = 0;
		while (row < rows) {
			final String line = sr.readLine();
			int col = 0;
			for (String altitude : line.split(" ")) {
				map[row][col] = Integer.valueOf(altitude);
				col++;
			}
			row++;
		}

		return solve(map, rows, cols);
	}

	@Nonnull
	static String solve(int[][] map, int rows, int cols) {
		char[][] basins = new char[rows][cols];

		char name = 'a';

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				boolean alreadyVisited = visit(map, basins, rows, cols, row, col, name);
				if (!alreadyVisited) {
					name++;
				}
			}
		}

		return toString(rows, cols, basins);
	}

	private static String toString(int rows, int cols, char[][] basins) {
		final StringBuilder result = new StringBuilder();
		for (int row = 0; row < rows; row++) {
			if(row > 0){
				result.append("\n");
			}
			for (int col = 0; col < cols; col++) {
				if(col > 0) {
					result.append(" ");
				}
				result.append(basins[row][col]);
			}
		}
		return result.toString();
	}

	private static boolean visit(int[][] map, char[][] basins, int rows, int cols, int row, int col, char name) {
		if (basins[row][col] != 0) {
			return true;
		}

		final int height = map[row][col];
		basins[row][col] = name;

		int north = Integer.MAX_VALUE;
		if (row > 0) {
			north = map[row - 1][col];
		}

		int west = Integer.MAX_VALUE;
		if (col > 0) {
			west = map[row][col - 1];
		}

		int south = Integer.MAX_VALUE;
		if (row < rows - 1) {
			south = map[row + 1][col];
		}

		int east = Integer.MAX_VALUE;
		if (col < cols - 1) {
			east = map[row][col + 1];
		}

		boolean alreadyVisited = true;
		if (alreadyVisited && north != Integer.MAX_VALUE && north < height && north <= west && north <= south && north <= east) {
			alreadyVisited = visit(map, basins, rows, cols, row - 1, col, name);
			if (alreadyVisited) {
				basins[row][col] = basins[row - 1][col];
				return true;
			}
		}

		if (alreadyVisited && west != Integer.MAX_VALUE && west < height && west <= north && west <= south && west <= east) {
			alreadyVisited = visit(map, basins, rows, cols, row, col - 1, name);
			if (alreadyVisited) {
				basins[row][col] = basins[row][col - 1];
				return true;
			}
		}

		if (alreadyVisited && east != Integer.MAX_VALUE && east < height && east <= north && east <= south && east <= west) {
			alreadyVisited = visit(map, basins, rows, cols, row, col + 1, name);
			if (alreadyVisited) {
				basins[row][col] = basins[row][col + 1];
				return true;
			}
		}

		if (alreadyVisited && south != Integer.MAX_VALUE && south < height && south <= north && south <= west && south <= east) {
			alreadyVisited = visit(map, basins, rows, cols, row + 1, col, name);
			if (alreadyVisited) {
				basins[row][col] = basins[row + 1][col];
				return true;
			}
		}

		return false;
	}
}
