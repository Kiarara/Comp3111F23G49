import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ShortestPathFinder {
    private int[][] map;
    private int numRows;
    private int numCols;
    private boolean[][] visited;
    private int[][] distance;
    private int[][][] prev;
    private int startRow;
    private int startCol;
    private int endRow;
    private int endCol;

    public ShortestPathFinder(String mapFile) {
        readMapFile(mapFile);
        visited = new boolean[numRows][numCols];
        distance = new int[numRows][numCols];
        prev = new int[numRows][numCols][2];
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
            System.out.println("x"); //test
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<int[]> findShortestPath(int startRow, int startCol, int endRow, int endCol) {
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;

        dfs(startRow, startCol);

        if (!visited[endRow][endCol]) {
            System.out.println("No path found.");
            return null;
        }

        LinkedList<int[]> path = new LinkedList<>();
        int row = endRow;
        int col = endCol;

        while (row != startRow || col != startCol) {
            path.addFirst(new int[]{row, col});
            int prevRow = prev[row][col][0];
            int prevCol = prev[row][col][1];
            row = prevRow;
            col = prevCol;
        }

        path.addFirst(new int[]{startRow, startCol});
        return path;
    }

    private void dfs(int row, int col) {
        visited[row][col] = true;

        if (row == endRow && col == endCol) {
            return;
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (isValidMove(newRow, newCol)) {
                prev[newRow][newCol] = new int[]{row, col};
                distance[newRow][newCol] = distance[row][col] + 1;
                dfs(newRow, newCol);
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols && map[row][col] == 0 && !visited[row][col];
    }

    public static void main(String[] args) {
        String mapFile = "/Users/liumuyuan/Desktop/map.csv";
        ShortestPathFinder pathFinder = new ShortestPathFinder(mapFile);

        int startRow = 12;
        int startCol = 0;
        int endRow = 1;
        int endCol = 29;

        List<int[]> shortestPath = pathFinder.findShortestPath(startRow, startCol, endRow, endCol);

        if (shortestPath != null) {
            System.out.println("Shortest path:");
            for (int[] vertex : shortestPath) {
                System.out.println("[" + vertex[0] + ", " + vertex[1] + "]");
            }
        }
    }
}