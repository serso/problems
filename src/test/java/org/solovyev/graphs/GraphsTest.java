package org.solovyev.graphs;

import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.solovyev.graphs.Graphs.*;

public class GraphsTest {

	private static final double EPS = 0.0000005;
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
			public boolean visit(@Nonnull Vertex<String> v) {
				assertEquals(getExpectedValue(), v.getValue());
				counter.incrementAndGet();
				return false;
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
			public boolean visit(@Nonnull Vertex<String> v) {
				assertEquals(getExpectedValue(), v.getValue());
				counter.incrementAndGet();
				return false;
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

		assertEquals(5, findShortestPath(g, s, x).getLength(), EPS);
		assertEquals(7, findShortestPath(g, s, y).getLength(), EPS);
		assertEquals(8, findShortestPath(g, s, u).getLength(), EPS);
		assertEquals(9, findShortestPath(g, s, v).getLength(), EPS);

		assertEquals(9, findShortestPath(g, x, s).getLength(), EPS);
		assertEquals(4, findShortestPath(g, x, v).getLength(), EPS);
		assertEquals(3, findShortestPath(g, x, u).getLength(), EPS);
		assertEquals(2, findShortestPath(g, x, y).getLength(), EPS);

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

	@Test
	public void testFindMaxFlow() throws Exception {
		Graph<Object> graph = Graph.newGraph();
		final Vertex<Object> a = graph.addVertex("A");
		final Vertex<Object> b = graph.addVertex("B");
		final Vertex<Object> c = graph.addVertex("C");
		final Vertex<Object> d = graph.addVertex("D");
		final Vertex<Object> e = graph.addVertex("E");
		final Vertex<Object> f = graph.addVertex("F");
		final Vertex<Object> g = graph.addVertex("G");

		a.addNeighbour(b, 3);
		a.addNeighbour(d, 3);

		b.addNeighbour(c, 4);

		c.addNeighbour(a, 3);
		c.addNeighbour(d, 1);
		c.addNeighbour(e, 2);

		d.addNeighbour(e, 2);
		d.addNeighbour(f, 6);

		e.addNeighbour(b, 1);
		e.addNeighbour(g, 1);

		f.addNeighbour(g, 9);

		assertEquals(5, Graphs.findMaxFlow(graph, a, g));

	}

	@Test
	public void testFindShortestPathAStar() throws Exception {
		final Graph<Point> graph = Graph.newGraph();
		final int size = 10;
		final Vertex<Point>[][] vertices = new Vertex[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				vertices[i][j] = graph.addVertex(Point.newPoint(i, j));
			}
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(i > 0) {
					vertices[i][j].addNeighbour(vertices[i-1][j]);
				}

				if(j > 0) {
					vertices[i][j].addNeighbour(vertices[i][j-1]);
				}

				if(i < size - 1) {
					vertices[i][j].addNeighbour(vertices[i+1][j]);
				}

				if(j < size - 1) {
					vertices[i][j].addNeighbour(vertices[i][j+1]);
				}
			}
		}

		Path<Point> path = Graphs.findShortestPathAStar(graph, vertices[0][0], vertices[size - 1][size - 1], new DistanceHeuristic<Point>() {
			@Override
			public int getDistance(@Nonnull Vertex<Point> from, @Nonnull Vertex<Point> to) {
				final Point toValue = to.getValue();
				final Point fromValue = from.getValue();
				return toValue.getDistance(fromValue);
			}
		});

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				boolean found = false;
				for (Vertex<Point> vertex : path.getVertices()) {
					if(vertex.getValue().x == i && vertex.getValue().y == j) {
						found = true;
						break;
					}
				}

				if(found) {
					System.out.print("x");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println(" ");
		}

	}

	private static final class Point {
		private final int x;
		private final int y;


		private Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		private static Point newPoint(int x, int y) {
			return new Point(x, y);
		}

		private int getDistance(@Nonnull Point that) {
			return (int) sqrt(pow(this.x - that.x, 2) + pow(this.y - that.y, 2));
		}
	}
}
