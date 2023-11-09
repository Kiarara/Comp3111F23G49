package Function_C;

import java.util.ArrayList;


//Controls all the game logic .. most important class in this project.
public class ThreadsController extends Thread {
	 ArrayList<ArrayList<DataOfSquare>> Squares= new ArrayList<ArrayList<DataOfSquare>>();
	 VertexLocation tomPos;
	 VertexLocation jerryPos;
	 long tomSpeed = 50;
	 public static int directionJerry;
	 // todo: the path of Jerry

	 // todo: the format of maze TBD
	 Maze m;

	 // todo: import the shortest path
	 ShortestPath tomPath;

	 //Constructor of ControllerThread
	 ThreadsController(){
		//Get all the threads
		Squares=Window.Grid;

		// todo: create a new maze
		m = new Maze();

		tomPos = new VertexLocation(m.getExit());
		jerryPos = new VertexLocation(m.getEntry());
		directionJerry = 1;

		// todo: a shortest path
		tomPath = new ShortestPath(tomPos, jerryPos);

	 }
	 
	 //Important part: Tom updates twice faster than Jerry (runs faster than Jerry)
	 public void run() {
		 boolean onlyTom = true;
		 // todo: initialize maze
		 while(true){
			 clearObject();
			 if (!onlyTom)
			 {
				 moveJerry(directionJerry);
				 checkGameEnd();
			 }
			 moveTom(tomPath);
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
		 VertexLocation exit = m.getExit();
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
			 case 4:
				 if(jerryPos.getY()+1<20){
					 jerryPos.updateLocation(jerryPos.getX(), jerryPos.getY()+1);
				 }
				 break;
			 case 3:
				 if(jerryPos.getY()-1>=0)
					 jerryPos.updateLocation(jerryPos.getX(), jerryPos.getY()-1);
				 break;
			 case 2:
				 if(jerryPos.getX()-1>=0)
					 jerryPos.updateLocation(jerryPos.getX()-1, jerryPos.getY());
				 break;
			 case 1:
				 if(jerryPos.getX()+1<20)
					 jerryPos.updateLocation(jerryPos.getX()+1, jerryPos.getY());
				 break;
		 }
	 }

	 // todo: update Tom
	 private void moveTom(ShortestPath tomPath){

	 }

	 //Refresh the squares that needs to be updated
	// need to revert the squares where Tom and Jerry were originally located
	 private void moveExterne(){
		 // update Jerry
		 Squares.get(tomPos.getX()).get(tomPos.getY()).changeObject(1);
		 Squares.get(jerryPos.getX()).get(jerryPos.getY()).changeObject(2);
	 }

	 private void clearObject(){
		 Squares.get(jerryPos.getX()).get(jerryPos.getY()).clearObject();
		 Squares.get(tomPos.getX()).get(tomPos.getY()).clearObject();
	 }

}
