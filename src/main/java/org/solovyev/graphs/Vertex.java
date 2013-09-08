package org.solovyev.graphs;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.solovyev.graphs.Edge.newEdge;

/**
* User: serso
* Date: 8/25/13
* Time: 7:31 PM
*/
public final class Vertex<V> implements Cloneable {

	@Nullable
	private Predecessor<V> predecessor;

	private double weight = Graphs.MAX_WEIGHT;

	private double flow = 0;

	private boolean visited = false;

	@Nonnull
	private final V value;

	@Nonnull
	private List<Edge<V>> edges = new ArrayList<Edge<V>>();

	Vertex(@Nonnull V value) {
		this.value = value;
	}

	@Nonnull
	public static <V> Vertex<V> newVertex(@Nonnull V value) {
		return new Vertex<V>(value);
	}

	public void addNeighbour(@Nonnull Vertex<V> neighbour, double weight) {
		edges.add(newEdge(this, neighbour, weight));
	}

	public void addNeighbour(@Nonnull Vertex<V> neighbour) {
		addNeighbour(neighbour, 0d);
	}

	@Nonnull
	public List<Edge<V>> getEdges() {
		return edges;
	}

	@Nonnull
	public Iterable<Vertex<V>> getNeighbours() {
		return new Iterable<Vertex<V>>() {
			@Override
			public Iterator<Vertex<V>> iterator() {
				return new Iterator<Vertex<V>>() {

					@Nonnull
					private final Iterator<Edge<V>> edgeIterator = edges.iterator();

					@Override
					public boolean hasNext() {
						return edgeIterator.hasNext();
					}

					@Override
					public Vertex<V> next() {
						return edgeIterator.next().getTo();
					}

					@Override
					public void remove() {
						edgeIterator.remove();
					}
				};
			}
		};
	}

	@Nullable
	public Predecessor<V> getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(@Nullable Predecessor<V> predecessor) {
		this.predecessor = predecessor;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getFlow() {
		return flow;
	}

	public void setFlow(double flow) {
		// cannot exceed the weight of vertex
		this.flow = Math.min(flow, weight);
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
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
				throw new AssertionError("Should not clone if predecessor != null");
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
