package org.solovyev.graphs;

import javax.annotation.Nonnull;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public final class Graphs {

	private Graphs() {
		throw new AssertionError();
	}

	static <V> void depthFirstSearch(@Nonnull Graph<V> graph, @Nonnull Visitor<V> visitor) {
		final Graph<SearchVertexValue<V>> newGraph = prepareGraph(graph);

		final Stack<Vertex<SearchVertexValue<V>>> stack = new Stack<Vertex<SearchVertexValue<V>>>();
		for (Vertex<SearchVertexValue<V>> v : newGraph.getVertices()) {
			stack.add(v);
		}

		while (!stack.isEmpty()) {
			depthFirstSearchVisit(stack.pop(), stack, visitor);
		}
	}

	static <V> void breadthFirstSearch(@Nonnull Graph<V> graph, @Nonnull Visitor<V> visitor) {
		final Graph<SearchVertexValue<V>> newGraph = prepareGraph(graph);

		final Queue<Vertex<SearchVertexValue<V>>> queue = new ArrayDeque<Vertex<SearchVertexValue<V>>>(graph.getVertices().size());
		for (Vertex<SearchVertexValue<V>> v : newGraph.getVertices()) {
			queue.add(v);
		}

		while (!queue.isEmpty()) {
			breadthFirstSearchVisit(queue.poll(), queue, visitor);
		}
	}

	private static <V> void breadthFirstSearchVisit(@Nonnull Vertex<SearchVertexValue<V>> v, @Nonnull Queue<Vertex<SearchVertexValue<V>>> queue, @Nonnull Visitor<V> visitor) {
		if(!v.getValue().visited) {
			visitor.visit(v.getValue().value);
			v.getValue().visited = true;
			for (Edge<SearchVertexValue<V>> edge : v.getEdges()) {
				queue.add(edge.getTo());
			}
		}
	}

	private static <V> void depthFirstSearchVisit(@Nonnull Vertex<SearchVertexValue<V>> v, @Nonnull Stack<Vertex<SearchVertexValue<V>>> stack, @Nonnull Visitor<V> visitor) {
		if(!v.getValue().visited) {
			visitor.visit(v.getValue().value);
			v.getValue().visited = true;
			for (Edge<SearchVertexValue<V>> e : v.getEdges()) {
				stack.add(e.getTo());
			}
		}
	}

	private static <V> Graph<SearchVertexValue<V>> prepareGraph(Graph<V> g) {
		final Graph<SearchVertexValue<V>> newGraph = Graph.newGraph();

		final Map<Vertex<V>, Vertex<SearchVertexValue<V>>> vertices = new HashMap<Vertex<V>, Vertex<SearchVertexValue<V>>>();
		for (Vertex<V> vertex : g.getVertices()) {
			Vertex<SearchVertexValue<V>> newVertex = getNewVertex(vertices, vertex);
			for (Edge<V> edge : vertex.getEdges()) {
				newVertex.addNeighbour(getNewVertex(vertices, edge.getTo()), edge.getWeight());
			}
			newGraph.addVertex(newVertex);
		}

		return newGraph;
	}

	@Nonnull
	private static <V> Vertex<SearchVertexValue<V>> getNewVertex(@Nonnull Map<Vertex<V>, Vertex<SearchVertexValue<V>>> vertices, @Nonnull Vertex<V> v) {
		Vertex<SearchVertexValue<V>> newVertex = vertices.get(v);
		if (newVertex == null) {
			newVertex = new Vertex<SearchVertexValue<V>>(new SearchVertexValue<V>(v));
			vertices.put(v, newVertex);
		}
		return newVertex;
	}

	private static final class SearchVertexValue<V> {

		private final Vertex<V> value;

		private boolean visited;

		private SearchVertexValue(Vertex<V> value) {
			this.value = value;
		}
	}

}
