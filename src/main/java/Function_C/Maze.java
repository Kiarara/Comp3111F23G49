package Function_C;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    VertexLocation entry;
    VertexLocation exit;

    int[][] maze = new int[30][30];


    public Maze(String csv_file) {
        String delimiter = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csv_file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                for (int j = 0; j < values.length; j++) {
                    maze[i][j] = Integer.parseInt(values[j]);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // find entry and exit
        for (int i = 0; i < 30; i++){
            for (int j = 0; j <30; j++){
                if (j == 0 && maze[i][j] == 0) entry = new VertexLocation(i, j);
                if (j == 29 && maze[i][j] == 0) exit = new VertexLocation(i,j);
            }
        }
    }

}
