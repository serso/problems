package org.solovyev.problems.googlecodejam;

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
public class Watersheds {

	private static char offset = 0;

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
			}
			row++;
		}

		return solve(map, rows, cols);
	}

	@Nonnull
	static String solve(int[][] map, int rows, int cols) {
		offset = 0;

		char[][] names = new char[rows][cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				boolean alreadyVisited = visit(map, names, rows, cols, row, col);
				if (!alreadyVisited) {
					nextName();
				}
			}
		}

		final StringBuilder result = new StringBuilder();
		for (int row = 0; row < rows; row++) {
			if(row > 0){
				result.append("\n");
			}
			for (int col = 0; col < cols; col++) {
				if(col > 0) {
					result.append(" ");
				}
				result.append(names[row][col]);
			}
		}
		return result.toString();
	}

	private static void nextName() {
		offset++;
	}

	private static boolean visit(int[][] map, char[][] names, int rows, int cols, int row, int col) {
		if (names[row][col] != 0) {
			return true;
		}

		final int height = map[row][col];
		names[row][col] = getName();

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
			alreadyVisited = visit(map, names, rows, cols, row - 1, col);
			if (alreadyVisited) {
				names[row][col] = names[row - 1][col];
				return true;
			}
		}

		if (alreadyVisited && west != Integer.MAX_VALUE && west < height && west <= north && west <= south && west <= east) {
			alreadyVisited = visit(map, names, rows, cols, row, col - 1);
			if (alreadyVisited) {
				names[row][col] = names[row][col - 1];
				return true;
			}
		}

		if (alreadyVisited && east != Integer.MAX_VALUE && east < height && east <= north && east <= south && east <= west) {
			alreadyVisited = visit(map, names, rows, cols, row, col + 1);
			if (alreadyVisited) {
				names[row][col] = names[row][col + 1];
				return true;
			}
		}

		if (alreadyVisited && south != Integer.MAX_VALUE && south < height && south <= north && south <= west && south <= east) {
			alreadyVisited = visit(map, names, rows, cols, row + 1, col);
			if (alreadyVisited) {
				names[row][col] = names[row + 1][col];
				return true;
			}
		}

		/*if(row > 0) {
			visit(map, names, rows, cols, row - 1, col);
		}

		if(col > 0) {
			visit(map, names, rows, cols, row, col - 1);
		}

		if(row < rows - 1) {
			visit(map, names, rows, cols, row + 1, col);
		}

		if(col < cols - 1) {
			visit(map, names, rows, cols, row, col + 1);
		}*/
		return false;
	}

	private static char getName() {
		return (char) ('a' + offset);
	}
}
