package com.sunny.leetcode;

import com.sunny.common.Logger;
import com.sunny.graphs.Graph;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a graph and a source vertex in graph, find shortest paths from source to all vertices in the given graph.
 *
 * Consider the following graph
 *             A
 *      (3) /     \ (4)
 *        B        C
 *   (1) /    (7) /  \ (5)
 *      /        /    \
 *     D --(2)- E      F
 *            /
 *           / (5)
 *          G
 *
 * Shortest paths from A are
 *  A -> B (3)
 *  A -> C (4)
 *  A -> B -> D (4)
 *  A -> B -> D -> E (6)
 *  A -> B -> D -> E -> G (11)
 *  A -> C -> F (9)
 *
 */
public class ShortestPathDijkstra {

    /**
     * Approach: the shortest path problem can be solved using Dijkstra's method. You need 4 data structures
     *  1) Binary heap (min heap) to keep track of which vertex to process next based on distance
     *  2) Map to keep track of distance from source to destination
     *  3) Map to keep track of parent path for each vertex as it is updated in the binary heap
     *  4) Map to keep track of which vertices have been processed
     *
     *  Initialize all nodes except the origin node with distance cost of infinity and add the nodes to the binary
     *  heap. Set cost of origin node to 0, this will ensure that the source node gets popped first.
     *  For each node that gets popped, retrieve its neighbours, for each the neighbours, update the distance cost
     *  in the min heap with MIN(current cost, actual cost). This ensures nodes with lowest cost floats up to the top
     *  and will be processed before nodes with heavier weights.
     *
     *  The priority queue does not automatically rebalance to reflect this update, therefore we need to re-add the
     *  modified vertex so that it is inserted in the correct order. If we retrieve a vertex that has already been
     *  processed, we simply ignore it, hence the need for the 4th map to keep track of which vertices have already
     *  been processed.
     *
     *  If the child node in the heap was updated using weights from the current node, i.e. cost was lower from current
     *  node to child node than existing cost, update the 2 data structures
     *      update distance map with (weight from source to current node) + (weight from current node to child node)
     *      update parent node of child node as current node, i.e. cheapest path to child is from current node
     *      re-add updated node to the priority queue as it doesn't balance after adjusting individual nodes. This
     *      would be too expensive for the priority queue. We get around this limitation by adding a new updated
     *      element and ignoring the older vertices when they are retrieved durign the poll/remove
     *
     *  To get path and cost from source to destination, do a reverse traversal of the two maps.
     *
     *       A
     *  10 /   \ 20
     *    B -5- C
     *  16 \   / 20
     *       D
     *  Heap will begin with {<A, 0>, <B,INF>, <C,INF>, <D,INF>}
     *      A is popped out since it has the lowest weight:
     *          From the graph, A's neighbours are B,C. Update the weights in the heap with min(current_weight, new_weight)
     *          Stack will look like:
     *              {<B,10>, <C,20>, <D,INF>}
     *          Update the distance and parent tables:
     *              A -> 0;     A -> A
     *              B -> 10     B -> A
     *              C -> 20     C -> A
     *      B is popped out since it has the lowest weight
     *          From the graph, B's neighbours are C, D. Update the weights in teh heap with min(current_weight, new_weight)
     *          Stack will look like:
     *              {<C,5>, <D,INF>}
     *          Update the distance and parent tables:
     *              A -> 0;     A -> A
     *              B -> 10     B -> A
     *              C -> 15     C -> B
     *              D -> 26     D -> B
     *
     *           Since path to C has a lower cost via B, the parent of C was updated as B and the cost to C was updated
     *           as cost to B + cost from B to C (10 + 16)
     *
     *  To get cost from A -> D look up cost table.
     *  To get the actual path from A -> D, do a revers look up of the parent table until you reach A
     *      D -> B
     *      B -> A
     *      Therefore path = A -> B -> D
     *
     *
     * @param graph
     * @param from
     * @param to
     * @return
     */
    public static int[] findShortestPath(Graph graph, int from, int to) {
        /** Map of distance wight (value of the array) from source to destination (index of the array) */
        int[] distanceMap = new int[graph.getNumberOfVertices()];
        /** Map of parent (value of the array) from child (index of the array) i.e, shortest path to this
         * child is from the parent
         */
        int[] parentMap = new int[graph.getNumberOfVertices()];
        /** keep track of vertices that have already been processed since there may be multiple entries of the
         * same vertex as it is updated and re-added to the priority queue
         */
        boolean[] processedVertices = new boolean[graph.getNumberOfVertices()];
        /** Create a priority using a constructor that takes in a comparator. The comparator should
         * float nodes lower wight up the queue.
         */
        GraphNodeComparator compator = new GraphNodeComparator();
        Queue<GraphNode> processQueue = new PriorityQueue<>(compator);
        /** Add all vertices with weight INT.MAX to the queue, except the source node, set weight = 0 for source
         * We cannot add the actual nodes from the graph because the nodes are made up of pairs, which are immutable.
         * We need a data structure for which we can update the weight in the queue as we process each node that
         * has been popped off, hence the use of GraphNode which has mutable weight.
         */
        for(int i = 0; i < graph.getNumberOfVertices(); i++) {
            if (i == from) {
                processQueue.add(new GraphNode(i, 0));
            } else {
                processQueue.add(new GraphNode(i, Integer.MAX_VALUE));
            }
        }

        /** Process all nodes in the queue */
        while (!processQueue.isEmpty()) {
            GraphNode currentNode = processQueue.poll();
            /** if vertex has bee processed, ignore it, else mark it as processed and continue to process it */
            if (processedVertices[currentNode.vertex]) {
               continue;
            } else {
                processedVertices[currentNode.vertex] = true;
            }
            /** for each connected vertex, find vertex in priority queue and update node's weight with edge weight if
             * it is smaller
             */
            for (Pair<Integer, Integer> graphEdge : graph.getNeighbours(currentNode.vertex)) {
                /** no need to process last element */
                if (processQueue.isEmpty()) {
                    break;
                }
                List<GraphNode> nodesToAddBack = new ArrayList<>();

                Iterator<GraphNode> iterator = processQueue.iterator();

                while (iterator.hasNext()) {
                    GraphNode node = iterator.next();
                    if (node.vertex != graphEdge.getKey()) {
                        /** not the vertex we are looking for, move to the next one */
                        continue;
                    }
                    /** set the node's weight to the minimum between the current node weight and edge weight */
                    if (graphEdge.getValue() < node.weight) {
                        /** add a new updated node back to the priority queue so it can placed in the correct order,
                         * simply updating the node weight does not update the priority queue. Priority queue does not
                         * guarantee resorting after adding/removing operation. To get around this, we need to add new
                         * nodes with updated weights back to the queue. We do this after the iteration
                         */
                        nodesToAddBack.add(new GraphNode(node.vertex, graphEdge.getValue()));
                        /** update the parent map, i.e. you get to this node via current node */
                        parentMap[node.vertex] = currentNode.vertex;
                        /** update the distance map, i.e. distance to this node from source, via parent */
                        distanceMap[node.vertex] = distanceMap[currentNode.vertex] + graphEdge.getValue();
                    }
                    /** found node we were looking for, no need to iterate through the rest of the nodes */
                    break;
                }
                /** add nodes that were updated with new weights so they can be properly queued in sorted order */
                for (GraphNode nodeToAdd : nodesToAddBack) {
                    processQueue.add(nodeToAdd);
                }
            }

        }

        Logger.log("Distance map : " + Arrays.toString(distanceMap));
        Logger.log("Parent map : " + Arrays.toString(parentMap));

        /** to find the shortest path from source to destination, travers the parent map */
        StringBuilder stringBuilder = new StringBuilder();
        int currentVertix = to;
        while (currentVertix != from) {
            stringBuilder.append(currentVertix);
            stringBuilder.append("-");
            currentVertix = parentMap[currentVertix];
        }
        stringBuilder.append(from);

        Logger.log("Shortest path from " + from + " to " + to + " is : " + stringBuilder.reverse().toString() + " with " +
                "distance = " + distanceMap[to]);

        return distanceMap;

    }

    public static class GraphNodeComparator implements Comparator<GraphNode> {

        @Override
        public int compare(GraphNode o1, GraphNode o2) {
            return o1.weight - o2.weight;
        }
    }

    public static class GraphNode {
        /** vertex is immutable, weight is not */
        final int vertex;
        int weight;

        public GraphNode(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        /**
         *       A
         *  10 /   \ 20
         *    B -5- C
         *  16 \   / 20
         *       D
         */
        Graph graph = new Graph(4);

        /* A -> B*/
        graph.addEdge(0, 1, 10);
        /* A -> C*/
        graph.addEdge(0, 2, 20);
        /* B -> C*/
        graph.addEdge(1, 2, 5);
        /* B -> D*/
        graph.addEdge(1, 3, 16);
        /* C -> D*/
        graph.addEdge(2, 3, 20);

        findShortestPath(graph, 0, 2);
    }
}
