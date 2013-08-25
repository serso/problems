package org.solovyev.problems;

import org.solovyev.graphs.ArtificialVertices;
import org.solovyev.graphs.Graph;
import org.solovyev.graphs.Vertex;

import javax.annotation.Nonnull;
import java.util.*;

import static org.solovyev.graphs.ArtificialVertices.newArtificialVertices;
import static org.solovyev.graphs.Vertex.newVertex;
import static org.solovyev.problems.NestedBoxes.Box.newEmptyBox;

/**
 * User: serso
 * Date: 8/25/13
 * Time: 3:15 PM
 */

// "Introduction to algorithms": 24-2
public class NestedBoxes {

	@Nonnull
	public static List<Box> getLongestNestedSequence(@Nonnull Box... boxes) {
		return getLongestNestedSequence(Arrays.asList(boxes));
	}

	@Nonnull
	public static List<Box> getLongestNestedSequence(@Nonnull List<Box> boxes) {
		// sort dimensions of each box
		prepareBoxes(boxes);

		// sort boxes according to their dimensions
		sortBoxes(boxes);

		final Graph<Box> g = createGraph(boxes);

		final ArtificialVertices<Box> av = addArtificialVertices(g);

		g.calculateSingleSourceLongestPaths(av.getSource());

		final List<Box> path = new ArrayList<Box>();
		for (Vertex<Box> vertex : g.getPathFrom(av.getDestination())) {
			path.add(vertex.getValue());
		}
		return path;
	}

	@Nonnull
	private static ArtificialVertices<Box> addArtificialVertices(@Nonnull Graph<Box> g) {
		final Vertex<Box> source = newVertex(newEmptyBox());
		final Vertex<Box> destination = newVertex(newEmptyBox());

		for (Vertex<Box> vertex : g.getVertices()) {
			if (vertex.getValue().isTopBox()) {
				source.addNeighbour(vertex, 1);
			} else {
				if(vertex.getEdges().isEmpty()) {
					vertex.addNeighbour(destination, 1);
				}
			}
		}

		g.addVertexToTheStart(source);
		g.addVertex(destination);

		return newArtificialVertices(source, destination);
	}

	@Nonnull
	static Graph<Box> createGraph(@Nonnull List<Box> boxes) {
		final Graph<Box> g = Graph.newGraph();

		for (Box box : boxes) {
			final Vertex<Box> newVertex = newVertex(box);

			boolean inside = false;
			for (Vertex<Box> vertex : g.getVertices()) {
				if (newVertex.getValue().canBeInsideOf(vertex.getValue())) {
					vertex.addNeighbour(newVertex, 1);
					inside = true;
				}
			}

			box.setTopBox(!inside);
			g.addVertex(newVertex);
		}

		return g;
	}

	private static void prepareBoxes(List<Box> boxes) {
		for (Box box : boxes) {
			box.sort();
		}
	}

	private static void sortBoxes(List<Box> boxes) {
		Collections.sort(boxes, new Comparator<Box>() {
			@Override
			public int compare(Box lb, Box rb) {
				return compareDimension(lb, rb, 0);
			}

			private int compareDimension(Box lb, Box rb, int dimension) {
				if(dimension >= lb.dimensions.length) {
					return 0;
				}

				final int ld = lb.dimensions[dimension];
				final int rd = rb.dimensions[dimension];
				if (ld > rd) {
					return -1;
				} else if (ld < rd) {
					return 1;
				} else {
					return compareDimension(lb, rb, dimension + 1);
				}
			}
		});
	}

	public static class Box {
		private int[] dimensions;
		private boolean topBox = false;

		private Box(int... dimensions) {
			this.dimensions = dimensions;
		}

		@Nonnull
		public static Box newBox(int... dimensions) {
			return new Box(dimensions);
		}

		public boolean canBeInsideOf(@Nonnull Box that) {
			for (int i = 0; i < dimensions.length; i++) {
				if(this.dimensions[i] >= that.dimensions[i]) {
					return false;
				}
			}

			return true;
		}

		public void sort() {
			Arrays.sort(dimensions);
			for(int i = 0; i < dimensions.length / 2; i++) {
				swap(dimensions, i, dimensions.length - i - 1);
			}
		}

		private void swap(int[] a, int i, int j) {
			int tmp = a[i];
			a[i] = a[j];
			a[j] = tmp; System.out.println();
		}

		public void setTopBox(boolean topBox) {
			this.topBox = topBox;
		}

		public boolean isTopBox() {
			return topBox;
		}

		public static Box newEmptyBox() {
			return newBox(0);
		}

		@Override
		public String toString() {
			return Arrays.toString(dimensions);
		}
	}

}
