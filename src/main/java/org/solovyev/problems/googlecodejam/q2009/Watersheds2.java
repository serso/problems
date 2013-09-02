package org.solovyev.problems.googlecodejam.q2009;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.solovyev.common.Charsets.UTF_8;

/**
 * User: serso
 * Date: 9/1/13
 * Time: 2:24 PM
 */

// http://code.google.com/codejam/contest/dashboard?c=90101#s=a&a=1
public class Watersheds2 {

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
		Point[][] basins = new Point[rows][cols];

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				findSink(map, basins, rows, cols, Point.newPoint(row, col));
			}
		}

		return toString(rows, cols, basins);
	}

	private static String toString(int rows, int cols, Point[][] basins) {
		char offset = 0;
		final Map<Point, Character> namesBySink = new HashMap<Point, Character>();

		final StringBuilder result = new StringBuilder();
		for (int row = 0; row < rows; row++) {
			if (row > 0) {
				result.append("\n");
			}
			for (int col = 0; col < cols; col++) {
				if (col > 0) {
					result.append(" ");
				}
				Character name = namesBySink.get(basins[row][col]);
				if(name == null) {
					name = (char)('a' + offset);
					namesBySink.put(basins[row][col], name);
					offset++;
				}
				result.append(name);
			}
		}
		return result.toString();
	}

	@Nonnull
	private static List<Point> getNeighbours(@Nonnull Point position, int rows, int cols) {
		final List<Point> result = new ArrayList<Point>(4);

		if (position.row > 0) {
			result.add(Point.newPoint(position.row - 1, position.col));
		}

		if (position.col > 0) {
			result.add(Point.newPoint(position.row, position.col - 1));
		}

		if (position.col < cols - 1) {
			result.add(Point.newPoint(position.row, position.col + 1));
		}

		if (position.row < rows - 1) {
			result.add(Point.newPoint(position.row + 1, position.col));
		}

		return result;
	}

	@Nonnull
	private static Point findSink(int[][] map, Point[][] basins, int rows, int cols, Point position) {
		if (position.getValue(basins) != null) {
			return position.getValue(basins);
		}

		Point nextPosition = position;
		for (Point neighbour : getNeighbours(position, rows, cols)) {
			if(neighbour.getValue(map) < nextPosition.getValue(map)) {
				nextPosition = neighbour;
			}
		}

		if (nextPosition != position) {
			final Point sink = findSink(map, basins, rows, cols, nextPosition);
			position.setValue(basins, sink);
			return sink;
		} else {
			position.setValue(basins, position);
			return position;
		}
	}

	private static final class Point {
		private final int row;
		private final int col;

		private Point(int row, int col) {
			this.row = row;
			this.col = col;
		}

		private static Point newPoint(int row, int col) {
			return new Point(row, col);
		}

		private <T> T getValue(T[][] map) {
			return map[row][col];
		}

		private <T> void setValue(T[][] map, T value) {
			map[row][col] = value;
		}

		private int getValue(int[][] map) {
			return map[row][col];
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Point point = (Point) o;

			if (col != point.col) return false;
			if (row != point.row) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = row;
			result = 31 * result + col;
			return result;
		}
	}
}
