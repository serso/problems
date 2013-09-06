package org.solovyev.graphs;

import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.solovyev.graphs.Graphs.breadthFirstSearch;
import static org.solovyev.graphs.Graphs.depthFirstSearch;

public class GraphsTest {

	@Nonnull
	private Graph<String> graph;

	@Nonnull
	private Vertex<String> rootVertex;

	@Before
	public void setUp() throws Exception {
		createTestGraph();
	}

	@Test
	public void testDepthFirstSearch() throws Exception {
		final AtomicInteger counter = new AtomicInteger();
		depthFirstSearch(graph, rootVertex, new Visitor<String>() {
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
						return "021";
					case 4:
						return "022";
					case 5:
						return "023";
					case 6:
						return "03";
					case 7:
						return "031";
					case 8:
						return "03121";
					case 9:
						return "032";
					case 10:
						return "033";
				}

				throw new AssertionError();
			}
		});
	}

	private Graph<String> createTestGraph() {
		graph = Graph.newGraph();
		rootVertex = graph.addVertex("0");
		final Vertex<String> v0 = rootVertex;
		final Vertex<String> v01 = graph.addVertex("01");
		final Vertex<String> v02 = graph.addVertex("02");
		final Vertex<String> v03 = graph.addVertex("03");

		final Vertex<String> v021 = graph.addVertex("021");
		final Vertex<String> v022 = graph.addVertex("022");
		final Vertex<String> v023 = graph.addVertex("023");
		final Vertex<String> v031 = graph.addVertex("031");
		final Vertex<String> v032 = graph.addVertex("032");
		final Vertex<String> v033 = graph.addVertex("033");
		final Vertex<String> v03121 = graph.addVertex("03121");
		v0.addNeighbour(v01);
		v0.addNeighbour(v02);
		v0.addNeighbour(v03);

		v02.addNeighbour(v021);
		v02.addNeighbour(v022);
		v02.addNeighbour(v023);

		v03.addNeighbour(v031);
		v03.addNeighbour(v032);
		v03.addNeighbour(v033);
		v031.addNeighbour(v03121);
		return graph;
	}

	@Test
	public void testBreadthFirstSearch() throws Exception {
		final AtomicInteger counter = new AtomicInteger();
		breadthFirstSearch(graph, rootVertex, new Visitor<String>() {
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
					case 10:
						return "03121";
				}

				throw new AssertionError();
			}
		});
	}
}
