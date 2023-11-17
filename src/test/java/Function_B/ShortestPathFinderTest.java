package Function_B;

import Shared.Maze;
import Shared.Window;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ShortestPathFinderTest {

    @Test
    void findShortestPath() {
        // test if the shortest path is the shortest one, compared with the map in the requirement file
        Window f1 = new Window(false);
        f1.set_maze("MazeMap_TnJ.csv");
        Maze m1 = f1.getMaze();
        ShortestPathFinder finder = new ShortestPathFinder(m1);
        finder.findShortestPath(m1.getEntry(), m1.getExit());
        int output_length = finder.shortestpath.size();
        int true_length = 49; // according to the requirement file
        assertEquals(output_length, true_length);

        // test if can function normally when there is no path
        f1.set_maze("Impossible_map.csv");
        m1 = f1.getMaze();
        finder = new ShortestPathFinder(m1);
        finder.findShortestPath(m1.getEntry(), m1.getExit());
        assertNull(finder.shortestpath);
    }

}
/*
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
}
/*
    public int[] find_next(VertexLocation a, VertexLocation b){
        findShortestPath(a, b);
        int[] next = new int[2];
        next[0] = shortestpath.get(shortestpath.size()-2)[0];
        next[1] = shortestpath.get(shortestpath.size()-2)[1];
        return next;
    }


    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols && map[row][col] == 0 && visited[row][col] == 0;
    }

    private void resetState() {
        for (int i = 0; i < numRows; i++) {
            Arrays.fill(visited[i], (byte) 0);
            Arrays.fill(distance[i], (byte) 0);
            Arrays.fill(prevRow[i], 0);
            Arrays.fill(prevCol[i], 0);
        }
    }

    public void displayPath(Window w) {
        ArrayList<ArrayList<DataOfSquare>> Grid = w.Grid;
        shortestpath.forEach(element -> Grid.get(element[0]).get(element[1]).lightMeUp(2));
    }
*/