package Function_C;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ShortestPathFinder {
    private int[][] map;
    private int numRows;
    private int numCols;
    private byte[][] visited;
    private byte[][] distance;
    private int[][] prevRow;
    private int[][] prevCol;

    LinkedList<int[]> shortestpath;
    Queue<int[]> queue;

    public ShortestPathFinder(String mapFile) {
        readMapFile(mapFile);
        visited = new byte[numRows][numCols];
        distance = new byte[numRows][numCols];
        prevRow = new int[numRows][numCols];
        prevCol = new int[numRows][numCols];

        shortestpath = new LinkedList<>();
        queue = new LinkedList<>();
    }

    private void readMapFile(String mapFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(mapFile))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            numRows = lines.size();
            numCols = lines.get(0).split(",").length;
            map = new int[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                String[] elements = lines.get(i).split(",");
                for (int j = 0; j < numCols; j++) {
                    map[i][j] = Integer.parseInt(elements[j].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findShortestPath(int startRow, int startCol, int endRow, int endCol) {

        shortestpath.clear();
        resetState();
        bfs(startRow, startCol, endRow, endCol);

        if (visited[endRow][endCol] == 0) {
            System.out.println("No path found.");
            shortestpath = null;
        }

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
        //MapWithPath(shortestpath); // print out
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

    public int[] find_next(int startRow, int startCol, int endRow, int endCol){
        findShortestPath(startRow, startCol, endRow, endCol);
        int[] next = new int[2];
        next[0] = shortestpath.get(shortestpath.size()-2)[0];
        next[1] = shortestpath.get(shortestpath.size()-2)[1];
        return next;
    }
    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols && map[row][col] == 0 && visited[row][col] == 0;
    }

    private void updateMapWithPath(List<int[]> path) {
        for (int[] position : path) {
            int row = position[0];
            int col = position[1];
            map[row][col] = 2;
        }
    }

    private void resetState() {
        for (int i = 0; i < numRows; i++) {
            Arrays.fill(visited[i], (byte) 0);
            Arrays.fill(distance[i], (byte) 0);
            Arrays.fill(prevRow[i], 0);
            Arrays.fill(prevCol[i], 0);
        }
    }

    public void printMap() {
        for (int[] row : map) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}