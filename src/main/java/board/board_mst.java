package board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
class association{

    int weight;
    int [] left_or_up_vertex;
    int [] right_or_down_vertex;
    int [] middle_vertex;
    boolean old_is_left_or_up;
    /*
    public association(int[] first_coor, int[] second_coor){
        right_or_down_vertex = first_coor;
        left_or_up_vertex = second_coor;
        Random rand = new Random();
        weight = rand.nextInt(30);
        middle_vertex = new int[2];
    }

     */

    public association(int[] first_coor, int[] second_coor){
        right_or_down_vertex = first_coor;
        left_or_up_vertex = second_coor;

        Random rand = new Random();
        weight = rand.nextInt(30);
        middle_vertex = new int[2];
        if(first_coor[0]>second_coor[0]){
            right_or_down_vertex = first_coor;
            left_or_up_vertex = second_coor;
            middle_vertex[0] = first_coor[0]-1;
            middle_vertex[1] = first_coor[1];
            old_is_left_or_up = false;
        }
        else if (second_coor[0]>first_coor[0]) {
            right_or_down_vertex = second_coor;
            left_or_up_vertex = first_coor;
            middle_vertex[0] = first_coor[0]+1;
            middle_vertex[1] = first_coor[1];
            old_is_left_or_up = true;
        }
        else if (first_coor[1]>second_coor[1]){
            right_or_down_vertex = first_coor;
            left_or_up_vertex =second_coor;
            middle_vertex[0] = first_coor[0];
            middle_vertex[1] = first_coor[1]-1;
            old_is_left_or_up = false;
        }
        else{
            right_or_down_vertex = second_coor;
            left_or_up_vertex = first_coor;
            middle_vertex[0] = first_coor[0];
            middle_vertex[1] = first_coor[1]+1;
            old_is_left_or_up = true;
        }
    }



    public int[] get_coor(int[] other_coor){
        if(Arrays.equals(other_coor,left_or_up_vertex)){
            return right_or_down_vertex;
        }
        else if(Arrays.equals(other_coor,left_or_up_vertex)) {
            return left_or_up_vertex;
        }
        else {
            int null_coor[] = {-1,-1};
            return null_coor;
        }
    }
    public int[] get_new_coor(){
        if(old_is_left_or_up){
            return right_or_down_vertex;
        }
        else return left_or_up_vertex;
    }


}

class WeightComparator implements Comparator<association> {
    public int compare(association a, association b) {
        if(a.weight>b.weight) {
            return 1;
        }
        else if(a.weight==b.weight){
            return 0;
        }
        else{
            return -1;
        }
    }
}


public class board_mst {

    Random rand = new Random();
    private int[][] grid;
    private ArrayList<association> association_list;
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
        association_list = new ArrayList<association>();
        mst = new ArrayList<int[]>();
    }
    public void expand_coor(int[] coor){
        if(coor[0]>0){
            int[] up_coor={coor[0]-2,coor[1]};
            boolean existed = false;
            for(int []existed_coor:mst){
                if(Arrays.equals(up_coor,existed_coor)){
                    existed=true;
                    break;
                }
            }
            if (!existed){
                association Association = new association(coor,up_coor);
                association_list.add(Association);
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
                association Association = new association(coor,down_coor);
                association_list.add(Association);
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
                association Association = new association(coor,left_coor);
                association_list.add(Association);
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
                association Association = new association(coor,right_coor);
                association_list.add(Association);
            }
        }
    }
    public void remove_redundant(int[] coor){
        //ArrayList<Integer> redundant_index = new ArrayList<Integer>();
        //int index=0;
        //int num_of_associations = association_list.size();
        for (int i=0;i<association_list.size();i++){
            int[] expected_coor= association_list.get(i).get_coor(coor);
            if(expected_coor[0]==-1){
                continue;
            }
            else{
                for(int[] existed_coor:mst){
                    if(Arrays.equals(expected_coor,existed_coor)){
                        //redundant_index.add(index);
                        association_list.remove(i);
                        i--;
                    }
                }
            }
            //index++;
        }
    }
    public void build_maze(){
        //start with randomly choosing a 0
        int []coor={2*rand.nextInt(15),2*rand.nextInt(14)+2};
        mst.add(coor);
        do {
            //adding associationthat square into associationt
            expand_coor(coor);
            Collections.sort(association_list, new WeightComparator());
            coor = association_list.get(0).get_new_coor();
            grid[association_list.get(0).middle_vertex[0]][association_list.get(0).middle_vertex[1]] = 0;
            association_list.remove(0);
            mst.add(coor);
            remove_redundant(coor);
        }while(!association_list.isEmpty());

        int starting= 2*rand.nextInt(15);
        int ending= 2*rand.nextInt(15);
        grid[starting][0]=0;
        grid[starting][1]=0;
        grid[ending][29]=0;
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
        board_mst Board = new board_mst();
        Board.build_maze();
        Board.print();
        Board.saveMazeToFile();
    }
}


