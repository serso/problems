package org.solovyev.problems.topcoder;

/**
 * User: serso
 * Date: 8/25/13
 * Time: 8:43 PM
 */

import org.solovyev.graphs.ArtificialVertices;
import org.solovyev.graphs.Edge;
import org.solovyev.graphs.Graph;
import org.solovyev.graphs.Vertex;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.solovyev.graphs.Graph.newGraph;
import static org.solovyev.graphs.Graphs.findLongestPath;
import static org.solovyev.graphs.Vertex.newVertex;

/**
 * User: serso
 * Date: 8/20/13
 * Time: 10:25 PM
 */
// http://community.topcoder.com/stat?c=problem_statement&pm=1593
public class CircuitsProblem2 {

	public static int howLong(@Nonnull String[] connects, @Nonnull String[] costs) {
		final Graph<Component> g = createGraph(connects, costs);

		final ArtificialVertices<Component> av = addArtificialVertices(g);

		return findLongestPath(g, av.getSource(), av.getDestination()).getLength();
	}

	@Nonnull
	private static ArtificialVertices<Component> addArtificialVertices(@Nonnull Graph<Component> g) {
		final Vertex<Component> source = newVertex(Component.newEmptyComponent());
		final Vertex<Component> destination = newVertex(Component.newEmptyComponent());

		for (Vertex<Component> vertex : g.getVertices()) {
			if (vertex.getValue().isFirst()) {
				source.addNeighbour(vertex, 0);
			}

			if (vertex.getValue().isLast()) {
				vertex.addNeighbour(destination, 0);
			}
		}

		g.addVertexToTheStart(source);
		g.addVertex(destination);

		return ArtificialVertices.newArtificialVertices(source, destination);
	}

	private static Graph<Component> createGraph(@Nonnull String[] connects, @Nonnull String[] costs) {
		final Graph<Component> result = newGraph();

		final Vertex<Component>[] components = new Vertex[connects.length];
		for (int i = 0; i < connects.length; i++) {
			Vertex<Component> vertex = getOrCreateVertex(components, result, i);

			final String[] vertexNeighbours = connects[i].split(" ");
			final String[] vertexCosts = costs[i].split(" ");

			boolean last = true;
			for (int j = 0; j < vertexNeighbours.length; j++) {
				final String neighbour = vertexNeighbours[j];
				final String cost = vertexCosts[j];
				if (neighbour != null && !neighbour.isEmpty()) {
					final Integer neighbourId = Integer.valueOf(neighbour);
					Vertex<Component> neighbourVertex = getOrCreateVertex(components, result, neighbourId);
					neighbourVertex.getValue().setFirst(false);

					vertex.addNeighbour(neighbourVertex, Integer.valueOf(cost));
					neighbourVertex.getValue().addIncomingComponent(vertex.getValue());

					last = false;
				}
			}

			vertex.getValue().setLast(last);
		}

		final List<Vertex<Component>> topologicalSortedComponents = getTopologicalSortedVertices(result);
		result.getVertices().clear();
		result.getVertices().addAll(topologicalSortedComponents);

		return result;
	}

	@Nonnull
	private static List<Vertex<Component>> getTopologicalSortedVertices(@Nonnull Graph<Component> g) {
		final List<Vertex<Component>> topologicalSortedVertices = new ArrayList<Vertex<Component>>();
		final List<Vertex<Component>> vertices = new LinkedList<Vertex<Component>>();
		for (Vertex<Component> vertex : g.getVertices()) {
			if(vertex.getValue().isFirst()) {
				vertices.add(vertex);
			}
		}

		while (!vertices.isEmpty()) {
			final Vertex<Component> vertex = vertices.remove(0);
			topologicalSortedVertices.add(vertex);
			for (Edge<Component> edge : vertex.getEdges()) {
				Vertex<Component> to = edge.getTo();
				Vertex<Component> from = edge.getFrom();
				to.getValue().removeIncomingComponent(from.getValue());
				if(!to.getValue().hasIncomingComponents()) {
					vertices.add(to);
				}
			}
		}

		return topologicalSortedVertices;
	}

	private static Vertex<Component> getOrCreateVertex(@Nonnull Vertex[] components, @Nonnull Graph<Component> graph, int i) {
		Vertex<Component> vertex = components[i];
		if (vertex == null) {
			vertex = newVertex(Component.newComponent(i));
			graph.addVertex(vertex);
			components[i] = vertex;
		}
		return vertex;
	}

	private static final class Component {

		private final int id;

		private boolean first = true;
		private boolean last = true;

		@Nonnull
		private List<Component> incomingComponents = new ArrayList<Component>();

		private Component(int id) {
			this.id = id;
		}


		@Nonnull
		private static Component newComponent(int id) {
			return new Component(id);
		}

		@Nonnull
		private static Component newEmptyComponent() {
			return new Component(-1);
		}

		public boolean isFirst() {
			return first;
		}

		public void setFirst(boolean first) {
			this.first = first;
		}

		public boolean isLast() {
			return last;
		}

		public void setLast(boolean last) {
			this.last = last;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Component component = (Component) o;

			if (id != component.id) return false;

			return true;
		}

		@Override
		public int hashCode() {
			return id;
		}

		public void addIncomingComponent(@Nonnull Component incomingComponent) {
			this.incomingComponents.add(incomingComponent);
		}

		public void removeIncomingComponent(@Nonnull Component incomingComponent) {
			this.incomingComponents.remove(incomingComponent);
		}

		public boolean hasIncomingComponents() {
			return !this.incomingComponents.isEmpty();
		}
	}
}
