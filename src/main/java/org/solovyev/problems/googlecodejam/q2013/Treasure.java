package org.solovyev.problems.googlecodejam.q2013;

import org.solovyev.graphs.Graph;
import org.solovyev.graphs.Vertex;
import org.solovyev.problems.googlecodejam.GoogleCodeJamProblem;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * User: serso
 * Date: 9/9/13
 * Time: 10:56 PM
 */
public class Treasure extends GoogleCodeJamProblem {
	@Nonnull
	@Override
	protected CharSequence solve(@Nonnull String line, @Nonnull BufferedReader br) throws IOException {
		final String[] arguments = line.split(" ");
		final int keysCount = Integer.valueOf(arguments[0]);
		final int chestsCount = Integer.valueOf(arguments[1]);
		line = br.readLine();

		final int[] keys = new int[keysCount];
		final String[] keysArguments = line.split(" ");
		for (int i = 0; i < keys.length; i++) {
			keys[i] = Integer.valueOf(keysArguments[i]);
		}


		final Chest[] chests = readChests(br, chestsCount);
		final Graph<Chest> g = Graph.newGraph();

		final Vertex<Chest> startVertex = g.addVertex(Chest.newChest(-1, -1, keys));
		final Vertex<Chest>[] vertices = toVertices(chests, g);
		addNeighbours(startVertex, vertices);
		for (Vertex<Chest> vertex : vertices) {
			addNeighbours(vertex, vertices);
		}


		return null;
	}

	private void addNeighbours(Vertex<Chest> vertex, Vertex<Chest>[] vertices) {
		for (int key : vertex.getValue().keys) {
			for (Vertex<Chest> v : vertices) {
				if(v.getValue().key == key) {
					vertex.addNeighbour(v);
				}
			}
		}
	}

	@Nonnull
	private Vertex<Chest>[] toVertices(Chest[] chests, Graph<Chest> g) {
		final Vertex<Chest>[] vertices = new Vertex[chests.length];
		for (int i = 0; i < chests.length; i++) {
			vertices[i] = g.addVertex(chests[i]);
		}
		return vertices;
	}

	@Nonnull
	private Chest[] readChests(BufferedReader br, int chestsCount) throws IOException {
		final Chest[] chests = new Chest[chestsCount];
		for (int i = 0; i < chestsCount; i++) {
			final String line = br.readLine();

			final String[] chestArguments = line.split(" ");

			final int key = Integer.valueOf(chestArguments[0]);
			final int[] keys = new int[chestArguments.length - 1];
			for (int j = 2; j < chestArguments.length; j++) {
				keys[j - 2] = Integer.valueOf(chestArguments[j]);
			}

			chests[i] = Chest.newChest(i, key, keys);
		}
		return chests;
	}

	private static final class Chest {
		private final int id;
		private int key;
		private final int[] keys;

		private Chest(int id, int key, int[] keys) {
			this.id = id;
			this.key = key;
			this.keys = keys;
		}

		@Nonnull
		private static Chest newChest(int id, int key, int[] keys) {
			return new Chest(id, key, keys);
		}


	}
}
