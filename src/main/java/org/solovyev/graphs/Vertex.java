package org.solovyev.graphs;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static org.solovyev.graphs.Edge.newEdge;

/**
* User: serso
* Date: 8/25/13
* Time: 7:31 PM
*/
public final class Vertex<V> implements Cloneable {

	@Nullable
	private Vertex<V> predecessor;

	private double weight = Double.NEGATIVE_INFINITY;

	@Nonnull
	private final V value;

	@Nonnull
	private List<Edge<V>> edges = new ArrayList<Edge<V>>();

	Vertex(@Nonnull V value) {
		this.value = value;
	}

	@Nonnull
	public static <V> Vertex<V> newVertex(@Nonnull V box) {
		return new Vertex<V>(box);
	}

	public void addNeighbour(@Nonnull Vertex<V> neighbour, int weight) {
		edges.add(newEdge(this, neighbour, weight));
	}

	@Nonnull
	public List<Edge<V>> getEdges() {
		return edges;
	}

	@Nullable
	public Vertex<V> getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(@Nullable Vertex<V> predecessor) {
		this.predecessor = predecessor;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Nonnull
	public V getValue() {
		return value;
	}

	@Override
	protected Vertex<V> clone() {
		try {
			final Vertex<V> clone = (Vertex<V>) super.clone();

			if(this.predecessor != null) {
				clone.predecessor = this.predecessor.clone();
			}

			clone.edges = new ArrayList<Edge<V>>(this.edges.size());
			for (Edge<V> edge : this.edges) {
				clone.edges.add(edge.clone());
			}

			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError(e);
		}
	}


}