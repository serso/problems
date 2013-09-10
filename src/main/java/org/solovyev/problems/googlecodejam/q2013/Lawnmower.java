package org.solovyev.problems.googlecodejam.q2013;

import org.solovyev.problems.googlecodejam.GoogleCodeJamProblem;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;

// http://code.google.com/codejam/contest/2270488/dashboard#s=p1

public class Lawnmower extends GoogleCodeJamProblem {
	@Nonnull
	@Override
	protected CharSequence solve(@Nonnull String line, @Nonnull BufferedReader br) throws IOException {
		final String[] dimensions = line.split(" ");

		final int rows = Integer.valueOf(dimensions[0]);
		final int cols = Integer.valueOf(dimensions[1]);
		final int[][] input = new int[rows][cols];
		final int[][] lawn = new int[rows][cols];

		for(int i = 0; i < rows; i++) {
			line = br.readLine();
			final String[] row = line.split(" ");
			for (int j = 0; j < row.length; j++) {
				input[i][j] = Integer.valueOf(row[j]);
				lawn[i][j] = 100;
			}
		}

		return solve(input, lawn);
	}

	@Nonnull
	CharSequence solve(int[][] input, int[][] lawn) {
		final int rows = input.length;
		final int cols = input[0].length;

		for (int i = 0; i < rows; i++) {
			final int[] row = lawn[i];
			cutRowUpTo(row, findMax(input[i]));
			for (int j = 0; j < cols; j++) {
				if (input[i][j] != lawn[i][j]) {
					cutColumnUpTo(lawn, j, input[i][j]);
				}
			}
		}


		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if(input[i][j] != lawn[i][j]) {
					return "NO";
				}
			}
		}
		return "YES";
	}

	private void cutRowUpTo(int[] row, int upTo) {
		for (int i = 0; i < row.length; i++) {
			if (upTo < row[i]) {
				row[i] = upTo;
			}
		}
	}

	private void cutColumnUpTo(int[][] lawn, int column, int upTo) {
		for (int i = 0; i < lawn.length; i++) {
			if (upTo < lawn[i][column]) {
				lawn[i][column] = upTo;
			}
		}
	}


	private int findMax(int[] row) {
		int max = 0;
		for (int item : row) {
			max = Math.max(max, item);
		}
		return max;
	}
}
