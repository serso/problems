package org.solovyev.graphs;

import javax.annotation.Nonnull;

import java.util.concurrent.atomic.AtomicInteger;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.solovyev.graphs.Graphs.breadthFirstSearch;
import static org.solovyev.graphs.Graphs.depthFirstSearch;

public class GraphsTest {

	@Test
	public void testDepthFirstSearch() throws Exception {
		final Graph<String> g = createTestGraph();

		final AtomicInteger counter = new AtomicInteger();
		depthFirstSearch(g, new Visitor<String>() {
			@Override
			public void visit(@Nonnull Vertex<String> v) {
				assertEquals(getExpectedValue(), v.getValue());
				counter.incrementAndGet();
			}

			private String getExpectedValue() {
				switch (counter.get()) {
					case 0:
						return "0";
					case 1:
						return "01";
					case 2:
						return "02";
					case 4:
						return "021";
					case 5:
						return "022";
					case 6:
						return "023";
					case 3:
						return "03";
					case 7:
						return "031";
					case 8:
						return "032";
					case 9:
						return "033";
				}

				throw new AssertionError();
			}
		});
	}

	private Graph<String> createTestGraph() {
		Graph<String> g = Graph.newGraph();
		final Vertex<String> v0 = g.addVertex("0");
		final Vertex<String> v01 = g.addVertex("01");
		final Vertex<String> v02 = g.addVertex("02");
		final Vertex<String> v03 = g.addVertex("03");

		final Vertex<String> v021 = g.addVertex("021");
		final Vertex<String> v022 = g.addVertex("022");
		final Vertex<String> v023 = g.addVertex("023");
		final Vertex<String> v031 = g.addVertex("031");
		final Vertex<String> v032 = g.addVertex("032");
		final Vertex<String> v033 = g.addVertex("033");
		v0.addNeighbour(v01);
		v0.addNeighbour(v02);
		v0.addNeighbour(v03);

		v02.addNeighbour(v021);
		v02.addNeighbour(v022);
		v02.addNeighbour(v023);

		v03.addNeighbour(v031);
		v03.addNeighbour(v032);
		v03.addNeighbour(v033);
		return g;
	}

	@Test
	public void testBreadthFirstSearch() throws Exception {
		final Graph<String> g = createTestGraph();

		final AtomicInteger counter = new AtomicInteger();
		breadthFirstSearch(g, new Visitor<String>() {
			@Override
			public void visit(@Nonnull Vertex<String> v) {
				assertEquals(getExpectedValue(), v.getValue());
				counter.incrementAndGet();
			}

			private String getExpectedValue() {
				switch (counter.get()) {
					case 0:
						return "0";
					case 1:
						return "01";
					case 2:
						return "02";
					case 3:
						return "03";
					case 4:
						return "021";
					case 5:
						return "022";
					case 6:
						return "023";
					case 7:
						return "031";
					case 8:
						return "032";
					case 9:
						return "033";
				}

				throw new AssertionError();
			}
		});
	}
}
