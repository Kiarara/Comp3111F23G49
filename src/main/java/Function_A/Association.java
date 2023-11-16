package Function_A;

import java.util.Arrays;
import java.util.Random;

public class Association {
    int weight;
    int [] left_or_up_vertex;
    int [] right_or_down_vertex;
    int [] middle_vertex;
    boolean old_is_left_or_up;

    public Association(int[] first_coor, int[] second_coor){
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
