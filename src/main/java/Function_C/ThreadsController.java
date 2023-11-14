
package Function_C;

import java.util.ArrayList;
import board.board_mst;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


//Controls all the game logic --> most important class in this project.
public class ThreadsController extends Thread {
	 ArrayList<ArrayList<DataOfSquare>> Squares;
	 private JFrame parent_window;
	 VertexLocation tomPos;
	 VertexLocation jerryPos;
	 long tomSpeed = 200;
	 public static int directionJerry;

	 Maze m;
	 ShortestPathFinder finder;


	 //List<int[]> tomPath;

	 public boolean running=false;

	 //Constructor of ControllerThread
	 ThreadsController(JFrame this_window){
		//Get all the threads
		Squares=Window.Grid;
		parent_window = this_window;
	 }

	//Important part: Tom updates twice more frequent than Jerry (runs faster than Jerry)
	 public void run() {
		 int jerry_move = 0;
		 boolean onlyTom;
		 running = true;

		 game_initialize();

		 while(running){
			 if(jerry_move < 3)
			 {
				 onlyTom = false;
				 jerry_move ++;
			 }
			 else
			 {
				 onlyTom = true;
				 jerry_move =0;
			 }

			 clearObject();
			 if (!onlyTom) {
				 moveJerry();
			 }
			 try {
				 if(isRunning()){
					 moveTom();
				 }
			 } catch (InterruptedException e) {
				 throw new RuntimeException(e);
			 }

			 try {
				 if(isRunning()){
					 moveExterne();
					 pauser();
				 }
			 } catch (InterruptedException e) {
				 throw new RuntimeException(e);
			 }
		 }
	 }

	 private void game_initialize(){
		 // generate a new maze
		 board_mst Board = new board_mst();
		 Board.build_maze();
		 Board.saveMazeToFile();

		 // read maze generated
		 String map_file = "actual_maze.csv";
		 m = new Maze(map_file);

		 // initialize tom, jerry, and the shortest pathfinder
		 tomPos = new VertexLocation(m.exit.x, m.exit.y);
		 jerryPos = new VertexLocation(m.entry.x, m.entry.y);
		 directionJerry = 1;

		 finder = new ShortestPathFinder(map_file);

		 Squares.get(tomPos.x).get(tomPos.y).changeObject(1);
		 Squares.get(jerryPos.x).get(jerryPos.y).changeObject(2);

		 for (int i = 0; i<30; ++i){
			 for (int j = 0; j<30; ++j) {
				 Squares.get(i).get(j).lightMeUp(2);
				 if (m.maze[i][j] == 1) Squares.get(i).get(j).lightMeUp(0);
			 }
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
	 private boolean isRunning() throws InterruptedException {
		 VertexLocation exit = m.exit;
		 boolean gameWin = exit.isSame(jerryPos);
		 if(gameWin) {
			 stopTheGame(true);
			 return false;
		 }

		 boolean getCaught = jerryPos.isSame(tomPos);
		 if(getCaught){
			 stopTheGame(false);
			 return false;
		 }

		 return true;
	 }
	 
	 //Stops The Game
	 private void stopTheGame(boolean win) {
		 running = false;
		 String message;
		 if(win) message = "Congratulations! You successfully escaped from Tom! ";
		 else
			 message = "Oops... You get caught by Tom :( ";

		 JFrame frame = new JFrame(message);
		 JButton exit_button = new JButton("Exit");
		 JButton restart_button = new JButton("Restart");

		 exit_button.addActionListener(e -> {
             JOptionPane.showMessageDialog(frame, "Exit");
             frame.dispose();
             parent_window.dispose();
         });

		 restart_button.addActionListener(e -> {
             JOptionPane.showMessageDialog(frame, "Restart");
             frame.dispose();
             ((Window) parent_window).restart_game();
         });

		 GridBagConstraints gbc = new GridBagConstraints();
		 gbc.gridx = 0;
		 gbc.gridy = 0;
		 gbc.weightx = 1.0;
		 gbc.weighty = 1.0;
		 gbc.fill = GridBagConstraints.CENTER;

		 JPanel optionPanel = new JPanel();
		 optionPanel.add(exit_button);
		 optionPanel.add(restart_button);

		 frame.getContentPane().setLayout(new GridBagLayout());
		 frame.getContentPane().add(optionPanel, gbc);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setSize(300, 200);
		 frame.setLocationRelativeTo(parent_window);
		 frame.setAlwaysOnTop(true);
		 frame.setVisible(true);


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
		 int[] next = finder.find_next(jerryPos.x, jerryPos.y, tomPos.x, tomPos.y);
		 tomPos.updateLocation(next[0], next[1]);
	 }

	 //Refresh the squares that needs to be updated
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
	/*
	 private void displayTomPath() {
		 for (int i = 0; i < tomPath.size(); i++)
			 Squares.get(tomPath.get(i)[0]).get(tomPath.get(i)[1]).lightMeUp(1);
	 }
	 */

}
