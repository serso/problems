package org.solovyev.problems.googlecodejam.q2013;

import org.solovyev.problems.googlecodejam.GoogleCodeJamProblem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * User: serso
 * Date: 9/2/13
 * Time: 10:30 PM
 */
public class TicTacToeTomek extends GoogleCodeJamProblem {

	public static final int SIZE = 4;

	@Nonnull
	@Override
	protected CharSequence solve(@Nonnull String line, @Nonnull BufferedReader br) throws IOException {
		char[][] board = new char[SIZE][SIZE];

		fillRow(board, 0, line);
		for(int row = 1; row < SIZE; row++) {
			line = br.readLine();
			fillRow(board, row, line);
		}

		br.readLine();

		return solve(board);
	}

	@Nonnull
	CharSequence solve(char[][] board) {
		String result = isWinner(board, 'X');
		if(result == null) {
			result = isWinner(board, 'O');
			if(result == null) {
				if(containsEmptyCells(board)) {
					result = "Game has not completed";
				} else {
					result = "Draw";
				}
			}
		}
		return result;
	}

	private boolean containsEmptyCells(char[][] board) {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(board[i][j] == '.') {
					return true;
				}
			}
		}
		return false;
	}

	@Nullable
	private String isWinner(char[][] board, char c) {
		boolean wind1 = true;
		boolean wind2 = true;
		for(int i = 0; i < SIZE; i++) {
			boolean winh = true;
			boolean winv = true;
			for(int j = 0; j < SIZE; j++) {
				if(board[i][j] != c && board[i][j] != 'T') {
					winv = false;
				}

				if(board[j][i] != c && board[j][i] != 'T') {
					winh = false;
				}
			}

			if(winh || winv) {
				return c + " won";
			}

			if(board[i][i] != c && board[i][i] != 'T') {
				wind1 = false;
			}

			if(board[i][SIZE - 1 - i]  != c && board[i][SIZE - 1 - i] != 'T') {
				wind2 = false;
			}
		}

		if(wind1 || wind2) {
			return c + " won";
		} else {
			return null;
		}
	}

	private void fillRow(char[][] board, int row, @Nonnull String s) {
		for (int i = 0; i < SIZE; i++) {
			board[row][i] = s.charAt(i);
		}
	}
}
