package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
//    public static void main(String[] args) {
//        // Press Opt+Enter with your caret at the highlighted text to see how
//        // IntelliJ IDEA suggests fixing it.
//        System.out.printf("Hello and welcome!");
//
//        // Press Ctrl+R or click the green arrow button in the gutter to run the code.
//        for (int i = 1; i <= 5; i++) {
//
//            // Press Ctrl+D to start debugging your code. We have set one breakpoint
//            // for you, but you can always add more by pressing Cmd+F8.
//            System.out.printf("i = " , i);
//        }
//    }
    public static void main(String[] args) {
        System.out.println("s");
        String mapFile = "/Users/liumuyuan/Desktop/Study/7、 2023-2024 Fall Sem/COMP 3111/Group/map.csv";
        ShortestPathFinder pathFinder = new ShortestPathFinder(mapFile);

        int startRow = 0;
        int startCol = 0;
        int endRow = 29;
        int endCol = 29;
        System.out.println("s");
        List<int[]> shortestPath = pathFinder.findShortestPath(startRow, startCol, endRow, endCol);
        System.out.println("s");
        if (shortestPath != null) {
            System.out.println("Shortest path:");
            for (int[] vertex : shortestPath) {
                System.out.println("[" + vertex[0] + ", " + vertex[1] + "]");
            }
        }
    }
}

//class Paths {
//    static boolean[] print_path(int[] start, int[] end){
//        int start_x = start[0];
//        int start_y = start[1];
//        int end_x = end[0];
//        int end_y = end[1];
//
//        if ((start_x >end_x) || (start_y > end_y)){
//            System.out.printf("No possible path!");
//        }
//        System.out.printf("The path starts at [", start_x, ",", start_y, "]");
//        while (start_x < end_x){
//            start_x += 1;
//            System.out.printf("The point moves to [", start_x, ",", start_y, "]");
//        }
//        while (start_y < end_y){
//            start_y += 1;
//            System.out.printf("The point moves to [", start_x, ",", start_y, "]");
//        }
//        boolean [] result = {true, true};
//        return result;
//    }
//    static int[] calculate_distance(int[] start, int[] end) {
//        int start_x = start[0];
//        int start_y = start[1];
//        int end_x = end[0];
//        int end_y = end[1];
//        int total_distance = (end_x + end_y) - (start_x + start_y);
//        System.out.printf("The total distance is ", total_distance);
//        int [] result = {total_distance, 1};
//        return result;
//    }
//
//}

class ShortestPathFinder {
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
                    map[i][j] = Integer.parseInt(elements[j]);
                }
            }
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


}
