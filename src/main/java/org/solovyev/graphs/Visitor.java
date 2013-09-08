package org.solovyev.graphs;

import javax.annotation.Nonnull;

public interface Visitor<V> {

	/**
	 * @param v vertex
	 * @return true if search should not be continued after vertex v
	 */
	boolean visit(@Nonnull Vertex<V> v);
}
