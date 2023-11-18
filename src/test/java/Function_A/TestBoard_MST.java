package Function_A;

import Shared.Maze;
import org.junit.jupiter.api.Test;

//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertEquals;
//import java.util.Scanner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class TestBoard_MST {
    @Test
    void Association(){
        int [] up_coor = {10,12};
        int [] down_coor = {12,12};
        int [] middle_of_up_and_down = {11,12};
        int [] left_coor = {20,20};
        int [] right_coor = {20,22};
        int [] middle_of_left_and_right = {20,21};
        //boolean expected_bool;
        boolean actual_bool;


        Association association_1 = new Association(up_coor,down_coor);
        int [] expected = down_coor;
        int [] actual = association_1.right_or_down_vertex;
        assertArrayEquals(expected,actual);
        expected = up_coor;
        actual = association_1.left_or_up_vertex;
        assertArrayEquals(expected,actual);
        expected = middle_of_up_and_down;
        actual = association_1.middle_vertex;
        assertArrayEquals(expected,actual);
        //expected_bool = true;
        actual_bool = association_1.old_is_left_or_up;
        assertTrue(actual_bool);


        Association association_2 = new Association(down_coor,up_coor);
        expected = down_coor;
        actual = association_2.right_or_down_vertex;
        assertArrayEquals(expected,actual);
        expected = up_coor;
        actual = association_2.left_or_up_vertex;
        assertArrayEquals(expected,actual);
        expected = middle_of_up_and_down;
        actual = association_2.middle_vertex;
        assertArrayEquals(expected,actual);
        //expected_bool = true;
        actual_bool = association_2.old_is_left_or_up;
        assertFalse(actual_bool);

        Association association_3 = new Association(right_coor,left_coor);
        expected = right_coor;
        actual = association_3.right_or_down_vertex;
        assertArrayEquals(expected,actual);
        expected = left_coor;
        actual = association_3.left_or_up_vertex;
        assertArrayEquals(expected,actual);
        expected = middle_of_left_and_right;
        actual = association_3.middle_vertex;
        assertArrayEquals(expected,actual);
        //expected_bool = true;
        actual_bool = association_3.old_is_left_or_up;
        assertFalse(actual_bool);

        Association association_4 = new Association(left_coor,right_coor);
        expected = right_coor;
        actual = association_4.right_or_down_vertex;
        assertArrayEquals(expected,actual);
        expected = left_coor;
        actual = association_4.left_or_up_vertex;
        assertArrayEquals(expected,actual);
        expected = middle_of_left_and_right;
        actual = association_4.middle_vertex;
        assertArrayEquals(expected,actual);
        //expected_bool = true;
        actual_bool = association_4.old_is_left_or_up;
        assertTrue(actual_bool);
    }
    @Test
    void get_coor(){
        int [] first_coor = {2,2};
        int [] second_coor = {2,4};
        int [] non_exist_coor = {2,3};
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
    void get_new_coor(){
        int [] left_coor = {16,18};
        int [] right_coor = {16,20};

        Association assoiciation_1 = new Association(left_coor,right_coor);
        int [] expected = right_coor;
        int [] actual = assoiciation_1.get_new_coor();
        assertArrayEquals(expected,actual);

        Association association_2 = new Association(right_coor,left_coor);
        expected = left_coor;
        actual = association_2.get_new_coor();
        assertArrayEquals(expected,actual);
    }
    @Test
    void compare(){
        int [] dummy_1 = {6,8};
        int [] dummy_2 = {6,10};
        int [] dummy_3 = {16,20};
        int [] dummy_4 = {14,20};
        Association association_1 = new Association(dummy_1,dummy_2);
        Association association_2 = new Association(dummy_3,dummy_4);
        association_1.weight = 20;
        association_2.weight = 10;
        int expected;
        int actual;

        WeightComparator weightComparator = new WeightComparator();
        expected = 1;
        actual = weightComparator.compare(association_1,association_2);
        assertEquals(expected,actual);

        expected = -1;
        actual = weightComparator.compare(association_2,association_1);
        assertEquals(expected,actual);

        association_1.weight = 15;
        association_2.weight = 15;
        expected = 0;
        actual = weightComparator.compare(association_1,association_2);
        assertEquals(expected,actual);

    }
    @Test
    void Board_MST(){
        Board_MST board_mst = new Board_MST();
        int [] all_one = new int[30];
        for (int i=0;i<30;i++){
            all_one[i]=1;
        }
        int [] one_and_zero = new int [30];
        for (int i=0;i<30;i++){
            if (i%2==0 && i>0){
                one_and_zero[i]=0;
            }
            else{
                one_and_zero[i]=1;
            }
        }

        for(int i=0;i<30;i++){
            if(i%2==0 && i>0){
                assertArrayEquals(board_mst.grid[i],one_and_zero);
            }
            else{
                assertArrayEquals(board_mst.grid[i],all_one);
            }
        }
        assertTrue(board_mst.association_list.isEmpty());
        assertTrue(board_mst.mst.isEmpty());
    }
    @Test
    void expand_coor(){
        Board_MST board_mst = new Board_MST();
        int [] coor_0 = {10,12};
        int [] expected_up_coor_0 = {8,12};
        int [] expected_down_coor_0 = {12,12};
        int [] expected_left_coor_0 = {10,10};
        int [] expected_right_coor_0 = {10,14};
        int [] existed_coor = {4,4};
        board_mst.mst.add(existed_coor);
        board_mst.expand_coor(coor_0);
        Association[] list = new Association[board_mst.association_list.size()];
        board_mst.association_list.toArray(list);
        assertArrayEquals(list[0].left_or_up_vertex,expected_up_coor_0);
        assertArrayEquals(list[0].right_or_down_vertex,coor_0);

        assertArrayEquals(list[1].right_or_down_vertex,expected_down_coor_0);
        assertArrayEquals(list[1].left_or_up_vertex,coor_0);

        assertArrayEquals(list[2].left_or_up_vertex,expected_left_coor_0);
        assertArrayEquals(list[2].right_or_down_vertex,coor_0);

        assertArrayEquals(list[3].right_or_down_vertex,expected_right_coor_0);
        assertArrayEquals(list[3].left_or_up_vertex,coor_0);

        board_mst.association_list.clear();
        board_mst.mst.clear();

        int [] coor_1 = {6,6};
        int [] expected_up_coor_1 = {4,6};
        int [] expected_down_coor_1 = {8,6};
        int [] expected_left_coor_1 = {6,4};
        int [] expected_right_coor_1 = {6,8};
        board_mst.mst.add(expected_up_coor_1);
        board_mst.mst.add(expected_down_coor_1);
        board_mst.mst.add(expected_left_coor_1);
        board_mst.mst.add(expected_right_coor_1);

        board_mst.expand_coor(coor_1);
        assertTrue(board_mst.association_list.isEmpty());
    }

    @Test
    void remove_redundant(){
        Board_MST board_mst = new Board_MST();
        int [] coor_0 = {10,12};
        int [] expected_up_coor_0 = {8,12};
        int [] expected_down_coor_0 = {12,12};
        int [] expected_left_coor_0 = {10,10};
        //int [] expected_right_coor_0 = {10,14};
        Association association_up = new Association(coor_0,expected_up_coor_0);
        Association association_down = new Association(coor_0,expected_down_coor_0);
        Association association_left = new Association(coor_0,expected_left_coor_0);

        board_mst.association_list.add(association_up);
        board_mst.association_list.add(association_down);
        board_mst.association_list.add(association_left);

        board_mst.mst.add(expected_up_coor_0);
        board_mst.mst.add(expected_down_coor_0);
        board_mst.mst.add(expected_left_coor_0);
        board_mst.mst.add(coor_0);

        board_mst.remove_redundant(coor_0);
        assertTrue(board_mst.association_list.isEmpty());
    }

    @Test
    void build_maze(){
        Board_MST board_mst = new Board_MST();
        board_mst.build_maze();
        //check if the first two rows and the last row are all 1
        for(int i=0;i<30;i++){
            assertEquals(board_mst.grid[0][i],1);
            assertEquals(board_mst.grid[1][i],1);
            assertEquals(board_mst.grid[29][i],1);
        }

        //check the first two column
        int count = 0;
        int starting_0 = -1;
        int starting_1 = -2;
        for(int i=0;i<30;i++){
            if(board_mst.grid[i][0]==0){
                count++;
                starting_0 = i;
            }
        }
        assertEquals(count,1);
        count = 0;
        for(int i=0;i<30;i++){
            if(board_mst.grid[i][1]==0){
                count++;
                starting_1 = i;
            }
        }
        assertEquals(count,1);
        assertEquals(starting_0,starting_1);
        assertEquals(starting_0%2,0);
        //checking the last column
        count = 0;
        int ending = -1;
        for (int i=0;i<30;i++){
            if(board_mst.grid[i][29]==0){
                count++;
                ending = i;
            }
        }
        assertEquals(count,1);
        assertEquals(ending%2,0);
        //making the starting and ending becomes 1 also
        board_mst.grid[starting_0][0]=1;
        board_mst.grid[starting_0][1]=1;
        board_mst.grid[ending][29]=1;

        //check the maze inside
        //check if every node is in the mst
        assertEquals(board_mst.mst.size(),14*14);
        //count the number of edges
        count = 0;
        for(int i=2;i<29;i++){
            for(int j=2;j<29;j++){
                if(i%2==0 & j%2==1){
                    if(board_mst.grid[i][j]==0){
                        count++;
                    }
                }
                else if(i%2==1){
                    if(board_mst.grid[i][j]==0){
                        count++;
                    }
                }
            }
        }
        assertEquals(14*14-1,count);
    }

    @Test
    void build_more_path(){
        Board_MST board_mst = new Board_MST();
        board_mst.build_maze();
        board_mst.build_more_path();
        //count the number of edges
        int count = 0;
        for(int i=2;i<29;i++){
            for(int j=2;j<29;j++){
                if(i%2==0 & j%2==1){
                    if(board_mst.grid[i][j]==0){
                        count++;
                    }
                }
                else if(i%2==1){
                    if(board_mst.grid[i][j]==0){
                        count++;
                    }
                }
            }
        }
        assertEquals(14*14,count);

        board_mst.build_more_path();
        count = 0;
        for(int i=2;i<29;i++){
            for(int j=2;j<29;j++){
                if(i%2==0 & j%2==1){
                    if(board_mst.grid[i][j]==0){
                        count++;
                    }
                }
                else if(i%2==1){
                    if(board_mst.grid[i][j]==0){
                        count++;
                    }
                }
            }
        }
        assertEquals(14*14+1,count);

        board_mst.build_more_path();
        count = 0;
        for(int i=2;i<29;i++){
            for(int j=2;j<29;j++){
                if(i%2==0 & j%2==1){
                    if(board_mst.grid[i][j]==0){
                        count++;
                    }
                }
                else if(i%2==1){
                    if(board_mst.grid[i][j]==0){
                        count++;
                    }
                }
            }
        }
        assertEquals(14*14+2,count);
        board_mst.build_more_path();
        count = 0;
        for(int i=2;i<29;i++){
            for(int j=2;j<29;j++){
                if(i%2==0 & j%2==1){
                    if(board_mst.grid[i][j]==0){
                        count++;
                    }
                }
                else if(i%2==1){
                    if(board_mst.grid[i][j]==0){
                        count++;
                    }
                }
            }
        }
        assertEquals(14*14+3,count);
    }
    @Test
    void wall_to_maze(){
        Board_MST board_mst = new Board_MST();
        //count number of white
        int count = 0;
        for(int i=2;i<29;i++){
            if(board_mst.grid[1][i]==0){
                count++;
            }
            if(board_mst.grid[i][1]==0){
                count++;
            }
        }
        if(board_mst.grid[1][1]==0){
            count++;
        }
        assertEquals(count,0);

        board_mst.wall_to_maze();
        //count number of white
        count = 0;
        for(int i=2;i<29;i++){
            if(board_mst.grid[1][i]==0){
                count++;
            }
            if(board_mst.grid[i][1]==0){
                count++;
            }
        }
        if(board_mst.grid[1][1]==0){
            count++;
        }
        assertEquals(count,16);
    }
    @Test
    void saveMazeToFile() throws IOException {
        Board_MST board_mst = new Board_MST();
        board_mst.build_maze();
        board_mst.saveMazeToFile();
        Maze maze = new Maze("actual_maze.csv");
        //for(int i=0;i<30;i++){
        assertArrayEquals(maze.maze, board_mst.grid);
        //}
    }
    @Test
    void main_a() throws IOException {
        Main_A.main(null);
        Maze maze = new Maze("actual_maze.csv");
        int count = 0;
        for(int i=2;i<29;i++){
            for(int j=2;j<29;j++){
                if(i%2==0 & j%2==1){
                    if(maze.maze[i][j]==0){
                        count++;
                    }
                }
                else if(i%2==1){
                    if(maze.maze[i][j]==0){
                        count++;
                    }
                }
            }
        }
        assertEquals(14*14+19,count);
    }
}
