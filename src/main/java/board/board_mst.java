package board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
class edge{
    Random rand = new Random();
    int weight;
    int [] left_or_up_vertex;
    int [] right_or_down_vertex;
    int [] middle_vertex;
    boolean old_is_left_or_up=true;
    public edge(int[] first_coor, int[] second_coor){
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
        if(other_coor==left_or_up_vertex){
            return right_or_down_vertex;
        }
        else if(other_coor==right_or_down_vertex) {
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

class WeightComparator implements Comparator<edge> {
    public int compare(edge a, edge b) {
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
        for (edge Edge:edge_list){
            int[] expected_coor=Edge.get_coor(coor);
            if(expected_coor[0]==-1){
                continue;
            }
            else{
                for(int[] existed_coor:mst){
                    if(expected_coor==existed_coor){
                        edge_list.remove(Edge);
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


    public static void main(String[] args) {
        int []a = {1,3};
        int []b = {1,5};
        edge hi = new edge(a,b);
        board_mst Board = new board_mst();
        Board.build_maze();
        Board.print();
    }
}


