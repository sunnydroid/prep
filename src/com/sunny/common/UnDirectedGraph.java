package com.sunny.common;

import java.util.List;

public final class UnDirectedGraph {
	private final int NUM_NODES = 5;
	private GNode root;
	/*
	 *         A
	 *      /  \  \
	 *    B     C  D 
	 *   /  \  /
	 *  E    F
	 */

	private int[][] adjacencyMatrix;

	public UnDirectedGraph() {
		root = buildNodesAndAdjacencyList();
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
		
		adjacencyMatrix = new int[NUM_NODES][NUM_NODES];
        adjacencyMatrix[0] = new int[] {1, 1, 1, 1, 0, 0};
        adjacencyMatrix[1] = new int[] {1, 1, 0, 0, 1, 1};
        adjacencyMatrix[2] = new int[] {1, 0, 1, 0, 0, 1};
        adjacencyMatrix[3] = new int[] {1, 0, 0, 1, 0, 0};
        adjacencyMatrix[4] = new int[] {0, 1, 0, 0, 1, 0};
        adjacencyMatrix[5] = new int[] {0, 1, 1, 0, 0, 1};

        return adjacencyMatrix;

	}

	public int getNUM_NODES() {
        return NUM_NODES;
    }

}
