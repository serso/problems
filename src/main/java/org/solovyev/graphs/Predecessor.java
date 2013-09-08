package org.solovyev.graphs;

import javax.annotation.Nonnull;

/**
 * User: serso
 * Date: 9/8/13
 * Time: 2:36 PM
 */
public class Predecessor<V> {

	@Nonnull
	private final Vertex<V> vertex;

	@Nonnull
	private final Edge<V> edge;

	private Predecessor(@Nonnull Vertex<V> vertex, @Nonnull Edge<V> edge) {
		this.vertex = vertex;
		this.edge = edge;
	}

	@Nonnull
	public static <V> Predecessor<V> newPredecessor(@Nonnull Vertex<V> vertex, @Nonnull Edge<V> edge) {
		return new Predecessor<V>(vertex, edge);
	}

	@Nonnull
	public Vertex<V> getVertex() {
		return vertex;
	}

	@Nonnull
	public Edge<V> getEdge() {
		return edge;
	}

	@Nonnull
	public Predecessor<V> getPredecessor() {
		return vertex.getPredecessor();
	}
}
