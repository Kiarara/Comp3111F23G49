package Function_B;
import java.util.*;

import Function_C.VertexLocation;
import Shared.*;

public class ShortestPathFinder {
    public int[][] map;
    public int numRows = 30;
    public int numCols = 30;
    public byte[][] visited;
    public byte[][] distance;
    public int[][] prevRow;
    public int[][] prevCol;

    LinkedList<int[]> shortestpath;
    Queue<int[]> queue;


    public ShortestPathFinder(Maze m) {
        map = m.maze;
        visited = new byte[numRows][numCols];
        distance = new byte[numRows][numCols];
        prevRow = new int[numRows][numCols];
        prevCol = new int[numRows][numCols];

        shortestpath = new LinkedList<>();
        queue = new LinkedList<>();
    }

    public LinkedList<int[]> findShortestPath(VertexLocation a, VertexLocation b) {
        int startRow = a.x;
        int startCol = a.y;
        int endRow = b.x;
        int endCol = b.y;
        shortestpath.clear();
        resetState();
        bfs(startRow, startCol, endRow, endCol);

        if (visited[endRow][endCol] == 0) {
            System.out.println("No path found.");
            shortestpath = null;
        }
        else {

            int row = endRow;
            int col = endCol;

            while (row != startRow || col != startCol) {
                shortestpath.addFirst(new int[]{row, col});
                int preRow = prevRow[row][col];
                int preCol = prevCol[row][col];
                row = preRow;
                col = preCol;
            }
            shortestpath.addFirst(new int[]{startRow, startCol});
        }
        return shortestpath;
    }

    private void bfs(int startRow, int startCol, int endRow, int endCol) {
        queue.clear();
        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = 1;
        distance[startRow][startCol] = 0;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] vertex = queue.poll();
            int row = vertex[0];
            int col = vertex[1];

            if (row == endRow && col == endCol) {
                return;
            }

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (isValidMove(newRow, newCol)) {
                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = 1;
                    distance[newRow][newCol] = (byte) (distance[row][col] + 1);
                    prevRow[newRow][newCol] = row;
                    prevCol[newRow][newCol] = col;
                }
            }
        }
    }

    public int[] find_next(VertexLocation a, VertexLocation b){
        findShortestPath(a, b);
        int[] next = new int[2];
        next[0] = shortestpath.get(shortestpath.size()-2)[0];
        next[1] = shortestpath.get(shortestpath.size()-2)[1];
        return next;
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols && map[row][col] == 0 && visited[row][col] == 0;
    }

    public void resetState() {
        for (int i = 0; i < numRows; i++) {
            Arrays.fill(visited[i], (byte) 0);
            Arrays.fill(distance[i], (byte) 0);
            Arrays.fill(prevRow[i], 0);
            Arrays.fill(prevCol[i], 0);
        }
    }

}