package org.solovyev.graphs;

import javax.annotation.Nonnull;

/**
 * User: serso
 * Date: 9/8/13
 * Time: 7:50 PM
 */
public interface DistanceHeuristic<V> {

	int getDistance(@Nonnull Vertex<V> from, @Nonnull Vertex<V> to);
}
