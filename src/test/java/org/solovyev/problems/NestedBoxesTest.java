package org.solovyev.problems;

import org.junit.Assert;
import org.junit.Test;

import static org.solovyev.problems.NestedBoxes.Box.newBox;
import static org.solovyev.problems.NestedBoxes.getLongestNestedSequence;

/**
 * User: serso
 * Date: 8/25/13
 * Time: 3:38 PM
 */
public class NestedBoxesTest {

	@Test
	public void testSolve() throws Exception {
		Assert.assertEquals("[[6, 4, 4], [5, 3, 3], [4, 2, 2], [2, 1, 1]]", getLongestNestedSequence(newBox(6, 4, 4), newBox(5, 3, 3), newBox(4, 2, 2), newBox(2, 1, 1)).toString());
		Assert.assertEquals("[[6, 4, 4], [5, 3, 3], [4, 2, 2], [2, 1, 1]]", getLongestNestedSequence(newBox(6, 4, 4), newBox(5, 3, 3), newBox(5, 4, 3), newBox(4, 2, 2), newBox(2, 1, 1)).toString());
		Assert.assertEquals("[[6, 5, 4], [5, 4, 3], [4, 2, 2], [2, 1, 1]]", getLongestNestedSequence(newBox(6, 4, 5), newBox(6, 5, 4), newBox(5, 3, 3), newBox(5, 4, 3), newBox(4, 2, 2), newBox(2, 1, 1)).toString());
		Assert.assertEquals("[[7, 6, 5], [6, 5, 4], [5, 4, 3], [4, 2, 2], [2, 1, 1]]", getLongestNestedSequence(newBox(6, 4, 5), newBox(7, 6, 5), newBox(6, 5, 4), newBox(5, 3, 3), newBox(5, 4, 3), newBox(4, 2, 2), newBox(2, 1, 1)).toString());

	}
}
