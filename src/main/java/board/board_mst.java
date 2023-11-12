package board;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class board_mst {
    Random rand = new Random();
    private int[][] grid;
    private ArrayList<edge> edge_list;
    private ArrayList<int[]> mst;

    //initializing the board with all zeros
    private board_mst() {
        grid = new int[30][30];
        for(int i=0;i<30;i++){
            for(int j=0;j<30;j++){
                grid[i][j]=1;
            }
        }
        for(int i=0;i<15;i++){
            for(int j=0;j<14;j++){
                grid[2*i][2+2*j]=0;
            }
        }
        edge_list = new ArrayList<edge>();
        mst = new ArrayList<int[]>();
    }
    public void expand_coor(int[] coor){
        if(coor[0]>0){
            int[] up_coor={coor[0]-2,coor[1]};
            boolean existed = false;
            for(int []existed_coor:mst){
                if(up_coor==existed_coor){
                    existed=true;
                    break;
                }
            }
            if (!existed){
                edge new_edge = new edge(coor,up_coor);
                edge_list.add(new_edge);
            }
        }
        if(coor[0]<28){
            int[] down_coor={coor[0]+2,coor[1]};
            boolean existed = false;
            for(int []existed_coor:mst){
                if(down_coor==existed_coor){
                    existed=true;
                    break;
                }
            }
            if (!existed){
                edge_list.add(new edge(coor,down_coor));
            }
        }
        if(coor[1]>2){
            int[] left_coor={coor[0],coor[1]-2};
            boolean existed = false;
            for(int []existed_coor:mst){
                if(left_coor==existed_coor){
                    existed=true;
                    break;
                }
            }
            if (!existed){
                edge_list.add(new edge(coor,left_coor));
            }
        }
        if(coor[1]<28){
            int[] right_coor={coor[0],coor[1]+2};
            boolean existed = false;
            for(int []existed_coor:mst){
                if(right_coor==existed_coor){
                    existed=true;
                    break;
                }
            }
            if (!existed){
                edge_list.add(new edge(coor,right_coor));
            }
        }
    }
    public void remove_redundant(int[] coor){
        for (edge edge:edge_list){
            int[] expected_coor=edge.get_coor(coor);
            if(expected_coor[0]==-1){
                continue;
            }
            else{
                for(int[] existed_coor:mst){
                    if(expected_coor==existed_coor){
                        edge_list.remove(edge);
                    }
                }
            }
        }
    }
    public void build_maze(){
        //start with randomly choosing a 0
        int []coor={2*rand.nextInt(15),2*rand.nextInt(14)+2};
        mst.add(coor);
        while(mst.size()!=210) {
            //adding edges of that square into edges list
            expand_coor(coor);
            Collections.sort(edge_list, new WeightComparator());
            coor = edge_list.get(0).get_new_coor();
            grid[edge_list.get(0).middle_vertex[0]][edge_list.get(0).middle_vertex[1]] = 0;
            edge_list.remove(0);
            mst.add(coor);
            remove_redundant(coor);
        }
    }
    public void print(){
        for(int i=0;i<30;i++){
            for(int j=0;j<30;j++){
                System.out.print(grid[i][j]);
                System.out.print(" ");
            }
            System.out.print('\n');
        }
    }

    public void saveMazeToFile() {
        try {
            FileWriter writer = new FileWriter("actual_maze.csv");

            for (int row = 0; row < 30; row++) {
                for (int col = 0; col < 30; col++) {
                    writer.append(String.valueOf(grid[row][col]));

                    if (col < 30 - 1) {
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
        int []a = {1,3};
        int []b = {1,5};
        edge hi = new edge(a,b);
        board_mst Board = new board_mst();
        Board.build_maze();
        Board.print();
        Board.saveMazeToFile();
    }
}


