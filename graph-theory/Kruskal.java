import java.util.Arrays;
import java.util.Scanner;

// O(log V) = O(log E)
// E is at most V^2; logV^2 = 2*logV

public class Kruskal {
	// Disjoint-set implementation of Kruskal's (Greedy) Algorithm for Minimum
	// Spanning Tree (MST)
	// Undirected graph

	// Sample input:
	/*
	4 6 -> Nodes Edges
	1 2 5 -> src dest weight
	1 3 3
	4 1 6
	2 4 7
	3 2 4
	3 4 5
	1 -> starting node
	 */

	static class Edge implements Comparable<Edge> {
		int src, dest, weight;

		public Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge otherEdge) {
			return this.weight - otherEdge.weight;
		}
	}

	static class Subset {
		// parent is the root node of the tree
		int parent, height;

		public Subset(int parent) {
			this.parent = parent;
			height = 0;
		}
	}

	// Find the root
	private static int find(Subset[] subsets, int root) {
		if (subsets[root].parent != root) {
			subsets[root].parent = find(subsets, subsets[root].parent);
		}

		return subsets[root].parent;
	}

	// Combine the trees/sets
	private static void union(Subset[] subsets, int src, int dest) {
		int srcRoot = find(subsets, src);
		int destRoot = find(subsets, dest);

		// Use height to determine the smaller tree merging into larger tree
		if (subsets[src].height < subsets[dest].height) {
			subsets[src].parent = destRoot;
		} else if (subsets[src].height > subsets[dest].height) {
			subsets[dest].parent = srcRoot;
		} else {
			// Same height just use one as root
			subsets[destRoot].parent = srcRoot;
			subsets[destRoot].height++;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] nodes_edges = sc.nextLine().split(" ");
		int numNodes = Integer.valueOf(nodes_edges[0]);
		int numEdges = Integer.valueOf(nodes_edges[1]);

		Subset[] subsets = new Subset[numNodes];
		for (int i = 0; i < numNodes; i++) {
			subsets[i] = new Subset(i);
		}

		Edge[] edgeArray = new Edge[numEdges];

		for (int i = 0; i < numEdges; i++) {
			String[] src_dest_weight = sc.nextLine().split(" ");
			edgeArray[i] = new Edge(Integer.valueOf(src_dest_weight[0]) - 1, Integer.valueOf(src_dest_weight[1]) - 1,
					Integer.valueOf(src_dest_weight[2]));
		}

		// Ascending order because we want to start from lowest weight edge
		Arrays.sort(edgeArray);
		int weight = 0;

		for (Edge e : edgeArray) {
			if (find(subsets, e.src) != find(subsets, e.dest)) {
				// Trees/sets don't cross means no cycle
				union(subsets, e.src, e.dest);
				weight += e.weight;
			}
		}

		System.out.println(weight);
	}
}
