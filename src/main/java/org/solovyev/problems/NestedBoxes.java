package org.solovyev.problems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * User: serso
 * Date: 8/25/13
 * Time: 3:15 PM
 */
public class NestedBoxes {

	@Nonnull
	public static List<Box> getLongestNestedSequence(@Nonnull Box... boxes) {
		return getLongestNestedSequence(Arrays.asList(boxes));
	}

	@Nonnull
	public static List<Box> getLongestNestedSequence(@Nonnull List<Box> boxes) {
		// sort dimensions of each box
		prepareBoxes(boxes);

		// sort boxes according to their dimensions
		sortBoxes(boxes);

		final Graph g = createGraph(boxes);

		addArtificialVertices(g);
		final Vertex source = g.vertices.get(0);
		source.weight = 0;

		calculateLongestPaths(g);

		return getLongestPath(g);
	}

	private static List<Box> getLongestPath(Graph g) {
		final List<Box> result = new ArrayList<Box>();

		final Vertex destination = g.vertices.get(g.vertices.size() - 1);
		Vertex vertex = destination;
		while (vertex.predecessor != null) {
			if (vertex != destination) {
				result.add(vertex.box);
			}
			vertex = vertex.predecessor;
		}

		Collections.reverse(result);
		return result;
	}

	private static void calculateLongestPaths(Graph g) {
		for (Vertex vertex : g.vertices) {
			for (Edge edge : vertex.edges) {
				if(edge.to.weight < vertex.weight + edge.weight) {
					edge.to.weight = vertex.weight + edge.weight;
					edge.to.predecessor = vertex;
				}
			}
		}
	}

	private static void addArtificialVertices(@Nonnull Graph g) {
		final Vertex source = Vertex.newVertex(Box.newEmptyBox());
		final Vertex destination = Vertex.newVertex(Box.newEmptyBox());

		for (Vertex vertex : g.vertices) {
			if (vertex.box.isTopBox()) {
				source.addNeighbour(vertex, 1);
			} else {
				if(vertex.edges.isEmpty()) {
					vertex.addNeighbour(destination, 1);
				}
			}
		}

		g.addVertexToTheStart(source);
		g.addVertex(destination);
	}

	private static void initSingleSourceGraph(@Nonnull Graph g) {

	}

	@Nonnull
	static Graph createGraph(@Nonnull List<Box> boxes) {
		final Graph g = new Graph();

		for (Box box : boxes) {
			final Vertex newVertex = Vertex.newVertex(box);

			boolean inside = false;
			for (Vertex vertex : g.vertices) {
				if (newVertex.box.canBeInsideOf(vertex.box)) {
					vertex.addNeighbour(newVertex, 1);
					inside = true;
				}
			}

			box.setTopBox(!inside);
			g.addVertex(newVertex);
		}

		return g;
	}

	private static void prepareBoxes(List<Box> boxes) {
		for (Box box : boxes) {
			box.sort();
		}
	}

	private static void sortBoxes(List<Box> boxes) {
		Collections.sort(boxes, new Comparator<Box>() {
			@Override
			public int compare(Box lb, Box rb) {
				return compareDimension(lb, rb, 0);
			}

			private int compareDimension(Box lb, Box rb, int dimension) {
				if(dimension >= lb.dimensions.length) {
					return 0;
				}

				final int ld = lb.dimensions[dimension];
				final int rd = rb.dimensions[dimension];
				if (ld > rd) {
					return -1;
				} else if (ld < rd) {
					return 1;
				} else {
					return compareDimension(lb, rb, dimension + 1);
				}
			}
		});
	}

	public static final class Graph {
		private final List<Vertex> vertices = new ArrayList<Vertex>();

		public void addVertex(@Nonnull Vertex vertex) {
			vertices.add(vertex);
		}

		public void addVertexToTheStart(@Nonnull Vertex vertex) {
			vertices.add(0, vertex);
		}
	}

	private static final class Vertex {

		@Nullable
		private Vertex predecessor;
		private int weight = Integer.MIN_VALUE;

		@Nonnull
		private final Box box;

		@Nonnull
		private final List<Edge> edges = new ArrayList<Edge>();

		private Vertex(@Nonnull Box box) {
			this.box = box;
		}

		private static Vertex newVertex(@Nonnull Box box) {
			return new Vertex(box);
		}

		public void addNeighbour(@Nonnull Vertex neighbour, int weight) {
			edges.add(Edge.newEdge(this, neighbour, weight));
		}
	}

	private static final class Edge {
		private final Vertex from;
		private final Vertex to;
		private final int weight;

		private Edge(Vertex from, Vertex to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		private static Edge newEdge(Vertex from, Vertex to, int weight) {
			return new Edge(from, to, weight);
		}
	}

	public static class Box {
		private int[] dimensions;
		private boolean topBox = false;

		private Box(int... dimensions) {
			this.dimensions = dimensions;
		}

		@Nonnull
		public static Box newBox(int... dimensions) {
			return new Box(dimensions);
		}

		public boolean canBeInsideOf(@Nonnull Box that) {
			for (int i = 0; i < dimensions.length; i++) {
				if(this.dimensions[i] >= that.dimensions[i]) {
					return false;
				}
			}

			return true;
		}

		public void sort() {
			Arrays.sort(dimensions);
			for(int i = 0; i < dimensions.length / 2; i++) {
				swap(dimensions, i, dimensions.length - i - 1);
			}
		}

		private void swap(int[] a, int i, int j) {
			int tmp = a[i];
			a[i] = a[j];
			a[j] = tmp; System.out.println();
		}

		public void setTopBox(boolean topBox) {
			this.topBox = topBox;
		}

		public boolean isTopBox() {
			return topBox;
		}

		public static Box newEmptyBox() {
			return newBox(0);
		}

		@Override
		public String toString() {
			return Arrays.toString(dimensions);
		}
	}

}
