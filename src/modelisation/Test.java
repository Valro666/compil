package modelisation;

import java.util.Random;

class Test {
	static boolean visite[];

	public static void dfs(Graph g, int u) {
		visite[u] = true;
		System.out.println("Je visite " + u);
		for (Edge e : g.adj(u))
			/* Si on prend l'arête dans le bon sens */
			if (e.from == u)
				if (!visite[e.to])
					dfs(g, e.to);
	}

	public static void testGraph() {
		int n = 5;
		int i, j;
		Graph g = new Graph(n * n + 2);

		for (i = 0; i < n - 1; i++)
			for (j = 0; j < n; j++)
				g.addEdge(new Edge(n * i + j, n * (i + 1) + j, 1664 - (i + j),
						10 * j));

		for (j = 0; j < n; j++)
			g.addEdge(new Edge(n * (n - 1) + j, n * n, 666, 10 * j));

		for (j = 0; j < n; j++)
			g.addEdge(new Edge(n * n + 1, j, 10 * j, 10 * j));

		g.addEdge(new Edge(13, 17, 1337, 0));
		g.writeFile("test.dot");
		// dfs à partir du sommet 3
		visite = new boolean[n * n + 2];
		dfs(g, 3);
	}

	public static int[][] gen(int i, int j) {
		Random r = new Random();

		int[][] image = new int[i][j];
		
		int ligne, colone;

		for (ligne = 0; ligne < image.length; ligne++) {
			for (colone = 0; colone < image[0].length; colone++) {

				image[ligne][colone] = r.nextInt(255);
				System.out.print(image[ligne][colone] + " ");

			}
			System.out.println();

		}
		System.out.println("-----------------------------");
		return image;
	}

	public static void main(String[] args) {
		// testGraph();

		int bidule = Integer.MAX_VALUE;
		
		int[][] prof = { { 3, 11, 24, 39 }, { 8, 21, 29, 39 },
				{ 74, 80, 100, 200 } };

		int[][] image = gen(3, 4);

		Graph gh = new Graph((4 * 3) + 2);

		SeamCarving sc = new SeamCarving();
		// Q1
		sc.writepgm(image, "bidule");
		// Q2
		int[][] itr = sc.interest(prof);
		System.out.println("done !");

		gh.tograph(itr);

		gh.writeFile("truc.txt");
		// System.out.println(bidule);
		// new fenetre();

	}
}
