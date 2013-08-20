package org.solovyev.problems.topcoder;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: serso
 * Date: 8/20/13
 * Time: 11:18 PM
 */
// todo serso: NOT solved
// http://community.topcoder.com/stat?c=problem_statement&pm=884
public class CriminalProblem {

	public static int numPseudonyms(String[] dbConnections, String[] fieldData) {
		final Graph dbGraph = Graph.newInstance(dbConnections);
		final Graph fieldGraph = Graph.newInstance(fieldData);

		return numPseudonyms(dbGraph, fieldGraph);
	}

	private static int numPseudonyms(@Nonnull Graph dbGraph, @Nonnull Graph fieldGraph) {
		if (dbGraph.nodes.size() != fieldGraph.nodes.size()) {
			return -1;
		} else {
			int size = 0;

			for (int i = 0; i < dbGraph.nodes.size(); i++) {
				for (int j = 0; j < fieldGraph.nodes.size(); j++) {
					final Boolean[][] checked = new Boolean[fieldGraph.nodes.size()][fieldGraph.nodes.size()];
					final boolean[][] checking = new boolean[fieldGraph.nodes.size()][fieldGraph.nodes.size()];
					final int[] mapping = new int[fieldGraph.nodes.size()];
					for (int k = 0; k < mapping.length; k++) {
						mapping[k] = -1;
					}
					if (checkConnections(dbGraph, i, fieldGraph, j, checked, checking, mapping)) {
						int differentNames = 0;
						for (int k = 0; k < mapping.length; k++) {
							if (mapping[k] >= 0) {
								if (!dbGraph.getName(k).equals(fieldGraph.getName(mapping[k]))) {
									differentNames++;
								}
							} else {
								differentNames = Integer.MIN_VALUE;
							}
						}
						size = Math.max(differentNames, size);
					}
				}
			}

			return size == 0 ? -1 : size;
		}
	}

	private static boolean checkConnections(Graph dbGraph,
											int i,
											Graph fieldGraph,
											int j,
											Boolean[][] checked,
											boolean[][] checking, int[] mapping) {
		if (checking[i][j]) {
			return true;
		} else {
			setChecking(i, j, checking, true);
		}

		if (checked[i][j] != null) {
			setChecking(i, j, checking, false);
			return checked[i][j];
		}

		final List<Integer> dbConnections = dbGraph.nodes.get(i);
		final List<Integer> fieldConnections = fieldGraph.nodes.get(j);

		if (dbConnections.size() == fieldConnections.size()) {
			for (int k = 0; k < dbConnections.size(); k++) {
				if (!checkConnections(dbGraph, dbConnections.get(k), fieldGraph, fieldConnections.get(k), checked, checking, mapping)) {
					setChecking(i, j, checking, false);
					setChecked(i, j, checked, false);
					return false;
				}
			}
			mapping[i] = j;
			setChecking(i, j, checking, false);
			setChecked(i, j, checked, true);
			return true;
		} else {
			setChecking(i, j, checking, false);
			setChecked(i, j, checked, false);
			return false;
		}
	}

	private static void setChecking(int i, int j, boolean[][] checking, boolean value) {
		checking[i][j] = value;
		checking[j][i] = value;
	}
	private static void setChecked(int i, int j, Boolean[][] checked, boolean value) {
		checked[i][j] = value;
		checked[j][i] = value;
	}

	private static final class Graph {
		private final List<List<Integer>> nodes = new ArrayList<List<Integer>>();
		private final Map<String, Integer> mapping = new HashMap<String, Integer>();
		private final List<String> reverseMapping = new ArrayList<String>();
		private int index = 0;

		private Graph() {
		}

		private static Graph newInstance(String[] connections) {
			Graph graph = new Graph();
			for (final String Connection : connections) {
				final String[] names = Connection.split(" ");
				graph.addNode(names[0], names[1]);
			}
			return graph;
		}

		private int getIndex(@Nonnull String name) {
			Integer index = mapping.get(name);
			if (index == null) {
				index = this.index;
				reverseMapping.add(name);
				this.index++;
				mapping.put(name, index);
			}

			return index;
		}

		private String getName(int index) {
			return reverseMapping.get(index);
		}

		private void addNode(@Nonnull String n1, @Nonnull String n2) {
			final int i1 = getIndex(n1);
			final int i2 = getIndex(n2);

			addNode(i1, i2);
			addNode(i2, i1);
		}

		private void addNode(int i1, int i2) {
			if (i1 == nodes.size()) {
				nodes.add(new ArrayList<Integer>());
			}
			nodes.get(i1).add(i2);
		}
	}
}
