import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * The test class SilkRoadTest.
 *
 * @author  Juan Pablo Vélez
 * @author Paula Díaz
 * @version 4
 */
public class SilkRoadC2Test{
    /**
     * Default constructor for test class SilkRoadTest
     */
    public SilkRoadC2Test(){}

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp(){}
    
    //Ciclo 1
    @Test
    public void shouldSilkRoad(){
        //Given
        Bar testBar = new Bar();
        //When
        SilkRoad simulator = new SilkRoad(50);
        //Then
        assertTrue(simulator.getLength() == 50);
        assertTrue(simulator.getDays() == 0);
        assertTrue(simulator.getActualDay() == 0);
        assertTrue(simulator.getRobots().size() == 0);
        assertTrue(simulator.getStores().size() == 0);
        assertEquals(simulator.getProgressBar(), testBar);
        assertTrue(simulator.getRoad().size() == 50);
    }
    
    @Test
    public void shouldSilkRoad2(){
        //Given
        Bar testBar = new Bar();
        //When
        SilkRoad simulator = new SilkRoad(-123);
        //Then
        assertTrue(simulator.getLength() == 123);
        assertTrue(simulator.getDays() == 0);
        assertTrue(simulator.getActualDay() == 0);
        assertTrue(simulator.getRobots().size() == 0);
        assertTrue(simulator.getStores().size() == 0);
        assertEquals(simulator.getProgressBar(), testBar);
        assertTrue(simulator.getRoad().size() == 123);
    }
    
    @Test
    public void shouldPlaceStore(){
        //Given
        SilkRoad simulator = new SilkRoad(34);
        //When
        simulator.placeStore(34, 100);
        //Then
        assertTrue(simulator.getStores().get(0) instanceof Store);
        Store newStore = simulator.getStores().get(0);
        assertTrue(newStore.getLocation() == 34);
        assertTrue(newStore.getStash() == 100);
        assertTrue(newStore.getTimesStolen() == 0);
        assertTrue(newStore.isFull());
    }
    
    @Test
    public void shoudNotPlaceStore(){
        //Given
        SilkRoad simulator = new SilkRoad(50);
        //When
        simulator.placeStore(-12, 55);
        //then
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldNotPlaceStore2(){
        //Given
        SilkRoad simulator = new SilkRoad(34);
        //When
        simulator.placeStore(34, -1);
        //Then
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldNotPlaceStore3(){
        //Given
        SilkRoad simulator = new SilkRoad(20);
        simulator.placeStore(1, 4);
        //When
        simulator.placeStore(1, 100);
        //Then
        assertFalse(simulator.ok());
        Store newStore = simulator.getStores().get(0);
        assertTrue(newStore.getLocation() == 1);
        assertTrue(newStore.getStash() == 4);
    }
    
    @Test
    public void shouldRemoveStore(){
        //Given
        SilkRoad simulator = new SilkRoad(20);
        simulator.placeStore(10, 10);
        //When
        simulator.removeStore(10);
        //Then
        assertTrue(simulator.ok());
        assertFalse(simulator.getRoad().get(9).hasStore());
        assertTrue(simulator.getStores().size() == 0);
    }
    
    @Test
    public void shouldNotRemoveStore(){
        //Given
        SilkRoad simulator = new SilkRoad(20);
        simulator.placeStore(20, 20);
        //When
        simulator.removeStore(25);
        //Then
        assertFalse(simulator.ok());
    }
        
    @Test
    public void shouldNotRemoveStore2(){
        //Given
        SilkRoad simulator = new SilkRoad(20);
        simulator.placeStore(11, 20);
        //When
        simulator.removeStore(10);
        //Then
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldPlaceRobot(){
        //Given
        SilkRoad simulator = new SilkRoad(20);
        //When
        simulator.placeRobot(1);
        //Then
        assertTrue(simulator.getRobots().size() == 1);
        Robot newRobot = simulator.getRobots().get(0);
        assertTrue(newRobot.getActualLocation() == 1);
        assertTrue(newRobot.getInitialLocation() == 1);
        assertTrue(newRobot.getProfit() == 0);
        assertTrue(newRobot.getProfitsPerMove().size() == 0);
    }
    
    @Test
    public void shouldNotPlaceRobot(){
        //Given
        SilkRoad simulator = new SilkRoad(20);
        //When
        simulator.placeRobot(-14);
        //Then
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldNotPlaceRobot2(){
        //Given
        SilkRoad simulator = new SilkRoad(20);
        //When
        simulator.placeRobot(21);
        //Then
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldRemoveRobot(){
        //Given
        SilkRoad simulator = new SilkRoad(20);
        simulator.placeRobot(12);
        //When
        simulator.removeRobot(12);
        //Then
        assertTrue(simulator.ok());
        assertTrue(simulator.getRobots().size() == 0);
        assertTrue(simulator.getRoad().get(11).getRobots().size() == 0);
    }
    
    @Test
    public void shouldNotRemoveRobot(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        //When
        simulator.removeRobot(100);
        //Then
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldNotRemoveRobot2(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        //When
        simulator.removeRobot(-100);
        //Then
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldNotRemoveRobot3(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        //When
        simulator.removeRobot(5);
        //Then
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldMoveRobot(){
        //Given
        SilkRoad simulator = new SilkRoad(20);
        simulator.placeRobot(5);
        //When
        simulator.moveRobot(5, 5);
        //Then
        assertTrue(simulator.ok());
        Robot mover = simulator.getRobots().get(0);
        assertTrue(mover.getActualLocation() == 10);
        assertTrue(mover.getInitialLocation() == 5);
        assertTrue(mover.getProfitsPerMove().size() == 1 && mover.getProfitsPerMove().get(0).intValue() == -5);
        assertTrue(mover.getProfit() == -5);
    }
    
    @Test
    public void shouldMoveRobot2(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        simulator.placeStore(3, 7);
        simulator.placeRobot(7);
        //When
        simulator.moveRobot(7, -4);
        //Then
        assertTrue(simulator.ok());
        Robot mover = simulator.getRobots().get(0);
        assertTrue(mover.getActualLocation() == 3);
        assertTrue(mover.getInitialLocation() == 7);
        assertTrue(mover.getProfitsPerMove().size() == 1 && mover.getProfitsPerMove().get(0).intValue() == 3);
        assertTrue(mover.getProfit() == 3);
        Store stolen = simulator.getStores().get(0);
        assertTrue(stolen.getTimesStolen() == 1);
        assertFalse(stolen.isFull());
    }
    
    @Test
    public void shoulMoveRobot3(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        simulator.placeStore(3, 7);
        simulator.placeRobot(7);
        simulator.placeRobot(6);
        simulator.moveRobot(7, -1);
        //When
        simulator.moveRobot(6, -3);
        //Then
        assertTrue(simulator.ok());
        Robot mover1 = simulator.getRobots().get(0);
        assertTrue(mover1.getActualLocation() == 6);
        assertTrue(mover1.getInitialLocation() == 7);
        assertTrue(mover1.getProfitsPerMove().size() == 1 && mover1.getProfitsPerMove().get(0).intValue() == -1);
        assertTrue(mover1.getProfit() == -1);
        Robot mover2 = simulator.getRobots().get(1);
        assertTrue(mover2.getActualLocation() == 3);
        assertTrue(mover2.getInitialLocation() == 6);
        assertTrue(mover2.getProfitsPerMove().size() == 1 && mover2.getProfitsPerMove().get(0).intValue() == 4);
        assertTrue(mover2.getProfit() == 4);
        Store stolen = simulator.getStores().get(0);
        assertTrue(stolen.getTimesStolen() == 1);
        assertFalse(stolen.isFull());
    }
    
    @Test
    public void shouldNotMoveRobot(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        //When
        simulator.moveRobot(3, 6);
        //Then
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldNotMoveRobot2(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        //When
        simulator.moveRobot(15,-7);
        //Then
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldNotMoveRobot3(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        simulator.placeRobot(1);
        //When
        simulator.moveRobot(1, -5);
        //Then
        assertFalse(simulator.ok());
        Robot mover = simulator.getRobots().get(0);
        assertTrue(mover.getActualLocation() == 1);
        assertTrue(mover.getInitialLocation() == 1);
        assertTrue(mover.getProfitsPerMove().size() == 0);
    }
    
    @Test
    public void shouldResupplyStores(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        simulator.placeRobot(2);
        simulator.placeStore(3, 5);
        simulator.placeStore(4, 1);
        simulator.placeStore(5,2);
        simulator.moveRobot(2, 1);
        simulator.moveRobot(3, 1);
        simulator.moveRobot(4, 1);
        //When
        simulator.resupplyStores();
        //Then
        assertTrue(simulator.ok());
        Store store1 = simulator.getStores().get(0);
        Store store2 = simulator.getStores().get(1);
        Store store3 = simulator.getStores().get(2);
        assertTrue(store1.getTimesStolen() == 1 && store1.isFull());
        assertTrue(store2.getTimesStolen() == 1 && store2.isFull());
        assertTrue(store3.getTimesStolen() == 1 && store3.isFull());
    }
    
    @Test
    public void shouldNotResupplyStores(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        //When
        simulator.resupplyStores();
        //Then
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldReturnRobots(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        simulator.placeRobot(10);
        simulator.placeRobot(1);
        simulator.moveRobot(10, -5);
        simulator.moveRobot(1, 4);
        //When
        simulator.returnRobots();
        //Then
        assertTrue(simulator.ok());
        Robot robot1 = simulator.getRobots().get(0);
        Robot robot2 = simulator.getRobots().get(1);
        assertTrue(robot1.getActualLocation() == 10 && robot1.getInitialLocation() == 10 && robot1.getProfitsPerMove().size() == 1 && robot1.getProfit() == -5);
        assertTrue(robot2.getActualLocation() == 1 && robot2.getInitialLocation() == 1 && robot2.getProfitsPerMove().size() == 1 && robot2.getProfit() == -4);
        ArrayList<Block> road = simulator.getRoad();
        assertFalse(road.get(4).hasRobots());
        assertTrue(road.get(0).hasRobots());
        assertTrue(road.get(9).hasRobots());
    }
    
    @Test
    public void shouldNotReturnRobots(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        //When
        simulator.returnRobots();
        //Then
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldRebbot(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        simulator.placeRobot(2);
        simulator.placeStore(3, 5);
        simulator.placeStore(4, 1);
        simulator.placeStore(5,2);
        simulator.moveRobot(2, 1);
        simulator.moveRobot(3, 1);
        simulator.moveRobot(4, 1);
        //When
        simulator.reboot();
        //Then
        assertTrue(simulator.ok());
        Store store1 = simulator.getStores().get(0);
        Store store2 = simulator.getStores().get(1);
        Store store3 = simulator.getStores().get(2);
        assertTrue(store1.getTimesStolen() == 1 && store1.isFull());
        assertTrue(store2.getTimesStolen() == 1 && store2.isFull());
        assertTrue(store3.getTimesStolen() == 1 && store3.isFull());
        Robot mover = simulator.getRobots().get(0);
        assertTrue(mover.getInitialLocation() == 2 && mover.getActualLocation() == 2 && mover.getProfit() == 5);
    }
    
    @Test
    public void shouldProfit(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        simulator.placeRobot(1);
        simulator.placeRobot(5);
        simulator.placeRobot(7);
        simulator.placeStore(8, 30);
        simulator.moveRobot(7, 1);
        simulator.moveRobot(1, 5);
        simulator.moveRobot(5, 5);
        //When
        int profit = simulator.profit();
        //Then
        assertTrue(profit == 19);
    }
    
    @Test
    public void shouldStores(){
        //Given
        SilkRoad simulator = new SilkRoad(20);
        int[][] answer = {{3, 15}, {7, 1}, {16, 1000}, {20, 20}};
        simulator.placeStore(16, 1000);
        simulator.placeStore(20, 20);
        simulator.placeStore(7, 1);
        simulator.placeStore(3, 15);
        //When
        int[][] stores = simulator.stores();
        //Then
        assertArrayEquals(answer, stores);
    }
    
    @Test
    public void shouldRobots(){
        //Given
        SilkRoad simulator = new SilkRoad(25);
        int[][] answer = {{4, 0}, {15, -4}, {15, 4}, {22 ,3}};
        simulator.placeStore(10, 100);
        simulator.placeStore(15, 10);
        simulator.placeStore(22, 14);
        simulator.placeRobot(4);
        simulator.placeRobot(11);
        simulator.placeRobot(11);
        simulator.placeRobot(21);
        simulator.moveRobot(11, 11);
        simulator.moveRobot(21, -6);
        simulator.moveRobot(11, 4);
        //When
        int[][] robots = simulator.robots();
        //Then
        assertArrayEquals(answer, robots);
    }
    
    //Ciclo 2
    @Test
    public void shouldSilkRoadElements(){
        //Given
        int[][] elements = {{1, 20, 0}, {2, 15, 15}, {2, 40, 50}, {1, 50, 0}, {2, 80, 20}, {2, 70, 30}};
        Bar testBar = new Bar();
        //When
        SilkRoad simulator = new SilkRoad(10, elements);
        //Then
        assertTrue(simulator.getLength() == 80);
        assertTrue(simulator.getDays() == 10);
        assertTrue(simulator.getActualDay() == 0);
        assertTrue(simulator.getStores().size() == 0);
        assertEquals(simulator.getProgressBar(), testBar);
        assertTrue(simulator.getRoad().size() == 80);
    }
    
    @Test
    public void shouldMoveRobots(){
        //Given
        SilkRoad simulator = new SilkRoad(20);
        simulator.placeStore(4, 12);
        simulator.placeStore(9, 4);
        simulator.placeStore(17, 9);
        simulator.placeStore(15, 40);
        simulator.placeRobot(13);
        simulator.placeRobot(19);
        simulator.placeRobot(5);
        simulator.placeRobot(16);
        simulator.placeRobot(4);
        //When
        simulator.moveRobots();
        //Then
        ArrayList<Robot> robots = simulator.getRobots();
        Robot r1 = robots.get(0);
        Robot r2 = robots.get(1);
        Robot r3 = robots.get(2);
        Robot r4 = robots.get(3);
        Robot r5 = robots.get(4);
        assertTrue(r1.getInitialLocation() == 13 && r1.getActualLocation() == 9 && r1.getProfit() == 0);
        assertTrue(r2.getInitialLocation() == 19 && r2.getActualLocation() == 19 && r2.getProfit() == 0);
        assertTrue(r3.getInitialLocation() == 5 && r3.getActualLocation() == 5 && r3.getProfit() == 0);
        assertTrue(r4.getInitialLocation() == 16 && r4.getActualLocation() == 15 && r4.getProfit() == 46);
        assertTrue(r5.getInitialLocation() == 4 && r5.getActualLocation() == 4 && r5.getProfit() == 12);
    }
    
    @Test
    public void shouldNotMoveRobots(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        simulator.placeStore(5, 100);
        //When
        simulator.moveRobots();
        //Then
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldNotMoveRobots2(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        simulator.placeRobot(5);
        //When
        simulator.moveRobots();
        //Then
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldEmptiedStores(){
        //Given
        SilkRoad simulator = new SilkRoad(20);
        int[][] answer = {{5, 4}, {11, 1}, {15, 2}};
        simulator.placeStore(15, 8);
        simulator.placeStore(5, 23);
        simulator.placeStore(11, 14);
        simulator.placeRobot(12);
        simulator.moveRobot(12,-1);
        simulator.moveRobot(11, 4);
        simulator.moveRobot(15, -10);
        simulator.reboot();
        simulator.moveRobot(12, 3);
        simulator.moveRobot(15, -10);
        simulator.reboot();
        simulator.moveRobot(12, -7);
        simulator.reboot();
        simulator.moveRobot(12, -7);
        //When
        int[][] emptiedStores = simulator.emptiedStores();
        //Then
        assertArrayEquals(answer, emptiedStores);
    }
    
    @Test
    public void shouldNotEmptiedStores(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        //When
        int[][] emptiedStores = simulator.emptiedStores();
        //Then
        assertNull(emptiedStores);
    }
    
    @Test
    public void shouldProfitPerMove(){
        //Given
        SilkRoad simulator = new SilkRoad(20);
        int[][] answer = {{5, 4}, {11, 1}, {15, 2}};
        simulator.placeStore(15, 8);
        simulator.placeStore(5, 23);
        simulator.placeStore(11, 14);
        simulator.placeRobot(12);
        simulator.moveRobot(12,-1);
        simulator.moveRobot(11, 4);
        simulator.moveRobot(15, -10);
        simulator.reboot();
        simulator.moveRobot(12, 3);
        simulator.moveRobot(15, -10);
        simulator.reboot();
        simulator.moveRobot(12, -7);
        simulator.reboot();
        simulator.moveRobot(12, -7);
        //When
        int[][] profitsPerMove = simulator.emptiedStores();
        //Then
        assertArrayEquals(answer, profitsPerMove);
    }
    
    @Test
    public void shouldNotProfitPerMove(){
        //Given
        SilkRoad simulator = new SilkRoad(10);
        //When
        int[][] profitsPerMove = simulator.profitPerMove();
        //Then
        assertNull(profitsPerMove);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown(){}
}