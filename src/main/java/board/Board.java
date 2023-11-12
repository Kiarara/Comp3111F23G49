package board;
public class Board {
    //data containing grid to 30*30
    private int[][] grid;
    //initializing the board with all zeros
    public Board() {
        grid = new int[30][30];
        for(int i=0;i<30;i++){
            for(int j=0;j<30;j++){
                grid[i][j]=0;
            }
        }
    }

    // Getter for accessing the grid
    public int GetCellValue(int row_num, int col_num) {
        return grid[row_num-1][col_num-1];
    }

    //change the value in the cell, the value should be either 0 or 1
    public void ChangeCellValue(int row_num, int col_num){
        if(grid[row_num-1][col_num-1] == 0){
            grid[row_num-1][col_num-1] = 1;
        }
        else{
            grid[row_num-1][col_num-1] = 0;
        }
    }

    public void PrintBoard(){
        for(int i=0;i<30;i++){
            System.out.print("[");
            for(int j=0;j<30;j++){
                System.out.print(grid[i][j]+",");
            }
            System.out.print("],"+"\n");
        }
    }
}
