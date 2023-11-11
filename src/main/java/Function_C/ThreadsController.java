package Function_C;

import java.util.ArrayList;


//Controls all the game logic .. most important class in this project.
public class ThreadsController extends Thread {
	 ArrayList<ArrayList<DataOfSquare>> Squares= new ArrayList<ArrayList<DataOfSquare>>();
	 VertexLocation tomPos;
	 VertexLocation jerryPos;
	 long tomSpeed = 150;
	 public static int directionJerry;
	 // todo: the path of Jerry

	 Maze m;

	 // todo: import the shortest path
	 //ShortestPath tomPath;

	 //Constructor of ControllerThread
	 ThreadsController(){
		//Get all the threads
		Squares=Window.Grid;

		// todo: create a new maze
		m = new Maze("MazeMap_TnJ.csv");

		tomPos = m.exit;
		jerryPos = m.entry;
		directionJerry = 1;

		// todo: a shortest path
		//tomPath = new ShortestPath(tomPos, jerryPos);

	 }
	 
	 //Important part: Tom updates twice more frequent than Jerry (runs faster than Jerry)
	 public void run() {
		 boolean onlyTom = true;
		 //int clear_count = 0;

		 Squares.get(tomPos.x).get(tomPos.y).changeObject(1);
		 Squares.get(jerryPos.x).get(jerryPos.y).changeObject(2);
		 // todo: initialize maze
		 for (int i = 0; i<30; ++i){
			 for (int j = 0; j<30; ++j) {
				 if (m.maze[i][j] == 1) Squares.get(i).get(j).lightMeUp(0);
			 }
		 }


		 while(true){
			 clearObject();
			 /*
			 if (clear_count<3){
				 clear_count++;
			 }
			 else{
				 clearObject();
				 clear_count = 0;
			 }

		  */
			 if (onlyTom) {
				 onlyTom = false;
			 }
			 else
			 {
				 moveJerry(directionJerry);
				 checkGameEnd();
				 onlyTom = true;
			 }
			 //moveTom(tomPath);
			 checkGameEnd();
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
	 private void checkGameEnd() {
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
	 private void stopTheGame(){
		 while(true){
			 pauser();
		 }
	 }
	 
	 //Moves the head of the snake and refreshes the positions in the arraylist
	 //1:right 2:left 3:top 4:bottom 0:nothing
	 private void moveJerry(int dir){
		 switch(dir){
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

	 // todo: update Tom
	 //private void moveTom(ShortestPath tomPath){
	 //}

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

}
