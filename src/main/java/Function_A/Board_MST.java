package Function_A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * This class is the class that makes the maze
 *
 * @author Benny (Wan Sze Chung)
 */
public class Board_MST {

    Random rand = new Random();
    public int[][] grid;
    public ArrayList<Association> association_list;
    public ArrayList<int[]> mst;

    /**
     * This is the constructor of the class,the map is 30*30,
     * nodes of the map are set up for the mst. Every node is a white square
     * and separated by a black square. The black squares between two white
     * squares are the edges between the nodes.
     */
    public Board_MST() {
        grid = new int[30][30];
        for(int i=0;i<30;i++){
            for(int j=0;j<30;j++){
                grid[i][j]=1;
            }
        }
        for(int i=0;i<14;i++){
            for(int j=0;j<14;j++){
                grid[2+2*i][2+2*j]=0;
            }
        }
        association_list = new ArrayList<>();
        mst = new ArrayList<>();
    }

    /**
     * This function expands a node, putting the possible edges to the
     * edges list
     * @param coor the coordinate of the expanding node
     */
    public void expand_coor(int[] coor){
        if(coor[0]>2){
            int[] up_coor={coor[0]-2,coor[1]};
            boolean existed = false;
            for(int []existed_coor:mst){
                if(Arrays.equals(up_coor,existed_coor)){
                    existed=true;
                    break;
                }
            }
            if (!existed){
                Association association = new Association(coor,up_coor);
                association_list.add(association);
            }
        }
        if(coor[0]<28){
            int[] down_coor={coor[0]+2,coor[1]};
            boolean existed = false;
            for(int []existed_coor:mst){
                if(Arrays.equals(down_coor,existed_coor)){
                    existed=true;
                    break;
                }
            }
            if (!existed){
                Association association = new Association(coor,down_coor);
                association_list.add(association);
            }
        }
        if(coor[1]>2){
            int[] left_coor={coor[0],coor[1]-2};
            boolean existed = false;
            for(int []existed_coor:mst){
                if(Arrays.equals(left_coor,existed_coor)){
                    existed=true;
                    break;
                }
            }
            if (!existed){
                Association association = new Association(coor,left_coor);
                association_list.add(association);
            }
        }
        if(coor[1]<28){
            int[] right_coor={coor[0],coor[1]+2};
            boolean existed = false;
            for(int []existed_coor:mst){
                if(Arrays.equals(right_coor,existed_coor)){
                    existed=true;
                    break;
                }
            }
            if (!existed){
                Association association = new Association(coor,right_coor);
                association_list.add(association);
            }
        }
    }

    /**
     * This remove the redundant edges in the edges list since after
     * a node is reached by the mst, there are still edges in the edges
     * list connecting it
     * @param coor the new expanded coordinate of the node
     */
    public void remove_redundant(int[] coor){
        for (int i=0;i<association_list.size();i++){
            int[] expected_coor= association_list.get(i).get_coor(coor);
            if(expected_coor[0] !=-1){
                association_list.remove(i);
                i--;
            }
        }
    }

    /**
     * build the mst maze
     */
    public void build_maze(){
        //start with randomly choosing a 0
        int []coor={2*rand.nextInt(14)+2,2*rand.nextInt(14)+2};
        mst.add(coor);
        do {
            //adding associationthat square into associationt
            expand_coor(coor);
            Collections.sort(association_list,new WeightComparator());
            coor = association_list.get(0).get_new_coor();
            grid[association_list.get(0).middle_vertex[0]][association_list.get(0).middle_vertex[1]] = 0;
            association_list.remove(0);
            mst.add(coor);
            remove_redundant(coor);
        }while(!association_list.isEmpty());

        int starting= 2*rand.nextInt(13)+2;
        int ending= 2*rand.nextInt(13)+2;
        grid[starting][0]=0;
        grid[starting][1]=0;
        grid[ending][29]=0;
    }

    /**
     * since the mst maze only have 1 possible path between the
     * starting and ending position, this function turns some black squares
     * into white squares to create more path
     */
    public void build_more_path(){
        boolean change=false;
        while(!change){
            int row= rand.nextInt(26)+2;
            int column;

            if (row%2==0){
                column = 2*rand.nextInt(12)+3;
                //System.out.println("row = "+row);
            }
            else{
                column = 2*rand.nextInt(13)+2;
                //System.out.println("col = "+column);
            }
            if(grid[row][column]==1){
                change=true;
                grid[row][column]=0;
            }
        }
    }

    /**
     * since the mst maze is build with walls with 2 squares thickness
     * on the top and left, this function makes the maze with walls with
     * 1 square thickness by changing some black squares into white on
     * the top and left
     */
    public void build_maze_with_single_wall(){
        build_maze();
        int change = 0;
        while(change!=8){
            int col = rand.nextInt(28)+1;
            if(grid[1][col]==1){
                grid[1][col]=0;
                change++;
            }
        }
        change = 0;
        while(change!=8){
            int row = rand.nextInt(28)+1;
            if(grid[row][1]==1){
                grid[row][1]=0;
                change++;
            }
        }
    }

    /**
     * This function saves the maze into a csv file
     */
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
        } catch (IOException e) {
            System.out.println("Error occurredduring saving the maze to a file.");
            e.printStackTrace();
        }
    }
}


