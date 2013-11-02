package com.sunny.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		
		adjancencyList = new HashMap<Node, ArrayList<Node>>();
		
		adjancencyList.put(A, ANeighbours);
		adjancencyList.put(B, BNeighbours);
		adjancencyList.put(C, CNeighbours);
		adjancencyList.put(D, DNeighbours);
		adjancencyList.put(E, ENeighbours);
		adjancencyList.put(F, FNeighbours);
		
		return A;
	}

	public void buildAdjacencyMatrix() {
		
		adjacencyMatrix = new int[NUM_NODES][NUM_NODES];

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
