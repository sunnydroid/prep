package com.sunny.common;

import java.util.ArrayList;
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
        neighbours = new ArrayList<>();
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
}
