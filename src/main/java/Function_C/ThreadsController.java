
package Function_C;

import java.awt.*;
import java.util.ArrayList;
import Function_A.Board_MST;
import Function_B.ShortestPathFinder;
import Shared.DataOfSquare;
import Shared.Maze;
import Shared.Window;

import javax.swing.*;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;


//Controls all the game logic --> most important class in this project.
public class ThreadsController extends Thread {
	 ArrayList<ArrayList<DataOfSquare>> Squares;
	 private JFrame parent_window;
	 VertexLocation tomPos;
	 VertexLocation jerryPos;
	 ArrayList<VertexLocation> freezerLocs;
	 boolean is_tom_frozen = false;


	 public static int directionJerry;

	 // for mode setting
	 long tomSpeed = 200;
	 int num_barrier_removed = 20;
	 int updates_before_jerry_pause = 10;
	 int num_of_freezer = 5;
	 long freezeTime = 5000;

	 Maze m;
	 ShortestPathFinder finder;
	 public boolean running=false;

	 public ThreadsController(JFrame this_window){
		//Get all the threads
		Squares= Window.Grid;
		parent_window = this_window;
		freezerLocs = new ArrayList<>();
	 }

	// Important part: the controller for the game
	// During the same period of time, Tom updates five times while Jerry only updates four times
	 public void run() {
		 int jerry_move = 0;
		 boolean onlyTom;
		 running = true;

		 game_initialize();

		 while(running){
			 if(jerry_move < 10)
			 {
				 onlyTom = false;
				 jerry_move ++;
			 }
			 else
			 {
				 onlyTom = true;
				 jerry_move = 0;
			 }

			 clearObject();
			 if (!onlyTom) {
				 moveJerry();
				 checkFreezer();
			 }
			 try {
				 if(isRunning()&& (!is_tom_frozen)){
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
		 Board_MST Board = new Board_MST();
		 Board.build_maze();
		 for(int i=0;i<10;i++) {
			 Board.build_more_path();
		 }
		 Board.saveMazeToFile();

		 // read and display maze generated
		 String map_file = "actual_maze.csv";
		 ((Window)parent_window).set_maze(map_file);
		 m = ((Window) parent_window).getMaze();
		 ((Window) parent_window).display_maze();

		 // initialize tom, jerry, and the shortest pathfinder
		 tomPos = new VertexLocation(m.getExit());
		 jerryPos = new VertexLocation(m.getEntry());
		 directionJerry = 1;

		 finder = new ShortestPathFinder(m);

		 Squares.get(tomPos.x).get(tomPos.y).changeObject(0);
		 Squares.get(jerryPos.x).get(jerryPos.y).changeObject(1);

		 // generate and display the locations of freezers
		 int num_generated = 0;
		 while (num_generated < num_of_freezer){
			 Random rand = new Random();
			 int row = rand.nextInt(29);
			 int col = rand.nextInt(28)+1;
			 if (m.maze[row][col] == 0){
				 freezerLocs.add(new VertexLocation(row, col));
				 Squares.get(row).get(col).changeObject(2);
				 num_generated++;
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
		 VertexLocation exit = m.getExit();
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

	 private void checkFreezer(){
		 for(VertexLocation freezer: freezerLocs){
			 if (jerryPos.isSame(freezer)){
				 Squares.get(freezer.x).get(freezer.y).clearObject();
				 freezeTom();
				 freezerLocs.remove(freezer);
				 break;
			 }
		 }
	 }

	 private void stopTheGame(boolean win) {
		 // stop the game
		 running = false;

		 // print out messages
		 String message;
		 if(win) message = "Congratulations! You successfully escaped from Tom! ";
		 else
			 message = "Oops... You get caught by Tom :( ";

		 JFrame exit_or_restart = new JFrame(message);
		 JButton exit_button = new JButton("Exit");
		 JButton restart_button = new JButton("Restart");

		 // check if users would like to exit or restart the game
		 exit_button.addActionListener(e -> {
             exit_or_restart.dispose();
             parent_window.dispose();
         });

		 restart_button.addActionListener(e -> {
			 // clear freezer
			 for (VertexLocation freezer: freezerLocs)
				 Squares.get(freezer.x).get(freezer.y).clearObject();
			 try {
				 sleep(2);
			 } catch (InterruptedException ex) {
				 throw new RuntimeException(ex);
			 }

			 // allow the event dispatch thread to process the disposal of the frame
			 EventQueue.invokeLater(() -> {
				 exit_or_restart.dispose();
				 ((Window) parent_window).restart_game();
			 });

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

		 exit_or_restart.getContentPane().setLayout(new GridBagLayout());
		 exit_or_restart.getContentPane().add(optionPanel, gbc);
		 exit_or_restart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 exit_or_restart.setSize(300, 200);
		 exit_or_restart.setLocationRelativeTo(parent_window);
		 exit_or_restart.setAlwaysOnTop(true);
		 exit_or_restart.setVisible(true);

	 }

	 // Moves Jerry internally (by updating the location stored)
	 // 1:right 2:left 3:top 4:bottom 0:nothing
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

	// Moves Tom internally (by updating the location stored)
	 private void moveTom() throws InterruptedException {
		 int[] next = finder.find_next(jerryPos, tomPos);
		 tomPos.updateLocation(next[0], next[1]);
	 }

	 // Display Tom and Jerry on the corresponding squares
	 private void moveExterne(){
		 // update Jerry
		 Squares.get(tomPos.x).get(tomPos.y).changeObject(0);
		 Squares.get(jerryPos.x).get(jerryPos.y).changeObject(1);
	 }

	 // Clear the images of Tom and Jerry before displaying them on other squares
	 private void clearObject(){
		 Squares.get(jerryPos.x).get(jerryPos.y).clearObject();
		 Squares.get(tomPos.x).get(tomPos.y).clearObject();
	 }

	 public void setMode(int mode){
		 switch (mode){
			 case 0: //easy
				 tomSpeed = 300;
				 num_barrier_removed = 40;
				 updates_before_jerry_pause = 20;
				 num_of_freezer = 10;
				 freezeTime = 10000;
				 break;
			 case 1: //medium
				 tomSpeed = 200;
				 num_barrier_removed = 20;
				 updates_before_jerry_pause = 10;
				 num_of_freezer = 5;
				 freezeTime = 5000;
				 break;
			 case 2: //difficult
				 tomSpeed = 100;
				 num_barrier_removed = 10;
				 updates_before_jerry_pause = 5;
				 num_of_freezer = 3;
				 freezeTime = 3000;
				 break;
		 }
	 }

	 private void freezeTom(){
		 is_tom_frozen = true;
		 Timer timer = new Timer();
		 timer.schedule(new TimerTask() {
			 @Override
			 public void run() {
				is_tom_frozen = false;
			 }
		 }, freezeTime);
	 }

}
