package Function_A;

import java.util.Arrays;
import java.util.Random;

/**
 *This class represents the association, meaning the edge between
 * two nodes of the MST.
 *
 * @author Benny (Wan Sze Chung)
 */
public class Association {
    /**
     * It is the weight of the edges
     */
    public int weight;
    /**
     * It is the left or up vertex coordination, which is a node
     */
    public int [] left_or_up_vertex;
    /**
     * It is the right or down vertex coordination, which is a node
     */
    public int [] right_or_down_vertex;
    /**
     * It is the coordination of the edge between two nodes
     */
    public int [] middle_vertex;
    /**
     * It stands for if the first coordinate of the constructor is
     * the left or up vertex
     */
    public boolean old_is_left_or_up;

    /**
     * This is the constructor of Association class
     * @param first_coor This is the first coordinate of one node
     * @param second_coor This is the coorinate of the second node
     */
    public Association(int[] first_coor, int[] second_coor){
        //right_or_down_vertex = first_coor;
        //left_or_up_vertex = second_coor;

        Random rand = new Random();
        weight = rand.nextInt(30);
        middle_vertex = new int[2];
        //first_coor is the down coor
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

    /**
     * This function receives a coordinate as an input, if the association
     * object has that input coordinate, it will return the other coordinate
     * of that association object. Else if the association object does not
     * have that input coordinate, it will return {-1,-1}, a null coordinate
     *
     * @param other_coor a coordinate of the nodes in the association
     * @return the other coordinate of the association object if the input
     * is one of the coordinate of the noes of the association object, else
     * return {-1,-1}, a null coordinate
     */
    public int[] get_coor(int[] other_coor){
        if(Arrays.equals(other_coor,right_or_down_vertex)){
            return left_or_up_vertex;
        }
        else if(Arrays.equals(other_coor,left_or_up_vertex)) {
            return right_or_down_vertex;
        }
        else {
            int [] null_coor = {-1,-1};
            return null_coor;
        }
    }

    /**
     * This function can help know the direction of the mst is growing
     * @return the newer coordinate of the nodes of the
     * association object, meaning the second coordinate in the constructor
     */
    public int[] get_new_coor(){
        if(old_is_left_or_up){
            return right_or_down_vertex;
        }
        else return left_or_up_vertex;
    }
}
