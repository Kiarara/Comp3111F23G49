
package Function_C;

import java.util.ArrayList;
import java.util.List;
import board.board_mst;


//Controls all the game logic .. most important class in this project.
public class ThreadsController extends Thread {
	 ArrayList<ArrayList<DataOfSquare>> Squares= new ArrayList<ArrayList<DataOfSquare>>();
	 VertexLocation tomPos;
	 VertexLocation jerryPos;
	 long tomSpeed = 200;
	 public static int directionJerry;

	 Maze m;
	 ShortestPathFinder finder;
	 List<int[]> tomPath;

	 public boolean running;

	 //Constructor of ControllerThread
	 ThreadsController(){
		//Get all the threads
		Squares=Window.Grid;

		board_mst Board = new board_mst();
		Board.build_maze();
		Board.saveMazeToFile();

		String map_file = "actual_maze.csv";
		m = new Maze(map_file);

		tomPos = new VertexLocation(m.exit.x, m.exit.y);
		jerryPos = new VertexLocation(m.entry.x, m.entry.y);
		directionJerry = 1;

		finder = new ShortestPathFinder(map_file);
		//tomPath = finder.findShortestPath(jerryPos.x,jerryPos.y, tomPos.x, tomPos.y);
		//tom_pathloc = tomPath.size()-1;
	 }

	//Important part: Tom updates twice more frequent than Jerry (runs faster than Jerry)
	 public void run() {
		 boolean onlyTom = true;
		 //int clear_count = 0;
		 running = true;

		 Squares.get(tomPos.x).get(tomPos.y).changeObject(1);
		 Squares.get(jerryPos.x).get(jerryPos.y).changeObject(2);

		 for (int i = 0; i<30; ++i){
			 for (int j = 0; j<30; ++j) {
				 if (m.maze[i][j] == 1) Squares.get(i).get(j).lightMeUp(0);
			 }
		 }

		 while(running){
			 clearObject();
			 if (onlyTom) {
				 onlyTom = false;
			 }
			 else
			 {
				 moveJerry();
				 try {
					 checkGameEnd();
				 } catch (InterruptedException e) {
					 throw new RuntimeException(e);
				 }
				 onlyTom = true;
			 }
			 try {
				 moveTom();
			 } catch (InterruptedException e) {
				 throw new RuntimeException(e);
			 }
			 try {
				 checkGameEnd();
			 } catch (InterruptedException e) {
				 throw new RuntimeException(e);
			 }
			 moveExterne();
			 pauser();
		 }
	 }
	 
	 //delay between each move of the snake
	 private void pauser(){
		 try {
			 sleep(tomSpeed);
		 } catch (InterruptedException e) {
				e.printStackTrace();
		 }
	 }
	 
	 //Checking if the Jerry get caught or Jerry reaches the exit point
	 private void checkGameEnd() throws InterruptedException {
		 VertexLocation exit = m.exit;
		 boolean gameWin = exit.isSame(jerryPos);
		 if(gameWin) {
			 System.out.println("Congratulations!");
			 stopTheGame();
			 return;
		 }

		 boolean getCaught = jerryPos.isSame(tomPos);
		 if(getCaught){
			 System.out.println("GET CAUGHT!");
			 stopTheGame();
		 }
	 }
	 
	 //Stops The Game
	 private void stopTheGame() throws InterruptedException {
		 //this.join();
		 running = false;
		 //this.interrupt();
	 }
	 
	 //Moves the head of the snake and refreshes the positions in the arraylist
	 //1:right 2:left 3:top 4:bottom 0:nothing
	 private void moveJerry(){
		 switch(directionJerry){
			 case 1:
				 if(jerryPos.y+1<30 && m.maze[jerryPos.x][jerryPos.y+1] == 0){
					 jerryPos.updateLocation(jerryPos.x, jerryPos.y+1);
				 }
				 break;
			 case 2:
				 if(jerryPos.y-1>=0 && m.maze[jerryPos.x][jerryPos.y-1] == 0)
					 jerryPos.updateLocation(jerryPos.x, jerryPos.y-1);
				 break;
			 case 3:
				 if(jerryPos.x-1>=0 && m.maze[jerryPos.x-1][jerryPos.y] == 0)
					 jerryPos.updateLocation(jerryPos.x-1, jerryPos.y);
				 break;
			 case 4:
				 if(jerryPos.x+1<30 && m.maze[jerryPos.x+1][jerryPos.y] == 0){
					 jerryPos.updateLocation(jerryPos.x+1, jerryPos.y);
				 }
				 break;
		 }
	 }

	 private void moveTom() throws InterruptedException {
		 //debug:
		 System.out.println("calling find_next");
		 int[] next = finder.find_next(jerryPos.x, jerryPos.y, tomPos.x, tomPos.y);
		 //sleep(500);
		 tomPos.updateLocation(next[0], next[1]);
	 }

	 private boolean pathToUpdate(){
		 for (int i = 0; i< tomPath.size(); ++i)
		 {
			 if (jerryPos.x == tomPath.get(i)[0] && jerryPos.y == tomPath.get(i)[1])
				 return false;
		 }
		 return true;
	 }

	 //Refresh the squares that needs to be updated
	// need to revert the squares where Tom and Jerry were originally located
	 private void moveExterne(){
		 // update Jerry
		 Squares.get(tomPos.x).get(tomPos.y).changeObject(1);
		 Squares.get(jerryPos.x).get(jerryPos.y).changeObject(2);
	 }

	 private void clearObject(){
		 Squares.get(jerryPos.getX()).get(jerryPos.getY()).clearObject();
		 Squares.get(tomPos.getX()).get(tomPos.getY()).clearObject();
	 }

	 // for debug: display the initial shortest path
	 private void displayTomPath() {
		 for (int i = 0; i < tomPath.size(); i++)
			 Squares.get(tomPath.get(i)[0]).get(tomPath.get(i)[1]).lightMeUp(1);
	 }

}
