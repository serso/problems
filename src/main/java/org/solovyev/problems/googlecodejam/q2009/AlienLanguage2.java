package org.solovyev.problems.googlecodejam.q2009;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.solovyev.common.Charsets.UTF_8;

public class AlienLanguage2 {

	public static final int LATIN_ALPHABET_COUNT = 26;

	private static final String lineSeparator = System.getProperty("line.separator");

	static String solve(@Nonnull InputStream is) throws IOException {
		final StringBuilder result = new StringBuilder();

		final BufferedReader sr = new BufferedReader(new InputStreamReader(is, UTF_8));

		final String firstLine = sr.readLine();
		final String[] arguments = firstLine.split(" ");
		final int wordLength = Integer.valueOf(arguments[0]);

		final int dictionarySize = Integer.valueOf(arguments[1]);
		int dictionaryCounter = 0;
		final int testCasesCount = Integer.valueOf(arguments[2]);
		int testCaseCounter = 0;

		final char[][] dictionary = new char[dictionarySize][wordLength];
		String line = sr.readLine();
		while (line != null) {
			if (dictionaryCounter < dictionarySize) {
				addWord(dictionary, dictionaryCounter, line);
				dictionaryCounter++;
			} else if(testCaseCounter < testCasesCount) {
				result.append(solve(dictionary, line, wordLength, testCaseCounter));
				result.append(lineSeparator);
				testCaseCounter++;
			} else {
				break;
			}

			line = sr.readLine();
		}

		return result.toString();
	}

	private static void addWord(char[][] dictionary, int dictionaryCounter, String word) {
		for (int i = 0; i < word.length(); i++) {
			dictionary[dictionaryCounter][i] = word.charAt(i);
		}
	}

	private static String solve(@Nonnull char[][] dictionary, @Nonnull String patternWord, int length, int testCase) {
		boolean[][] pattern = getPattern(patternWord, length);

		int counter = 0;

		for (char[] word : dictionary) {
			boolean contains = true;

			for (int i = 0; i < word.length; i++) {
				final char letter = word[i];
				if (!pattern[i][getCharPosition(letter)]) {
					contains = false;
					break;
				}
			}

			if(contains) {
				counter++;
			}
		}
		return "Case #" + (testCase + 1) + ": " + counter;
	}

	@Nonnull
	static boolean[][] getPattern(@Nonnull String pattern, int length) {
		boolean[][] result = new boolean[length][LATIN_ALPHABET_COUNT];
		int j = 0;

		boolean inBrackets = false;
		for (int i = 0; i < pattern.length(); i++) {
			char c = pattern.charAt(i);
			switch (c) {
				case '(':
					inBrackets = true;
					break;
				case ')':
					inBrackets = false;
					break;
				default:
					if (inBrackets) {
						for (; i < pattern.length(); i++) {
							c = pattern.charAt(i);
							switch (c) {
								case ')':
									inBrackets = false;
									break;
								default:
									result[j][getCharPosition(c)] = true;
							}
							if (!inBrackets) {
								break;
							}
						}
						j++;
					} else {
						result[j][getCharPosition(c)] = true;
						j++;
					}
			}
		}

		return result;
	}

	private static int getCharPosition(char c) {
		return c - 'a';
	}
}
