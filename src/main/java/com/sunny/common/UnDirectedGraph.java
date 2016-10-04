package com.sunny.common;

import java.util.List;

public final class UnDirectedGraph {
    private int vertices = 6;
    private int edges;
    private GNode root;
    private int[][] adjacencyMatrix;

	/*
	 *        A
	 *      /  \  \
	 *    B     C  D
	 *   /  \  /
	 *  E    F
	 *
	 *  G(6, 6) - 6 nodes and 6 edges.
	 *  Adjacency list
	 *  ---------------
	 *  G[A] = {B, C, D}
	 *  G[B] = {A, E, F}
	 *  G[C] = {A, F}
	 *  G[D] = {A}
	 *  G[E] = {B}
	 *  G[F] = {B, C}
	 *
	 *  Adjacency Matrix
	 *  -----------------
	 *    A B C D E F
	 *  A 0 1 1 1 0 0
	 *  B 1 0 0 0 1 1
	 *  C 1 0 0 0 0 1
	 *  D 1 0 0 0 0 0
	 *  E 0 1 0 0 0 0
	 *  F 0 1 1 0 0 0
	 */

	public UnDirectedGraph() {
		root = buildNodesAndAdjacencyList();
        adjacencyMatrix = buildAdjacencyMatrix();
	}

    public UnDirectedGraph(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
    }
	
	public GNode buildNodesAndAdjacencyList() {
		GNode A = new GNode('A');
		GNode B = new GNode('B');
		GNode C = new GNode('C');
		GNode D = new GNode('D');
		GNode E = new GNode('E');
		GNode F = new GNode('F');
		
		List<GNode> ANeighbours = A.getNeighbours();
		List<GNode> BNeighbours = B.getNeighbours();
		List<GNode> CNeighbours = C.getNeighbours();

		ANeighbours.add(B); // A -> B
		ANeighbours.add(C); // A -> C
		ANeighbours.add(D); // A -> D
		
		BNeighbours.add(E); // B -> E
		BNeighbours.add(F); // B -> F
		
		CNeighbours.add(F); // C -> F
		
        return A;
	}

    public GNode getRoot() {
        return root;
    }

    public int[][] buildAdjacencyMatrix() {

        /*
          A B C D E F
        A 1 1 1 1 0 0
        B 1 1 0 0 1 1
        C 1 0 1 0 0 1
        D 1 0 0 1 0 0
        E 0 1 0 0 1 0
        F 0 1 1 0 0 1
         */
		
		adjacencyMatrix = new int[vertices][vertices];
        adjacencyMatrix[0] = new int[] {1, 1, 1, 1, 0, 0};
        adjacencyMatrix[1] = new int[] {1, 1, 0, 0, 1, 1};
        adjacencyMatrix[2] = new int[] {1, 0, 1, 0, 0, 1};
        adjacencyMatrix[3] = new int[] {1, 0, 0, 1, 0, 0};
        adjacencyMatrix[4] = new int[] {0, 1, 0, 0, 1, 0};
        adjacencyMatrix[5] = new int[] {0, 1, 1, 0, 0, 1};

        return adjacencyMatrix;

	}

	public int getVertices() {
        return vertices;
    }

}
