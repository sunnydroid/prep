package com.sunny.graphs;

import com.sunny.common.Logger;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Path between two nodes is directed
 * Directed graphs are also know as digraphs
 *
 * Graphs are a formal representation of connected nodes. A graph
 * is defined as
 *  G = (V,E)
 *      where V = set of nodes a.k.a nodes
 *      and E = set of edges a.k.a links
 *
 * Both vertices and edges are sets, however edge representation shows direction of edge and order is important.
 *  V = {v1, v2, v3, v4}
 *  E = {(v1, v2), (v1, v3), (v2, v3), (v3, v4)}
 *
 * Twitter model is an example of directed graph, where the direction of path represents follower and following
 * Facebook connections are an example of undirected graph where a connection is bidirectional
 *
 * A graph can be dense, i.e. with many edges between vertices or sparse
 * An edge list (or array) is a representation of all the edges in the graph. Therefore to represent
 * edges requires O(E) space and similarly, finding an edge requires O(E) time. Searching for edges can
 * be costly if the graph is big and densely connected.
 * [
 *  [1, 2],
 *  [2, 3],
 *  [3, 1]
 * ]
 *
 * Though this representation is simple an adjacency
 * matrix will be a better solution for big, densely connected graphs.
 *
 * Adjacency matrix serves as a lookup table where a value of 1 represents presence of an edge between vertices
 * while 0 means no edge. For example the following graph can be represented as:
 *
 *      1
 *     / \
 *    2---3
 *
 *    1 2 3
 *  |------
 * 1| 0 1 1
 * 2| 1 0 1
 * 3| 1 1 0
 *
 *
 * The main diagonal will be all 0s unless there is a node has an edge with itself.
 * We can determine if the graph is directed or not simply by looking to see if the graph is symmetrical about the
 * main diagonal.
 *
 * The matrix would be defined as:
 * [
 *  [0, 1, 1],
 *  [1, 0, 1],
 *  [1, 1, 0]
 *  ]
 *
 *  Searching for an edge using the matrix representation can be done in O(1) time. Adding or removing an edge can also
 *  be done in O(1) time.
 *  The trade off here is that if the graph is large and sparse, it will be mostly filled with 0s, but take up a lot
 *  of space. Storage requirements are O(v^2)
 *
 * The hybrid approach, Adjacency list is an array of linked list that serves as a representation of the graph but also
 * makes it easier to see which other adjacent vertices.
 *
 * Each vertex is an index in the list. The graph above can be represented as:
 * 1 => 2 -> 3
 * 2 => 3 -> 1
 * 3 => 1 -> 2
 *
 * Representing a graph using Adjacency list is space efficient, requiring O(V + E) space for directed graphs and
 * O(V + 2E) for undirected graph since each edge will be present for both vertices.
 * Edges can be retrieved in O(1) time, worst case O(d) where d is the degree, or depth of the graph, i.e. number
 * of connections for the vertex with most number of edges. Max d = |V| - 1, i.e vertex is connected to every other
 * vertex.
 *
 */
public class Graph {
    /** Edges will be represented as an array of LinkedList of Pair
     * Pair will allow to tag on a weight to an edge if needed
     */
    LinkedList<Pair<Integer,Integer>>[] adjacencyList;
    int numberOfVertices;

    public Graph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        adjacencyList = new LinkedList[numberOfVertices];
        for(int i = 0; i < numberOfVertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to, int weight) {
        adjacencyList[from].add(new Pair(to, weight));
    }

    public void removeEdge(int from, int to) {
        ListIterator<Pair<Integer, Integer>> listIterator = adjacencyList[from].listIterator();
        while (listIterator.hasNext()) {
            Pair<Integer, Integer> pair = listIterator.next();
            if (pair.getKey().intValue() == to) {
                listIterator.remove();
                return;
            }
        }
    }

    public void printGraph() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < adjacencyList.length; i++) {
            stringBuilder.append(i + "=>");
            LinkedList<Pair<Integer, Integer>> edges = adjacencyList[i];
            for (Pair<Integer, Integer> pair : edges) {
                stringBuilder.append("{" + pair.getKey() + ", " + pair.getValue() + "} -> ");
            }
            stringBuilder.append("\n");
        }

        Logger.log(stringBuilder.toString());
    }
}
