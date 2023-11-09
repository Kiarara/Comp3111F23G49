package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MazeSolver {
    private static final int[][] NEIGHBORS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Possible movements: up, down, left, right

    public static List<Integer> findShortestPath(int[][] map, int startVertex, int endVertex) {
        int n = map.length;
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
            parent[i] = -1;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);
        visited[startVertex] = true;

        boolean pathFound = false;

        while (!stack.isEmpty()) {
            int currentVertex = stack.pop();

            if (currentVertex == endVertex) {
                pathFound = true;
                break;
            }

            for (int[] neighbor : NEIGHBORS) {
                int newRow = (currentVertex / n) + neighbor[0];
                int newCol = (currentVertex % n) + neighbor[1];
                int newVertex = newRow * n + newCol;

                if (isValidMove(map, visited, newRow, newCol)) {
                    stack.push(newVertex);
                    visited[newVertex] = true;
                    parent[newVertex] = currentVertex;
                }
            }
        }

        if (!pathFound) {
            return new ArrayList<>(); // No path found
        }

        return reconstructPath(parent, startVertex, endVertex);
    }

    private static boolean isValidMove(int[][] map, boolean[] visited, int row, int col) {
        int n = map.length;
        return row >= 0 && row < n && col >= 0 && col < n && !visited[row * n + col] && map[row][col] == 0;
    }

    private static List<Integer> reconstructPath(int[] parent, int startVertex, int endVertex) {
        List<Integer> path = new ArrayList<>();
        int currentVertex = endVertex;

        while (currentVertex != startVertex) {
            path.add(currentVertex);
            currentVertex = parent[currentVertex];
        }

        path.add(startVertex);
        return path;
    }
}
