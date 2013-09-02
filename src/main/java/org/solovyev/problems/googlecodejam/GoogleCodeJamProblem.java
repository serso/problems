package org.solovyev.problems.googlecodejam;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.solovyev.common.Charsets.UTF_8;

/**
 * User: serso
 * Date: 9/2/13
 * Time: 10:31 PM
 */
public abstract class GoogleCodeJamProblem {

	@Nonnull
	public final String solve(@Nonnull InputStream is) throws IOException {
		final StringBuilder result = new StringBuilder();

		final BufferedReader sr = new BufferedReader(new InputStreamReader(is, UTF_8));

		final String firstLine = sr.readLine();
		final String[] arguments = firstLine.split(" ");

		final int testCasesCount = Integer.valueOf(arguments[0]);
		int testCaseCounter = 0;

		String line = sr.readLine();
		while (line != null) {
			if(testCaseCounter < testCasesCount) {
				result.append("Case #");
				result.append(testCaseCounter + 1);
				result.append(": ");
				result.append(solve(line, sr));
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
	protected abstract CharSequence solve(@Nonnull String line, @Nonnull BufferedReader sr) throws IOException;
}
