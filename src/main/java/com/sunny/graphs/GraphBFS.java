package com.sunny.graphs;

import com.sunny.common.Logger;
import com.sunny.common.Queue;
import javafx.util.Pair;

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
     * Check if path exists between 2 vertices.
     * Algorithm:
     *  create new array of vertex size to keep track of visited nodes
     *  create new queue of nodes to process
     *  add all children of source node to queue
     *  mark source node as visited
     *  while queue is not empty:
     *      node = queue.pop
     *      if node is visited:
     *          continue
     *      if node equals target node:
     *          return true
     *      add node's children to queue
     *      mark node as visited
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

}
