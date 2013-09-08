package org.solovyev.graphs;

import javax.annotation.Nonnull;
import java.util.*;

import static java.lang.Math.min;
import static org.solovyev.common.collections.Collections.reversed;
import static org.solovyev.graphs.Predecessor.newPredecessor;

public final class Graphs {

	public static final int MAX_WEIGHT = Integer.MAX_VALUE;
	public static final int MIN_WEIGHT = Integer.MIN_VALUE;

	private Graphs() {
		throw new AssertionError();
	}

	public static <V> int findMaxFlow(@Nonnull Graph<V> g, @Nonnull Vertex<V> s, @Nonnull Vertex<V> d) {
		while (true) {
			for (Vertex<V> vertex : g.getVertices()) {
				vertex.setWeight(MAX_WEIGHT);
				vertex.setFlow(MAX_WEIGHT);
				vertex.setVisited(false);
				vertex.setPredecessor(null);
			}

			findFlowPath(s, d);
			if (d.getPredecessor() == null) {
				return sumFlow(s);
			}
		}
	}

	private static <V> void findFlowPath(@Nonnull Vertex<V> s, @Nonnull Vertex<V> d) {
		final Queue<Vertex<V>> queue = new ArrayDeque<Vertex<V>>();
		queue.add(s);

		loop:
		while (!queue.isEmpty()) {
			final Vertex<V> from = queue.poll();
			if (!from.isVisited()) {
				from.setVisited(true);

				for (Edge<V> e : from.getEdges()) {
					final Vertex<V> to = e.getTo();
					if(e.getResidualFlow() > 0 && !to.isVisited()) {
						to.setPredecessor(newPredecessor(from, e));
						to.setFlow(min(from.getFlow(), e.getResidualFlow()));
						if(!to.equals(d)) {
							queue.add(to);
						} else {
							// path found => backtrack and set correct flow values
							backtrackFlow(d);

							break loop;
						}
					}
				}
			}
		}
	}

	private static <V> void backtrackFlow(@Nonnull Vertex<V> d) {
		Predecessor<V> p = d.getPredecessor();
		while (p != null) {
			final Edge<V> e = p.getEdge();
			e.setFlow(e.getFlow() + d.getFlow());
			p = p.getPredecessor();
		}
	}

	private static <V> int sumFlow(@Nonnull Vertex<V> s) {
		int flow = 0;
		for (Edge<V> edge : s.getEdges()) {
			flow += edge.getFlow();
		}
		return flow;
	}

	@Nonnull
	public static int[][] findShortestPaths(@Nonnull int[][] g) {
		final int size = g.length;

		final int[][] paths = new int[size][];
		for (int i = 0; i < size; i++) {
			paths[i] = Arrays.copyOf(g[i], size);
		}

		for (int k = 0; k < size; k++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if(paths[i][k] != MAX_WEIGHT && paths[k][j] != MAX_WEIGHT) {
						paths[i][j] = min(paths[i][j], paths[i][k] + paths[k][j]);
					}
				}
			}
		}

		return paths;
	}

	@Nonnull
	public static <V> Path<V> findShortestPath(@Nonnull Graph<V> g, @Nonnull Vertex<V> s, @Nonnull Vertex<V> d) {
		return findPathDijkstra(g, s, d, true);
	}

	@Nonnull
	public static <V> Path<V> findLongestPath(@Nonnull Graph<V> g, @Nonnull Vertex<V> s, @Nonnull Vertex<V> d) {
		return findPathDijkstra(g, s, d, false);
	}

	public static <V> Path<V> findPathDijkstra(@Nonnull Graph<V> g, @Nonnull Vertex<V> s, @Nonnull Vertex<V> d, boolean shortest) {
		final int boundary = shortest ? MAX_WEIGHT : MIN_WEIGHT;

		initSingleSource(g, s, boundary);

		final PriorityQueue<Vertex<V>> queue = new PriorityQueue<Vertex<V>>(g.getVertices().size(), new VertexPathComparator<V>());
		queue.add(s);

		while (!queue.isEmpty()) {
			final Vertex<V> v = queue.poll();
			if (v.getWeight() == boundary) {
				break;
			}

			for (Edge<V> edge : v.getEdges()) {
				final int weight = v.getWeight() + edge.getWeight();
				final Vertex<V> u = edge.getTo();
				if (shortest ? (u.getWeight() > weight) : (u.getWeight() < weight)) {
					u.setWeight(weight);
					u.setPredecessor(newPredecessor(v, edge));

					// resort queue according to the u.path change
					queue.remove(u);
					queue.add(u);
				}
			}
		}

		return Path.newPath(d);
	}

	public static <V> Path<V> findLongestPathBellmanForm(@Nonnull Graph<V> graph, @Nonnull Vertex<V> source, @Nonnull Vertex<V> destination) {
		return findPathBellmanForm(graph, source, destination, false);
	}

	public static <V> Path<V> findPathBellmanForm(@Nonnull Graph<V> graph, @Nonnull Vertex<V> source, @Nonnull Vertex<V> destination, boolean shortest) {
		initSingleSource(graph, source, shortest ? MAX_WEIGHT : MIN_WEIGHT);

		for (Vertex<V> from : graph.getVertices()) {
			for (Edge<V> edge : from.getEdges()) {
				final Vertex<V> to = edge.getTo();
				final int weight = from.getWeight() + edge.getWeight();
				if (shortest ? (to.getWeight() > weight) : (to.getWeight() < weight)) {
					to.setWeight(weight);
					to.setPredecessor(newPredecessor(from, edge));
				}
			}
		}

		return Path.newPath(destination);
	}

	static <V> void depthFirstSearch(@Nonnull Graph<V> graph, @Nonnull Vertex<V> source, @Nonnull Visitor<V> visitor) {
		initSingleSource(graph, source, 0);

		final Stack<Vertex<V>> stack = new Stack<Vertex<V>>();
		stack.add(source);

		while (!stack.isEmpty()) {
			depthFirstSearchVisit(stack.pop(), stack, visitor);
		}
	}

	static <V> void breadthFirstSearch(@Nonnull Graph<V> graph, @Nonnull Vertex<V> source, @Nonnull Visitor<V> visitor) {
		initSingleSource(graph, source, 0);

		final Queue<Vertex<V>> queue = new ArrayDeque<Vertex<V>>();
		queue.add(source);

		while (!queue.isEmpty()) {
			breadthFirstSearchVisit(queue.poll(), queue, visitor);
		}
	}

	private static <V> void breadthFirstSearchVisit(@Nonnull Vertex<V> v, @Nonnull Queue<Vertex<V>> queue, @Nonnull Visitor<V> visitor) {
		if (!v.isVisited()) {
			final boolean stop = visitor.visit(v);
			v.setVisited(true);
			if (!stop) {
				for (Edge<V> edge : v.getEdges()) {
					queue.add(edge.getTo());
				}
			}
		}
	}

	private static <V> void depthFirstSearchVisit(@Nonnull Vertex<V> v, @Nonnull Stack<Vertex<V>> stack, @Nonnull Visitor<V> visitor) {
		if (!v.isVisited()) {
			final boolean stop = visitor.visit(v);
			v.setVisited(true);
			if (!stop) {
				for (Edge<V> e : reversed(v.getEdges())) {
					stack.add(e.getTo());
				}
			}
		}
	}

	public static <V> void initSingleSource(@Nonnull Graph<V> graph, @Nonnull Vertex<V> source, int startValue) {
		for (Vertex<V> vertex : graph.getVertices()) {
			vertex.setWeight(startValue);
			vertex.setVisited(false);
			vertex.setPredecessor(null);
		}
		source.setWeight(0);
	}

	private static class VertexPathComparator<V> implements Comparator<Vertex<V>> {
		@Override
		public int compare(Vertex<V> o1, Vertex<V> o2) {
			final int path1 = o1.getWeight();
			final int path2 = o2.getWeight();
			if (path1 < path2) {
				return -1;
			} else if (path1 > path2) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	public static final class Path<V> {

		private final int length;

		@Nonnull
		private final List<Vertex<V>> vertices = new ArrayList<Vertex<V>>();

		private Path(int length) {
			this.length = length;
		}

		public int getLength() {
			return length;
		}

		@Nonnull
		public List<Vertex<V>> getVertices() {
			return vertices;
		}

		public static <V> Path<V> newPath(@Nonnull Vertex<V> destination) {
			final Path<V> path = new Path<V>(destination.getWeight());

			final List<Vertex<V>> vertices = new ArrayList<Vertex<V>>();
			vertices.add(destination);
			Predecessor<V> p = destination.getPredecessor();
			while (p != null) {
				vertices.add(p.getVertex());
				p = p.getPredecessor();
			}

			for (Vertex<V> vertex : reversed(vertices)) {
				path.vertices.add(vertex);
			}

			return path;
		}
	}
}
