package modelisation;

import java.util.ArrayList;
import java.io.*;

public class Graph {
	private ArrayList<Edge>[] adj;
	private final int V;
	int E;

	@SuppressWarnings("unchecked")
	public Graph(int N) {
		this.V = N;
		this.E = 0;
		adj = (ArrayList<Edge>[]) new ArrayList[N];
		for (int v = 0; v < N; v++)
			adj[v] = new ArrayList<Edge>(8);

	}

	public int vertices() {
		return V;
	}

	public void addEdge(Edge e) {
		int v = e.from;
		int w = e.to;
		adj[v].add(e);
		adj[w].add(e);
	}

	public void tograph(int[][] itr) {

		int ligne, colone;

		int noeud = 0;
		int dernier = (itr.length * itr[0].length) + 1;

		for (ligne = 0; ligne < itr.length; ligne++) {

			for (colone = 0; colone < itr[0].length; colone++) {
				noeud++;
				System.out.print(noeud + " ");
				// gauche droite
				if (colone == 0) {
					addEdge(new Edge(0, noeud, Integer.MAX_VALUE, 0));

				}
				if (colone == itr[0].length - 1) {
					addEdge(new Edge(noeud, dernier, itr[ligne][colone], 0));
				} else {
					addEdge(new Edge(noeud, noeud + 1, itr[ligne][colone], 0));

				}
				// droite gauche
				if (colone > 0) {
					addEdge(new Edge(noeud, noeud - 1, Integer.MAX_VALUE, 0));
				}
				// diag bas gauche
				if (ligne != itr.length && colone != 0
						&& noeud + itr[0].length < 13) {
					addEdge(new Edge(noeud, noeud + itr[0].length - 1,
							Integer.MAX_VALUE, 0));
				}
				// diag haut gauche
				if (ligne != 0 && colone != 0 && noeud - itr[0].length > 0) {
					addEdge(new Edge(noeud, noeud - itr[0].length - 1,
							Integer.MAX_VALUE, 0));
				}

			}
			System.out.println();
		}
		System.out.println(dernier);

	}

	public final Iterable<Edge> adj(int v) {
		return adj[v];
	}

	public final Iterable<Edge> edges() {
		ArrayList<Edge> list = new ArrayList<Edge>();
		for (int v = 0; v < V; v++)
			for (Edge e : adj(v)) {
				if (e.to != v)
					list.add(e);
			}
		return list;
	}

	public void writeFile(String s) {
		try {
			PrintWriter writer = new PrintWriter(s, "UTF-8");
			writer.println("digraph G{");
			for (Edge e : edges()) {
//				if (e.capacity == Integer.MAX_VALUE) {
//					writer.println(e.from + "->" + e.to + "[label=\"" + e.used
//							+ "/00\"];");
//				} else {
				writer.println(e.from + "->" + e.to + "[label=\"" + e.used
			+ "/" + e.capacity + "\"];");
//				}
			}
			writer.println("}");
			writer.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
