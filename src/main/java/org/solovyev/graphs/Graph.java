package org.solovyev.graphs;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.solovyev.graphs.Vertex.newVertex;

/**
* User: serso
* Date: 8/25/13
* Time: 7:31 PM
*/
public final class Graph<V> implements Cloneable {

	@Nonnull
	private List<Vertex<V>> vertices = new ArrayList<Vertex<V>>();

	private Graph() {
	}

	@Nonnull
	public static <V> Graph<V> newGraph() {
		return new Graph<V>();
	}

	public void addVertex(@Nonnull Vertex<V> vertex) {
		vertices.add(vertex);
	}

	@Nonnull
	public Vertex<V> addVertex(@Nonnull V value) {
		final Vertex<V> result = newVertex(value);
		vertices.add(result);
		return result;
	}

	public void addVertexToTheStart(@Nonnull Vertex<V> vertex) {
		vertices.add(0, vertex);
	}

	@Nonnull
	public List<Vertex<V>> getVertices() {
		return vertices;
	}

	@Nonnull
	public Iterable<Edge<V>> getEdges() {
		return new Iterable<Edge<V>>() {
			@Override
			public Iterator<Edge<V>> iterator() {
				return new Iterator<Edge<V>>() {

					@Nonnull
					private final Iterator<Vertex<V>> vertexIterator = vertices.iterator();

					private Iterator<Edge<V>> iterator;

					@Override
					public boolean hasNext() {
						if(iterator == null) {
							return setIterator();
						} else {
							if(iterator.hasNext()) {
								return true;
							} else {
								return setIterator();
							}
						}
					}

					private boolean setIterator() {
						boolean hasNext = false;

						while (vertexIterator.hasNext()) {
							final Vertex<V> nextVertex = vertexIterator.next();
							iterator = nextVertex.getEdgesIterable().iterator();
							if (iterator.hasNext()) {
								hasNext = true;
								break;
							}
						}

						return hasNext;
					}

					@Override
					public Edge<V> next() {
						return iterator.next();
					}

					@Override
					public void remove() {
						iterator.remove();
					}
				};
			}
		};
	}

	@Override
	protected Graph<V> clone() {
		try {
			final Graph<V> clone = (Graph<V>) super.clone();

			clone.vertices = new ArrayList<Vertex<V>>(this.vertices.size());
			for (Vertex<V> vertex : vertices) {
				clone.vertices.add(vertex.clone());
			}

			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError(e);
		}
	}
}
