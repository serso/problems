package org.solovyev.problems.googlecodejam;

import javax.annotation.Nonnull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.solovyev.common.text.Strings;

public class GoogleCodeJamTestCase {

	public static void assertEquals(@Nonnull GoogleCodeJamProblem problem, @Nonnull String expectedFileName, @Nonnull String inputFileName) throws IOException {
		final InputStream expectedStream = problem.getClass().getResourceAsStream(expectedFileName);
		final InputStream inputStream = problem.getClass().getResourceAsStream(inputFileName);
		Assert.assertEquals(Strings.convertStream(expectedStream), problem.solve(inputStream));
	}

	public static void printResultToOutput(@Nonnull GoogleCodeJamProblem problem, @Nonnull String inputFileName) throws IOException {
		System.out.println(problem.solve(problem.getClass().getResourceAsStream(inputFileName)));
	}

	public static void printResultToFile(@Nonnull GoogleCodeJamProblem problem, @Nonnull String outputFileName, @Nonnull String inputFileName) throws IOException {
		final File out = new File(outputFileName);
		if(out.exists()) {
			if(!out.delete()) {
				throw new AssertionError("Unable to delete file!");
			}
		}

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(out.getName());
			fileWriter.append(problem.solve(problem.getClass().getResourceAsStream(inputFileName)));
		} finally {
			if (fileWriter != null) {
				fileWriter.close();
			}
		}
	}
}
