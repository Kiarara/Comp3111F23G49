package Function_C;

import Shared.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static Function_C.ThreadsController.directionJerry;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ThreadsControllerTest {
    public Window test_game_window;
    public ThreadsController test_tc;

    @BeforeEach
    void setUp() {
        test_game_window = new Window();
        test_tc = new ThreadsController(test_game_window);
    }

    @Test
    @Order(1)
    public void testThreadsController(){
        Window game_window = new Window();
        ThreadsController tc = new ThreadsController(game_window); // target function
        assertEquals(200, tc.tomSpeed);
        assertEquals(20, tc.num_barrier_removed);
        assertEquals(10, tc.updates_before_jerry_pause);
        assertEquals(5, tc.num_of_freezer);
        assertEquals(5000, tc.propEffectiveDuration);
    }

    @Test
    @Order(2)
    public void testSetMode(){
        // Test setting the mode to easy
        test_tc.setMode(0); // target function
        assertEquals(300, test_tc.tomSpeed);
        assertEquals(40, test_tc.num_barrier_removed);
        assertEquals(20, test_tc.updates_before_jerry_pause);
        assertEquals(10, test_tc.num_of_freezer);
        assertEquals(10000, test_tc.propEffectiveDuration);

        // Test setting the mode to medium
        test_tc.setMode(1); // target function
        assertEquals(200, test_tc.tomSpeed);
        assertEquals(20, test_tc.num_barrier_removed);
        assertEquals(10, test_tc.updates_before_jerry_pause);
        assertEquals(5, test_tc.num_of_freezer);
        assertEquals(5000, test_tc.propEffectiveDuration);

        // Test setting the mode to difficult
        test_tc.setMode(2); // target function
        assertEquals(100, test_tc.tomSpeed);
        assertEquals(10, test_tc.num_barrier_removed);
        assertEquals(5, test_tc.updates_before_jerry_pause);
        assertEquals(3, test_tc.num_of_freezer);
        assertEquals(3000, test_tc.propEffectiveDuration);
    }

    @Test
    @Order(3)
    public void testGameInitialize() {
        assertDoesNotThrow(() -> test_tc.game_initialize()); // target function

        // assert generation of maze and the shortest path finder
        assertNotNull(test_tc.m);
        assertNotNull(test_tc.finder);

        // check initial position of Tom and Jerry
        assertEquals(0, test_tc.jerryPos.y);
        assertEquals(29, test_tc.tomPos.y);
        assertEquals(1, directionJerry);
        assertEquals(0, test_tc.Squares.get(test_tc.tomPos.x).get(test_tc.tomPos.y).getObject());
        assertEquals(1, test_tc.Squares.get(test_tc.jerryPos.x).get(test_tc.jerryPos.y).getObject());

        // check initialization of freezerLocs
        test_tc.setMode(1);
        assertNotNull(test_tc.freezerLocs);
        assertEquals(5, test_tc.freezerLocs.size());
        for (VertexLocation freezerLoc: test_tc.freezerLocs){
            assertEquals(2, test_tc.Squares.get(freezerLoc.x).get(freezerLoc.y).getObject());
        }

        // check initialization of Tuffy Position
        assertNotNull(test_tc.tuffyPos);
        assertEquals(3, test_tc.Squares.get(test_tc.tuffyPos.x).get(test_tc.tuffyPos.y).getObject());

    }

    @Test
    @Order(4)
    public void testPauser(){
        // under normal conditions
        test_tc.pauser(); // target function

        assertThrows(RuntimeException.class, () -> {
            Thread.currentThread().interrupt();  // Force an InterruptedException to be thrown
            test_tc.pauser(); // target function
        });
    }

    @Test
    @Order(5)
    public void testCheckGameEnds() {
        // initialize tom and jerry to entry and exit
        test_tc.game_initialize();

        // check when tom and jerry at different locations and Jerry is not at the exit
        assert(test_tc.checkGameEnds()); // target function

        // check when Tom catches Jerry
        test_tc.tomPos.updateLocation(test_tc.jerryPos.x, test_tc.jerryPos.y);
        assertFalse(test_tc.checkGameEnds()); // target function

        // check when Jerry reaches exit
        test_tc.game_initialize();
        test_tc.moveTom();
        test_tc.jerryPos.updateLocation(test_tc.m.getExit().x, test_tc.m.getExit().y);
        assertFalse(test_tc.checkGameEnds()); // target function
    }

    @Test
    @Order(15)
    public void testCheckFreezer()  {
        test_tc.setMode(1);
        test_tc.game_initialize();

        // check when Jerry's position doesn't have a freezer
        test_tc.checkFreezer(); // target function
        assertNotNull(test_tc.freezerLocs);
        assertEquals(test_tc.num_of_freezer, test_tc.freezerLocs.size());
        for(VertexLocation freezerloc: test_tc.freezerLocs)
        {
            assertEquals(2, test_tc.Squares.get(freezerloc.x).get(freezerloc.y).getObject());
        }

        VertexLocation freezerloc = test_tc.freezerLocs.get(0);
        assertNotNull(freezerloc);

        // check when Jerry finds a freezer
        test_tc.jerryPos = new VertexLocation(freezerloc.x, freezerloc.y);
        test_tc.checkFreezer(); // target function
        assertEquals(test_tc.num_of_freezer-1, test_tc.freezerLocs.size());
        assertEquals(-1, test_tc.Squares.get(freezerloc.x).get(freezerloc.y).getObject());
    }

    @Test
    @Order(16)
    public void testCheckTuffy() {

        test_tc.setMode(1);
        test_tc.game_initialize();

        // check when Jerry hasn't met Tuffy
        assertDoesNotThrow(() -> test_tc.checkTuffy());// target function
        assertEquals(3, test_tc.Squares.get(test_tc.tuffyPos.x).get(test_tc.tuffyPos.y).getObject());

        // check when Jerry meets Tuffy
        test_tc.jerryPos.updateLocation(test_tc.tuffyPos.x, test_tc.tuffyPos.y);
        test_tc.checkTuffy(); // target function

        // check the tuffyPos is cleared
        assertEquals(-1, test_tc.tuffyPos.x);
        assertEquals(-1, test_tc.tuffyPos.y);
        assertEquals(-1, test_tc.Squares.get(test_tc.jerryPos.x).get(test_tc.jerryPos.y).getObject());
    }

    @Test
    @Order(6)
    public void testStopTheGame(){
        test_tc.stopTheGame(true); // target function
        assertFalse(test_tc.running);

        test_tc.running = true;

        test_tc.stopTheGame(false); // target function
        assertFalse(test_tc.running);
    }

    @Test
    @Order(7)
    public void testMoveJerry() throws IOException {
        test_tc.m = new Maze("maze_for_testing.csv");
        test_tc.jerryPos = new VertexLocation(0,0);

        // when the move is valid
        for(int i: new int[] {1,2,3,4}){
            test_tc.jerryPos.updateLocation(19, 4);
            test_tc.directionJerry = i;
            test_tc.moveJerry(); // target function
            assert(!test_tc.jerryPos.isSame(new VertexLocation(19,4)));
        }

        // when the move is invalid
        for(int i: new int[] {1,2,3,4}){
            test_tc.jerryPos.updateLocation(9, 0);
            test_tc.directionJerry = i;
            test_tc.moveJerry(); // target function
            assert(test_tc.jerryPos.isSame(new VertexLocation(9,0)));
        }
    }

    @Test
    @Order(8)
    public void testMoveTom(){
        test_tc.setMode(0);
        test_tc.game_initialize();
        VertexLocation original = new VertexLocation(test_tc.tomPos); // the exit to a maze
        test_tc.moveTom(); // target function
        assertEquals(original.x, test_tc.tomPos.x);
        assertEquals(original.y-1, test_tc.tomPos.y);
    }

    @Test
    @Order(9)
    public void testMoveExterne(){
        test_tc.jerryPos = new VertexLocation(0,0);
        test_tc.tomPos = new VertexLocation(10,10);

        // check when both Jerry and Tom get displayed on a new location
        test_tc.moveExterne(); // target function
        assertEquals(1, test_tc.Squares.get(0).get(0).getObject());
        assertEquals(0, test_tc.Squares.get(10).get(10).getObject());

        // check when only Jerry gets updated
        test_tc.Squares.get(10).get(10).changeObject(2);
        test_tc.moveExterne(); // target function
        assertEquals(1, test_tc.Squares.get(0).get(0).getObject());
        assertNotEquals(0, test_tc.Squares.get(10).get(10).getObject());
    }

    @Test
    @Order(10)
    public void testClearObject(){
        test_tc.jerryPos = new VertexLocation(0,0);
        test_tc.tomPos = new VertexLocation(10,10);
        test_tc.Squares.get(0).get(0).changeObject(1);
        test_tc.Squares.get(10).get(10).changeObject(0);

        // check when both Jerry and Tom get cleared
        test_tc.clearObject(); // target function
        assertEquals(-1, test_tc.Squares.get(0).get(0).getObject());
        assertEquals(-1, test_tc.Squares.get(10).get(10).getObject());

        // check when only Jerry gets updated
        test_tc.Squares.get(10).get(10).changeObject(2);
        test_tc.clearObject(); // target function
        assertEquals(-1, test_tc.Squares.get(0).get(0).getObject());
        assertNotEquals(-1, test_tc.Squares.get(10).get(10).getObject());
    }

    @Test
    @Order(11)
    public void testFreezeTom() throws InterruptedException {
        test_tc.is_tom_frozen = false;
        test_tc.propEffectiveDuration = 2000;
        test_tc.freezeTom(); // target function
        assert(test_tc.is_tom_frozen);

        CountDownLatch latch = new CountDownLatch(1);

        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                assert(!test_tc.is_tom_frozen);
                latch.countDown();
            }
        }, 2500);

        boolean isCompleted = latch.await(1, TimeUnit.MINUTES);
        assertTrue(isCompleted);
    }

    @Test
    @Order(12)
    public void testTuffyComes() throws InterruptedException {
        test_tc.setMode(0);
        test_tc.game_initialize();

        CountDownLatch latch = new CountDownLatch(1);
        test_tc.tuffyComes(); // target function
        LinkedList<int[]> path_expected = test_tc.finder.findShortestPath(test_tc.m.getEntry(),test_tc.m.getExit());
        for (int[] path: path_expected)
            assertEquals(2, test_tc.Squares.get(path[0]).get(path[1]).getColor());

        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i<30; ++i)
                    for (int j = 0; j<30; ++j)
                        assertNotEquals(2, test_tc.Squares.get(i).get(j).getColor());
                latch.countDown();
            }
        }, test_tc.propEffectiveDuration+2000);

        boolean isCompleted = latch.await(1, TimeUnit.MINUTES);
        assertTrue(isCompleted);

    }

    @Test
    @Order(13)
    public void testExitOrRestart(){
        test_tc.exit_or_restart("Congratulations");
    }

    @Test
    @Order(14)
    public void testRun(){
        test_tc.run();
    }

}
