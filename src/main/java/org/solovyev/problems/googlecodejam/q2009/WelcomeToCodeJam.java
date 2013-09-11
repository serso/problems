package org.solovyev.problems.googlecodejam.q2009;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static org.solovyev.common.Charsets.UTF_8;

/**
 * User: serso
 * Date: 9/1/13
 * Time: 6:53 PM
 */
public class WelcomeToCodeJam {

	private static final String pattern = "welcome to code jam";

	private static final String lineSeparator = System.getProperty("line.separator");

	static String solve(@Nonnull InputStream is) throws IOException {
		final StringBuilder result = new StringBuilder();

		final BufferedReader sr = new BufferedReader(new InputStreamReader(is, UTF_8));

		final String firstLine = sr.readLine();
		final int testCasesCount = Integer.valueOf(firstLine);
		int testCaseCounter = 0;

		String line = sr.readLine();
		while (line != null) {
			if (testCaseCounter < testCasesCount) {
				result.append("Case #");
				result.append(testCaseCounter + 1);
				result.append(": ");
				result.append(toString(solve(line)));
				result.append(lineSeparator);
				testCaseCounter++;
			} else {
				break;
			}

			line = sr.readLine();
		}

		return result.toString();
	}

	private static String toString(BigInteger integer) {
		String s = integer.toString();
		final int length = s.length();
		if(length > 4) {
			return s.substring(length - 4);
		} else {
			switch (length) {
				case 1:
					return "000" + s;
				case 2:
					return "00" + s;
				case 3:
					return "0" + s;
				default:
					throw new AssertionError();
			}
		}
	}

	static BigInteger solve(@Nonnull String line) {
		return solve(line, pattern);
	}

	static BigInteger solve(@Nonnull String text, @Nonnull String pattern) {
		final BigInteger[][] visited = new BigInteger[text.length()+1][pattern.length()+1];
		return solve(text, pattern, text.length() - 1, pattern.length() - 1, visited);
	}

	private static BigInteger solve(@Nonnull String text, @Nonnull String pattern, int textPosition, int patternPosition, BigInteger[][] processed) {
		if(patternPosition < 0) {
			return ONE;
		}

		if(textPosition < 0) {
			return ZERO;
		}

		if(processed[textPosition][patternPosition] != null) {
			return processed[textPosition][patternPosition];
		}

		BigInteger result = ZERO;

		final int oldTextPosition = textPosition;
		final char patternCharacter = pattern.charAt(patternPosition);
		for (; textPosition >= 0; textPosition--) {
			final char textChar = text.charAt(textPosition);
			if (textChar == patternCharacter) {
				result = result.add(solve(text, pattern, textPosition - 1, patternPosition - 1, processed));
			}

			if(textPosition < patternPosition) {
				break;
			}
		}

		processed[oldTextPosition][patternPosition] = result;

		return result;
	}
}
