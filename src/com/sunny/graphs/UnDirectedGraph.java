package com.sunny.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class UnDirectedGraph {
	private final int NUM_NODES = 5;
	private Node root;
	/*
	 *         A
	 *      /  \  \
	 *    B     C  D 
	 *   /  \  /
	 *  E    F
	 */

	private Map<Node, ArrayList<Node>> adjancencyList;
	private int[][] adjacencyMatrix;

	public UnDirectedGraph() {
		root = buildNodesAndAdjacencyList();
	}
	
	public static void main(String[] args) {
		UnDirectedGraph graph = new UnDirectedGraph();
		
	}
	
	/* 
	 * Depths First Search
	 */
	public void dfs() {
		
	}
	
	public Node buildNodesAndAdjacencyList() {
		Node A = new Node('A');
		Node B = new Node('B');
		Node C = new Node('C');
		Node D = new Node('D');
		Node E = new Node('E');
		Node F = new Node('F');
		
		ArrayList<Node> ANeighbours = new ArrayList<Node>();
		ArrayList<Node> BNeighbours = new ArrayList<Node>();
		ArrayList<Node> CNeighbours = new ArrayList<Node>();
		ArrayList<Node> DNeighbours = new ArrayList<Node>();
		ArrayList<Node> ENeighbours = new ArrayList<Node>();
		ArrayList<Node> FNeighbours = new ArrayList<Node>();

		ANeighbours.add(B); // A -> B
		ANeighbours.add(C); // A -> C
		ANeighbours.add(D); // A -> D
		
		BNeighbours.add(A); // B -> A
		BNeighbours.add(E); // B -> E
		BNeighbours.add(F); // B -> F
		
		CNeighbours.add(A); // C -> A
		CNeighbours.add(F); // C -> F
		
		DNeighbours.add(A); // D -> A
		
		ENeighbours.add(B); // E -> B
		
		FNeighbours.add(B); // F -> B
		ENeighbours.add(C); // F -> C
		
		adjancencyList = new HashMap<>();
		
		adjancencyList.put(A, ANeighbours);
		adjancencyList.put(B, BNeighbours);
		adjancencyList.put(C, CNeighbours);
		adjancencyList.put(D, DNeighbours);
		adjancencyList.put(E, ENeighbours);
		adjancencyList.put(F, FNeighbours);
		
		return A;
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

	private static class Node {
		
		char c;
		int visited;
		
		public Node(char c) {
			this.c = c;
			visited = 0;
		}
	}
}
