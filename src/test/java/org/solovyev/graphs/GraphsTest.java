package org.solovyev.graphs;

import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.solovyev.graphs.Graphs.*;

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
		depthFirstSearch(rootVertex, new Visitor<String>() {
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
		breadthFirstSearch(rootVertex, new Visitor<String>() {
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

	@Test
	public void testFindShortestPath() throws Exception {
		final Graph<String> g = Graph.newGraph();
		final Vertex<String> s = g.addVertex("s");
		final Vertex<String> x = g.addVertex("x");
		final Vertex<String> y = g.addVertex("y");
		final Vertex<String> u = g.addVertex("u");
		final Vertex<String> v = g.addVertex("v");
		s.addNeighbour(x, 5);
		s.addNeighbour(u, 10);
		x.addNeighbour(u, 3);
		x.addNeighbour(v, 9);
		x.addNeighbour(y, 2);
		y.addNeighbour(v, 6);
		y.addNeighbour(s, 7);
		v.addNeighbour(y, 4);
		u.addNeighbour(v, 1);
		u.addNeighbour(x, 2);

		assertEquals(5, findShortestPath(g, s, x).getLength());
		assertEquals(7, findShortestPath(g, s, y).getLength());
		assertEquals(8, findShortestPath(g, s, u).getLength());
		assertEquals(9, findShortestPath(g, s, v).getLength());

		assertEquals(9, findShortestPath(g, x, s).getLength());
		assertEquals(4, findShortestPath(g, x, v).getLength());
		assertEquals(3, findShortestPath(g, x, u).getLength());
		assertEquals(2, findShortestPath(g, x, y).getLength());

	}

	@Test
	public void testFindShortestPaths() throws Exception {
		final int i = Graphs.MAX_WEIGHT;
		int[][] g = {
				{0,	3,	8,	i,	-4},
				{i,	0,	i,	1,	7},
				{i,	4,	0,	i,	i},
				{2,	i,	-5,	0,	i},
				{i,	i,	i,	6,	0}};

		final int[][] paths = findShortestPaths(g);
		assertArrayEquals(new int[]{0,	1,	-3,	2,	-4}, paths[0]);
		assertArrayEquals(new int[]{3,	0,	-4,	1,	-1}, paths[1]);
		assertArrayEquals(new int[]{7,	4,	0,	5,	3}, paths[2]);
		assertArrayEquals(new int[]{2,	-1,	-5,	0,	-2}, paths[3]);
		assertArrayEquals(new int[]{8,	5,	1,	6,	0}, paths[4]);
	}
}
