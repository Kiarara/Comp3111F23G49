package board;

import Function_A.Association;
import org.junit.jupiter.api.Test;

//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
public class Board_MST_Test {
    @Test
    void get_coor(){
        int [] first_coor = {0,2};
        int [] second_coor = {0,4};
        int [] expected = first_coor;

        Association assoiciation = new Association(first_coor,second_coor);
        int [] actual = assoiciation.get_coor(second_coor);
        assertArrayEquals(expected,actual);
        expected = second_coor;
        actual = assoiciation.get_coor(first_coor);
        assertArrayEquals(expected,actual);



    }
    @Test
    void expand_coor(){

    }


}
