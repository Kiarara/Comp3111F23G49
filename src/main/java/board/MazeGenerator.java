package board;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
class Edge implements Comparable<Edge> {
    int src;
    int dest;
    int weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class MazeGenerator {
    private static final int SIZE = 30;
    private static final int[][] grid = new int[SIZE][SIZE];

    private static int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    private static void union(int[] parent, int[] rank, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);

        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }

    private static List<Edge> generateEdges() {
        List<Edge> edges = new ArrayList<>();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int curr = row * SIZE + col;

                if (col < SIZE - 1) {
                    int right = curr + 1;
                    edges.add(new Edge(curr, right, (int) (Math.random() * 100)));
                }

                if (row < SIZE - 1) {
                    int down = curr + SIZE;
                    edges.add(new Edge(curr, down, (int) (Math.random() * 100)));
                }
            }
        }

        return edges;
    }

    private static List<Edge> generateMST(List<Edge> edges) {
        List<Edge> mst = new ArrayList<>();
        Collections.sort(edges);

        int[] parent = new int[SIZE * SIZE];
        int[] rank = new int[SIZE * SIZE];
        for (int i = 0; i < SIZE * SIZE; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int numEdges = 0;
        int index = 0;

        while (numEdges < SIZE * SIZE - 1) {
            Edge edge = edges.get(index);
            int src = edge.src;
            int dest = edge.dest;

            int srcRoot = find(parent, src);
            int destRoot = find(parent, dest);

            if (srcRoot != destRoot) {
                union(parent, rank, srcRoot, destRoot);
                mst.add(edge);
                numEdges++;
            }

            index++;
        }

        return mst;
    }

    public static void generateMaze() {
        List<Edge> edges = generateEdges();
        List<Edge> mst = generateMST(edges);

        for (int row = 0; row < SIZE; row++) {
            Arrays.fill(grid[row], 1);
        }

        for (Edge edge : mst) {
            int src = edge.src;
            int dest = edge.dest;

            int srcRow = src / SIZE;
            int srcCol = src % SIZE;
            int destRow = dest / SIZE;
            int destCol = dest % SIZE;

            if (srcCol < destCol) {
                grid[srcRow][srcCol + 1] = 0;
            } else if (srcCol > destCol) {
                grid[srcRow][srcCol - 1] = 0;
            } else if (srcRow < destRow) {
                grid[srcRow + 1][srcCol] = 0;
            } else if (srcRow > destRow) {
                grid[srcRow - 1][srcCol] = 0;
            }
        }
    }

    public static void printMaze() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void saveMazeToFile() {
        try {
            FileWriter writer = new FileWriter("actual_maze.csv");

            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    writer.append(String.valueOf(grid[row][col]));

                    if (col < SIZE - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }

            writer.flush();
            writer.close();
            System.out.println("Maze saved to 'actual_maze.csv'.");
        } catch (IOException e) {
            System.out.println("Error occurredduring saving the maze to a file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generateMaze();

        // Set random starting and ending points
        int startRow = (int) (Math.random() * SIZE);
        int endRow = (int) (Math.random() * SIZE);
        grid[startRow][0] = 0;
        grid[endRow][SIZE - 1] = 0;

        saveMazeToFile();

        printMaze();
    }
}
