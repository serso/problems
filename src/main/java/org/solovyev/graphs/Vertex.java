package org.solovyev.graphs;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
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

	private int inDegree = 0;
	private int outDegree = 0;

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
		neighbour.inDegree++;
		outDegree++;
	}

	public void addNeighbour(@Nonnull Vertex<V> neighbour) {
		addNeighbour(neighbour, 0d);
	}

	@Nonnull
	public List<Edge<V>> getEdges() {
		return Collections.unmodifiableList(edges);
	}

	@Nonnull
	public Iterable<Edge<V>> getEdgesIterable() {
		return new Iterable<Edge<V>>() {
			@Override
			public Iterator<Edge<V>> iterator() {
				return new Iterator<Edge<V>>() {

					@Nonnull
					private final Iterator<Edge<V>> iterator = edges.iterator();
					private Edge<V> lastEdge;

					@Override
					public boolean hasNext() {
						return iterator.hasNext();
					}

					@Override
					public Edge<V> next() {
						lastEdge = iterator.next();
						return lastEdge;
					}

					@Override
					public void remove() {
						iterator.remove();
						lastEdge.getTo().inDegree--;
						lastEdge.getFrom().outDegree--;
					}
				};
			}
		};
	}

	@Nonnull
	public Iterable<Vertex<V>> getNeighbours() {
		return new Iterable<Vertex<V>>() {
			@Override
			public Iterator<Vertex<V>> iterator() {
				return new Iterator<Vertex<V>>() {

					@Nonnull
					private final Iterator<Edge<V>> edgeIterator = edges.iterator();
					private Edge<V> lastNext;

					@Override
					public boolean hasNext() {
						return edgeIterator.hasNext();
					}

					@Override
					public Vertex<V> next() {
						lastNext = edgeIterator.next();
						return lastNext.getTo();
					}

					@Override
					public void remove() {
						edgeIterator.remove();
						lastNext.getTo().inDegree--;
						outDegree--;
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

	public int getInDegree() {
		return inDegree;
	}

	public int getOutDegree() {
		return outDegree;
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
