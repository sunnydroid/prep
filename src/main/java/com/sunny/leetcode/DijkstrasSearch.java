package com.sunny.leetcode;

/**
 * When implementing Dijkstra’s algorithm, two sets are maintained, one set contains list of vertices already
 * included in SPT (Shortest Path Tree), other set contains vertices not yet included. With adjacency list
 * representation, all vertices of a graph can be traversed in O(V+E) time using BFS. The idea is to traverse
 * all vertices of graph using BFS and use a Min Heap to store the vertices not yet included in SPT (or the vertices
 * for which shortest distance is not finalized yet).
 *
 * Min Heap is used as a priority queue to get the minimum distance vertex from set of not yet processed vertices.
 * Time complexity of operations like extract-min and decrease-key value is O(LogV) for Min Heap (not true for Java
 * Priority Queue implementation, see caveat below).
 *
 * Distances of vertices not already processed are set to infinity when adding to the priority queue. As we process
 * from the source vertex, we update the weights in the priority queue of each neighbouring vertexes IF it is smaller
 * than the existing weight. This allows vertices with the smallest distances to float up the queue and hence vertex
 * with the smallest distance will be processed next when the queue is polled.
 *
 * Caveat: when weights of elements already in the priority queue are updated, the queue does not automatically sort.
 * The only time it sorts is when elements are added or removed using add() and remove operations(). Once distances
 * are updated, create a new vertex with updated weight and add it to the priority queue. But now we'll have duplicate
 * vertexes with different weights. This is why we need to maintain a second list of already processed vertices. If
 * the vertex has already been processed after polling the queue, ignore it. We do not bother removing older vertices once
 * the weight is updated is because each add/remove operation is O(logV) operation due to heapify operation
 *
 *
 * The PriorityQueue implementation in Java can be initialised using a comparator which defines the natural ordering
 * of the elements.
 *
 */
public class DijkstrasSearch {
}
