package org.solovyev.graphs;

import javax.annotation.Nonnull;

/**
* User: serso
* Date: 8/25/13
* Time: 7:31 PM
*/
public final class Edge<V> implements Cloneable {

	@Nonnull
	private Vertex<V> from;

	@Nonnull
	private Vertex<V> to;

	private final int weight;

	private int flow;

	private Edge(@Nonnull Vertex<V> from, @Nonnull Vertex<V> to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Nonnull
	public static <V> Edge<V> newEdge(@Nonnull Vertex<V> from, @Nonnull Vertex<V> to, int weight) {
		return new Edge<V>(from, to, weight);
	}

	@Nonnull
	public Vertex<V> getFrom() {
		return from;
	}

	@Nonnull
	public Vertex<V> getTo() {
		return to;
	}

	public int getWeight() {
		return weight;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	@Override
	protected Edge<V> clone(){
		try {
			final Edge<V> clone = (Edge<V>) super.clone();
			clone.to = this.to.clone();
			clone.from = this.from.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError(e);
		}
	}
}
