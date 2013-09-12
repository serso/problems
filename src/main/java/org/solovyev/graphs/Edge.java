package org.solovyev.graphs;

import javax.annotation.Nonnull;

public final class Edge<V> implements Cloneable {

	@Nonnull
	private Vertex<V> from;

	@Nonnull
	private Vertex<V> to;

	private final double weight;

	private double flow;

	private Edge(@Nonnull Vertex<V> from, @Nonnull Vertex<V> to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Nonnull
	public static <V> Edge<V> newEdge(@Nonnull Vertex<V> from, @Nonnull Vertex<V> to, double weight) {
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

	public double getWeight() {
		return weight;
	}

	public double getResidualFlow() {
		return weight - flow;
	}

	public double getFlow() {
		return flow;
	}

	public void setFlow(double flow) {
		this.flow = Math.min(flow, weight);
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
