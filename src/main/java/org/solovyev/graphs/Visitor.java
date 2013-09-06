package org.solovyev.graphs;

import javax.annotation.Nonnull;

public interface Visitor<V> {
	void visit(@Nonnull Vertex<V> v);
}
