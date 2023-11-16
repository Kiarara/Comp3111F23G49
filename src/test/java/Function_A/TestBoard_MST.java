package Function_A;

import org.junit.jupiter.api.Test;

//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
public class TestBoard_MST {
    @Test
    void get_coor(){
        int [] first_coor = {0,2};
        int [] second_coor = {0,4};
        int [] non_exist_coor = {0,3};
        int [] expected = first_coor;

        Association assoiciation = new Association(first_coor,second_coor);
        int [] actual = assoiciation.get_coor(second_coor);
        assertArrayEquals(expected,actual);
        expected = second_coor;
        actual = assoiciation.get_coor(first_coor);
        assertArrayEquals(expected,actual);
        int [] null_coor = {-1,-1};
        expected = null_coor;
        actual = assoiciation.get_coor(non_exist_coor);
        assertArrayEquals(expected,actual);

    }
    @Test
    void expand_coor(){

    }


}
