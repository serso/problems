package org.solovyev.problems;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.solovyev.problems.IntegerKnapsackProblem.Item;
import static org.solovyev.problems.IntegerKnapsackProblem.Item.newItem;
import static org.solovyev.problems.IntegerKnapsackProblem.Items;
import static org.solovyev.problems.IntegerKnapsackProblem.solve;

/**
 * User: serso
 * Date: 8/12/13
 * Time: 11:31 PM
 */
public class IntegerKnapsackProblemTest {

	@Test
	public void testSolve() throws Exception {
		Items actual = solve(10, Arrays.asList(newItem(1, 1)));
		assertEquals(10, actual.getValue());

		actual = solve(10, Arrays.asList(newItem(1, 1), newItem(2, 1), newItem(2, 2), newItem(4, 5)));
		assertEquals(12, actual.getValue());

		final List<Item> items = actual.getItems();
		assertTrue(items.contains(newItem(4, 5)));
	}
}
