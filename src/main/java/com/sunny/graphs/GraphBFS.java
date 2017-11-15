package com.sunny.graphs;

import com.sunny.common.GNode;
import com.sunny.common.Logger;
import com.sunny.common.Node;
import com.sunny.common.Queue;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
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
public class GraphBFS {

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        /** A = 0, B = 1 ...F = 5 */
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(0, 3, 1);

        graph.addEdge(1, 0, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(1, 5, 1);

        graph.addEdge(2, 0, 1);
        graph.addEdge(2, 5, 1);

        graph.addEdge(3, 0, 1);

        graph.addEdge(4, 1, 1);

        graph.addEdge(5, 1, 1);
        graph.addEdge(5, 2, 1);

        graph.printGraph();
//        graph.removeEdge(0, 1);
//        graph.printGraph();

        doesPathExistBFS(graph, 0, 4);
        graph.removeEdge(1, 4);
        doesPathExistBFS(graph, 0, 4);
    }

    /**
     * Breath First Search Algorithm()
     * BFS(GNode start, int targetNodeValue)
     *  if targetNodeValue equals current root node value, return list of visited nodes
     *  if targetNodeValue not equal current root node value, and there are no children, return empty list (path not available)
     *  add current node to visited node list
     *  instantiate new queue of nodes to process
     *  add current node's children to queue
     *  while queue is not empty:
     *      current node -> pop from queue
     *      recurse BFS(current node, target node, visited list
     */
    private void BFS(GNode currentRoot, char targetValue, List<GNode> visitedNodes) {
        /* create new list and add current node and previously visited list to track new route */
        List<GNode> newVisitedNodes = new LinkedList<>(visitedNodes);
        newVisitedNodes.add(currentRoot);

        if(currentRoot.getData() == targetValue) {
            /* reached target, return list of visited nodes */
            Logger.log("Target node reached via:");
            Logger.logList(newVisitedNodes);
            return;
        }
        if(currentRoot.getNeighbours().isEmpty()) {
            /* no path available to target, return empty list if problem requires*/
            Logger.log("Target node not reachable via:");
            Logger.logList(newVisitedNodes);
//            return Collections.emptyList();
            return;
        }
        /* create new queue and all neighbours that need to be recursed */
        Queue<GNode> queue = new Queue<>();
        queue.enqueue(currentRoot.getNeighbours());
        /* recurse till queue is not empty */
        while(!queue.isEmpty()) {
            BFS(queue.dequeue(), targetValue, newVisitedNodes);
        }
    }

    /**
     * Check if path exists between 2 vertices.
     * Algorithm:
     *  mark source node as visited
     *  create new queue of nodes to process
     *  create new array of vertex size to keep track of visited nodes
     *  add all children of source node to queue
     *  while queue is not empty:
     *      node = queue.pop
     *      if node is visited:
     *          continue
     *      mark node as visited
     *      if node equals target node:
     *          return true
     *      add node's children to queue
     *
     *  return false
     *
     * @param graph
     * @param from
     * @param to
     * @return
     */
	public static boolean doesPathExistBFS(Graph graph, int from, int to) {
		Logger.log("Testing path from '" + from + " to '" + to);
        boolean[] visited = new boolean[graph.numberOfVertices];
        Queue<Pair<Integer, Integer>> queue = new Queue<>();
        /** enqueue all of source node's children */
        queue.enqueue(graph.adjacencyList[from]);
        /** mark source as visited */
        visited[from] = true;
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.dequeue();
            /** if node has been visited, continue to the next node */
            if (visited[pair.getKey()]) {
                continue;
            }
            /** if node is the target, return true */
            if (pair.getKey() == to) {
                Logger.log("Path exists");
                return true;
            }
            /** otherwise, mark node as visited and add its children to queue */
            visited[pair.getKey()] = true;
            queue.enqueue(graph.adjacencyList[pair.getKey()]);
        }

        Logger.log("Path does not exist");
		return false;
	}

	private void pushToGraphStack(List<Node> nodeList, GraphStack graphStack) {
		for(Node node : nodeList) {
			Logger.log("pushing '" + node.getNum() +"' to stack");
			graphStack.push(node);
		}
	}
}
