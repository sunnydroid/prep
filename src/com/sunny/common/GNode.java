package com.sunny.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sunshah on 7/7/16.
 */
public class GNode {
    private char data;
    private int visited;
    private List<GNode> neighbours;

    public GNode(char data) {
        this.data = data;
        visited = 0;
        neighbours = new LinkedList<>();
    }

    public char getData() {
        return data;
    }

    public int getVisited() {
        return visited;
    }

    public List<GNode> getNeighbours() {
        return neighbours;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("data=").append(data).append("; visited=").append(visited).toString();
    }
}
