package org.solovyev.graphs;

import javax.annotation.Nonnull;

public interface DistanceHeuristic<V> {

	double getDistance(@Nonnull Vertex<V> from, @Nonnull Vertex<V> to);
}
