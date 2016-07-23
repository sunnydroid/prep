package com.sunny.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sunny.common.GNode;
import com.sunny.common.Logger;
import com.sunny.common.Node;
import com.sunny.common.Queue;
import com.sunny.common.UnDirectedGraph;

public class GraphBFS {

	private Map<Node, ArrayList<Node>> adjacencyList;
    private GNode root;
    int[][] adjacencyMatrix;

	public GraphBFS() {
		UnDirectedGraph unDirectedGraph = new UnDirectedGraph();
		adjacencyMatrix = unDirectedGraph.buildAdjacencyMatrix();
		root = unDirectedGraph.buildNodesAndAdjacencyList();
	}

    public GNode getRoot() {
        return root;
    }

    public static void main(String[] args) {
		GraphBFS graphTest = new GraphBFS();
		graphTest.BFS(graphTest.root, 'F', new LinkedList<>());
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

	public boolean doesPathExist(Node from, Node to) {
		Logger.log("Testing path from '" + from.getNum() + " to '" + to.getNum() + "'");
		Map<Node, Boolean> visitedMap = new HashMap<Node,Boolean>();
		GraphStack stack = new GraphStack();
		if(adjacencyList.containsKey(from)) {
			/* Get all adjacent nodes of the source node and push onto stack */
			pushToGraphStack(adjacencyList.get(from), stack);			
			/* iterate through the stack until empty */
			while(!stack.isEmpty()) {
				Node currentNode = stack.pop();
				Logger.log("Popped '" + currentNode.getNum() + "' from stack");
				if(visitedMap.get(currentNode) == null || visitedMap.get(currentNode) == false) {
					visitedMap.put(currentNode, true);
					if(currentNode == to) {
						Logger.log("Path exists!");
						return true;
					} else {
						pushToGraphStack(adjacencyList.get(currentNode), stack);
					}
				}
			}
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
