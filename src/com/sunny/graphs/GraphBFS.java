package com.sunny.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sunny.common.Logger;
import com.sunny.common.Node;

public class GraphBFS {

	private Map<Node, ArrayList<Node>> adjacencyList;
	
	public GraphBFS() {
		UnDirectedGraph unDirectedGraph = new UnDirectedGraph();
		unDirectedGraph.buildAdjacencyList();
		adjacencyList = unDirectedGraph.getAdjancencyList();
	}
	
	public static void main(String[] args) {
		GraphBFS graphTest = new GraphBFS();
		graphTest.testBFS();

	}
	
	public void testBFS() {
		UnDirectedGraph unDirGraph = new UnDirectedGraph();
		List<Node> nodesList = unDirGraph.getNodeList();
		doesPathExist(nodesList.get(0), nodesList.get(2));
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
