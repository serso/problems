package org.solovyev.problems.googlecodejam.q2009;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static org.solovyev.common.Charsets.UTF_8;

/**
 * User: serso
 * Date: 9/1/13
 * Time: 12:52 PM
 */
public class AlienLanguage {

	static String solve(@Nonnull InputStream is) throws IOException {
		final StringBuilder result = new StringBuilder();

		final BufferedReader sr = new BufferedReader(new InputStreamReader(is, UTF_8));

		final String firstLine = sr.readLine();
		final String[] arguments = firstLine.split(" ");
		final int length = Integer.valueOf(arguments[0]);

		final int dictionarySize = Integer.valueOf(arguments[1]);
		int dictionaryCounter = 0;
		final int testCasesCount = Integer.valueOf(arguments[2]);
		int testCaseCounter = 0;

		final Set<String> dictionary = new HashSet<String>();
		String line = sr.readLine();
		while (line != null) {
			if (dictionaryCounter < dictionarySize) {
				dictionary.add(line);
				dictionaryCounter++;
			} else if(testCaseCounter < testCasesCount) {
				result.append(solve(dictionary, line, length, testCaseCounter));
				result.append("\n");
				testCaseCounter++;
			} else {
				break;
			}

			line = sr.readLine();
		}

		return result.toString();
	}

	private static String solve(@Nonnull Set<String> dictionary, @Nonnull String pattern, int length, int testCase) {
		int counter = 0;
		for (CharSequence word : getWords(pattern)) {
			if (dictionary.contains(word.toString())) {
				counter++;
			}
		}
		return "Case #" + (testCase + 1) + ": " + counter;
	}

	@Nonnull
	static List<? extends CharSequence> getWords(@Nonnull String pattern) {
		List<StringBuilder> words = new ArrayList<StringBuilder>();
		words.add(new StringBuilder());

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
						final List<StringBuilder> newWords = new ArrayList<StringBuilder>();
						for (; i < pattern.length(); i++) {
							c = pattern.charAt(i);
							switch (c) {
								case ')':
									inBrackets = false;
									break;
								default:
									for (StringBuilder word : words) {
										newWords.add(new StringBuilder(word).append(c));
									}
							}
							if (!inBrackets) {
								break;
							}
						}
						words = newWords;
					} else {
						for (StringBuilder word : words) {
							word.append(c);
						}
					}
			}
		}

		return words;
	}
}
