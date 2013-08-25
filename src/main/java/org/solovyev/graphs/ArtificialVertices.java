package org.solovyev.graphs;

import javax.annotation.Nonnull;

/**
 * User: serso
 * Date: 8/25/13
 * Time: 9:05 PM
 */
public class ArtificialVertices<V> {

	@Nonnull
	private final Vertex<V> source;

	@Nonnull
	private final Vertex<V> destination;

	private ArtificialVertices(@Nonnull Vertex<V> source, @Nonnull Vertex<V> destination) {
		this.source = source;
		this.destination = destination;
	}

	@Nonnull
	public static <V> ArtificialVertices<V> newArtificialVertices(@Nonnull Vertex<V> source, @Nonnull Vertex<V> destination) {
		return new ArtificialVertices<V>(source, destination);
	}

	@Nonnull
	public Vertex<V> getSource() {
		return source;
	}

	@Nonnull
	public Vertex<V> getDestination() {
		return destination;
	}
}
