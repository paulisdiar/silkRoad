import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * The test class SilkRoadTest.
 *
 * @author  Paula Días
 * @author Juan Pablo Vélez
 * @version 3
 */
public class SilkRoadC2Test{
    /**
     * Default constructor for test class SilkRoadC2Test
     */
    public SilkRoadC2Test(){
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp(){
    }
    
    @Test
    public void shouldCreateSilkRoad(){
        SilkRoad simulator = new SilkRoad(50);
        Road testRoad = new Road(50);
        assertTrue(simulator.getLength() == 50);
        assertTrue(simulator.getTotalProfit() == 0);
        assertFalse(simulator.isVisible());
        assertTrue(simulator.getStores().size() == 50);
        assertTrue(simulator.getRobots().size() == 0);
        assertTrue(testRoad.equals(simulator.getRoad()));
    }
    
    @Test
    public void shouldCreateSilkRoad2(){
        SilkRoad simulator = new SilkRoad(-123);
        Road testRoad = new Road(123);
        assertTrue(simulator.getLength() == 123);
        assertTrue(simulator.getTotalProfit() == 0);
        assertFalse(simulator.isVisible());
        assertTrue(simulator.getStores().size() == 123);
        assertTrue(simulator.getRobots().size() == 0);
        assertTrue(testRoad.equals(simulator.getRoad()));
    }
    
    @Test
    public void shouldPlaceStore(){
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeStore(50, 45);
        assertTrue(simulator.ok());
        simulator.placeStore(1, 54);
        assertTrue(simulator.ok());
        simulator.placeStore(14, 100);
        assertTrue(simulator.ok());
        assertTrue(simulator.countStores()==3);
    }
    
    @Test
    public void shoudNotPlaceStore(){
        //fuera de road
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeStore(-14, 4); 
        assertFalse(simulator.ok());
        simulator.placeStore(25, 1);
        assertTrue(simulator.ok());
        simulator.placeStore(51, 40);
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldNotPlaceStore2(){
        //ya hay tienda
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeStore(25, 1);
        assertTrue(simulator.ok());
        simulator.placeStore(25, 40);
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldNotPlaceStore3(){
        //tenges negativos
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeStore(25, -123);
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldRemoveStore(){
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeStore(25, 1);
        assertTrue(simulator.ok());
        simulator.placeStore(123, -15);
        assertFalse(simulator.ok());
        simulator.removeStore(25);
        assertTrue(simulator.ok());
    }
    
    @Test
    public void shouldNotRemoveStore(){
        //fuera de la road
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeStore(25, 1);
        assertTrue(simulator.ok());
        simulator.removeStore(100);
        assertFalse(simulator.ok());
        simulator.placeStore(30, 1);
        assertTrue(simulator.ok());
        simulator.removeStore(-14);
        assertFalse(simulator.ok());
    }
        
    @Test
    public void shouldNotRemoveStore2(){
        //no hay tienda
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeStore(25, 1);
        assertTrue(simulator.ok());
        simulator.removeStore(24);
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldPlaceRobot(){
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeRobot(50);
        assertTrue(simulator.ok());
        simulator.placeRobot(1);
        assertTrue(simulator.ok());
        simulator.placeRobot(13);
        assertTrue(simulator.ok());
    }
    
    @Test
    public void shouldNotPlaceRobot(){
        //fuera de road
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeRobot(67);
        assertFalse(simulator.ok());
        simulator.placeRobot(45);
        assertTrue(simulator.ok());
        simulator.placeRobot(51);
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldNotPlaceRobot2(){
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeRobot(-1);
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldRemoveRobot(){
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeRobot(50);
        assertTrue(simulator.ok());
        simulator.placeRobot(20);
        assertTrue(simulator.ok());
        simulator.placeRobot(5);
        assertTrue(simulator.ok());
        assertFalse(simulator.getRobots().isEmpty());
        simulator.removeRobot(20);
        assertTrue(simulator.ok());
        simulator.removeRobot(50);
        assertTrue(simulator.ok());
        simulator.removeRobot(5);
        assertTrue(simulator.ok());
        assertTrue(simulator.getRobots().isEmpty());
    }
    
    @Test
    public void shouldNotRemoveRobot(){
        //fuera de road
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeRobot(50);
        assertTrue(simulator.ok());
        simulator.removeRobot(123);
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldNotRemoveRobot2(){
        //location negativa
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeRobot(15);
        assertTrue(simulator.ok());
        simulator.removeRobot(-5);
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldNotRemoveRobot3(){
        //no hay robot
        SilkRoad simulator = new SilkRoad(50);
        simulator.removeRobot(15);
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldMove(){
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeRobot(25);
        simulator.placeRobot(25);
        try{
            simulator.moveRobot(25, 5);
            assertTrue(simulator.ok());
            assertTrue(simulator.getRobots().get(0).getProfit() == -5);
            assertTrue(simulator.getRobots().get(1).getProfit() == 0);
            simulator.moveRobot(30, -5);
            assertTrue(simulator.ok());
            assertTrue(simulator.getRobots().get(0).getProfit() == -10);
        }catch(InterruptedException e){}
    }
    
    @Test
    public void shouldMove2(){
        //mover a tienda
        SilkRoad simulator = new SilkRoad(25);
        simulator.placeRobot(10);
        simulator.placeStore(15, 100);
        simulator.placeStore(5, 15);
        try{
            simulator.moveRobot(10, 5);
            assertTrue(simulator.ok());
            assertTrue(simulator.getRobots().get(0).getProfit() == 95);
            assertFalse(simulator.getStores().get(14).isFull());
            simulator.moveRobot(15, -10);
            assertTrue(simulator.ok());
            assertFalse(simulator.getStores().get(4).isFull());
            assertTrue(simulator.getRobots().get(0).getProfit() == 100);
        }catch(InterruptedException e){}
    }
    
    @Test
    public void shouldMove3(){
        //dos robots a la misma tienda
        SilkRoad simulator = new SilkRoad(25);
        simulator.placeRobot(10);
        simulator.placeRobot(20);
        simulator.placeStore(15, 100);
        try{
            simulator.moveRobot(10, 5);
            assertTrue(simulator.ok());
            assertTrue(simulator.getRobots().get(0).getProfit() == 95);
            assertFalse(simulator.getStores().get(14).isFull());
            simulator.moveRobot(20, -5);
            assertTrue(simulator.ok());
            assertTrue(simulator.getRobots().get(1).getProfit() == -5);
        }catch(InterruptedException e){}
    }
    
    @Test
    public void shouldNotMove(){
        //fuera de road
        SilkRoad simulator = new SilkRoad(25);
        simulator.placeRobot(10);
        try{
            simulator.moveRobot(26, -6);
            assertFalse(simulator.ok());
        }catch(InterruptedException e){}
    }
    
    @Test
    public void shouldNotMove2(){
        //location negativa
        SilkRoad simulator = new SilkRoad(25);
        simulator.placeRobot(10);
        try{
            simulator.moveRobot(-10, 5);
            assertFalse(simulator.ok());
        }catch(InterruptedException e){}
    }
    
    @Test
    public void shouldNotMove3(){
        //no hay robot
        SilkRoad simulator = new SilkRoad(25);
        try{
            simulator.moveRobot(5, 5);
            assertFalse(simulator.ok());
        }catch(InterruptedException e){}
    }
    
    @Test
    public void shouldNotMove4(){
        //se sale del road
        SilkRoad simulator = new SilkRoad(25);
        simulator.placeRobot(10);
        try{
            simulator.moveRobot(10, -30);
            assertFalse(simulator.ok());
        }catch(InterruptedException e){}
    }
    
    @Test
    public void shouldResupplyStores(){
        SilkRoad simulator = new SilkRoad(10);
        simulator.placeStore(7, 5);
        simulator.placeStore(3, 5);
        simulator.placeRobot(5);
        simulator.placeRobot(5);
        try{
            simulator.moveRobot(5, 2);
            assertTrue(simulator.ok());
            assertFalse(simulator.getStores().get(6).isFull());
            simulator.moveRobot(5, -2);
            assertTrue(simulator.ok());
            assertFalse(simulator.getStores().get(2).isFull());
        }catch(InterruptedException e){}
        simulator.resupplyStores();
        assertTrue(simulator.ok());
    }
    
    @Test
    public void shouldNotResupplyStores(){
        SilkRoad simulator = new SilkRoad(10);
        simulator.resupplyStores();
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldReturnRobots(){
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeRobot(3);
        simulator.placeRobot(7);
        try{
            simulator.moveRobot(3, 2);
            assertTrue(simulator.getRobots().get(0).getActualLocation() == 5);
            simulator.moveRobot(7, -2);
            assertTrue(simulator.getRobots().get(1).getActualLocation() == 5);
        }catch(InterruptedException e){}
        simulator.returnRobots();
        assertTrue(simulator.getRobots().get(0).getActualLocation() == 3);
        assertTrue(simulator.getRobots().get(1).getActualLocation() == 7);
    }
    
    @Test
    public void shouldNotReturnRobots(){
        SilkRoad simulator = new SilkRoad(50);
        simulator.returnRobots();
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldRebbot(){
        SilkRoad simulator = new SilkRoad(50);
        simulator.placeRobot(3);
        simulator.placeRobot(7);
        simulator.placeStore(5, 50);
        simulator.placeStore(8, 15);
        assertTrue(simulator.getStores().get(4).isFull());
        assertTrue(simulator.getStores().get(7).isFull());
        try{
            simulator.moveRobot(3, 2);
            assertTrue(simulator.getRobots().get(0).getActualLocation() == 5);
            assertFalse(simulator.getStores().get(4).isFull());
            simulator.moveRobot(7, 1);
            assertTrue(simulator.getRobots().get(1).getActualLocation() == 8);
            assertFalse(simulator.getStores().get(7).isFull());
        }catch(InterruptedException e){}
        simulator.reboot();
        assertTrue(simulator.getRobots().get(0).getActualLocation() == 3);
        assertTrue(simulator.getRobots().get(1).getActualLocation() == 7);
        simulator.reboot();
        simulator.reboot();
        simulator.reboot();
        assertTrue(simulator.getActualDay() == 4);
    }
    
    @Test
    public void shouldProfit(){
        SilkRoad simulator = new SilkRoad(20);
        simulator.placeRobot(3);
        simulator.placeRobot(7);
        simulator.placeRobot(10);
        simulator.placeStore(5, 50);
        simulator.placeStore(8, 15);
        assertTrue(simulator.getStores().get(4).isFull());
        assertTrue(simulator.getStores().get(7).isFull());
        try{
            simulator.moveRobot(3, 2);
            assertTrue(simulator.getRobots().get(0).getActualLocation() == 5);
            assertFalse(simulator.getStores().get(4).isFull());
            simulator.moveRobot(7, 1);
            assertTrue(simulator.getRobots().get(1).getActualLocation() == 8);
            assertFalse(simulator.getStores().get(7).isFull());
            simulator.moveRobot(10, -2);
        }catch(InterruptedException e){}
        assertTrue(simulator.profit() == 60);
    }
    
    @Test
    public void shouldStores(){
        SilkRoad simulator = new SilkRoad(20);
        int[][] answer = {{3, 15}, {7, 1}, {16, 1000}, {20, 20}};
        simulator.placeStore(16, 1000);
        simulator.placeStore(20, 20);
        simulator.placeStore(7, 1);
        simulator.placeStore(3, 15);
        assertArrayEquals(answer, simulator.stores());
    }
    
    @Test
    public void shouldRobots(){
        SilkRoad simulator = new SilkRoad(25);
        int[][] answer = {{4, 0}, {15, -4}, {15, 4}, {22 ,3}};
        simulator.placeStore(10, 100);
        simulator.placeStore(15, 10);
        simulator.placeStore(22, 14);
        simulator.placeRobot(4);
        simulator.placeRobot(11);
        simulator.placeRobot(11);
        simulator.placeRobot(21);
        try{
            simulator.moveRobot(11, 11);
            simulator.moveRobot(21, -6);
            simulator.moveRobot(11, 4);
        }catch(InterruptedException e){}
        assertArrayEquals(answer, simulator.robots());
    }
    
    //Pruebas métodos ciclo 2
    @Test
    public void shouldSilkRoad(){
        int[][] elements = {{1, 20, 0}, {2, 15, 15}, {2, 40, 50}, {1, 50, 0}, {2, 80, 20}, {2, 70, 30}};
        SilkRoad simulator = new SilkRoad(10, elements);
        Road testRoad = new Road(80);
        assertTrue(simulator.getLength() == 80);
        assertTrue(simulator.getTotalProfit() == 0);
        assertFalse(simulator.isVisible());
        assertTrue(simulator.getStores().size() == 80);
        assertTrue(simulator.getRobots().size() == 0);
        assertTrue(testRoad.equals(simulator.getRoad()));
        assertTrue(simulator.getDays() == 10);
        assertTrue(simulator.getActualDay() == 0);
    }
    
    @Test
    public void shouldMoveRobots(){
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
        simulator.moveRobots();
        assertTrue(simulator.ok());
        ArrayList<Robot> robots = simulator.getRobots();
        assertTrue(robots.get(0).getActualLocation()==9);
        assertTrue(robots.get(0).getProfit()==0);
        assertTrue(robots.get(1).getActualLocation()==17);
        assertTrue(robots.get(1).getProfit()==7);
        assertTrue(robots.get(2).getActualLocation()==5);
        assertTrue(robots.get(2).getProfit()==0);
        assertTrue(robots.get(3).getActualLocation()==15);
        assertTrue(robots.get(3).getProfit()==39);
        assertTrue(robots.get(4).getActualLocation()==4);
        assertTrue(robots.get(4).getProfit()==12);
    }
    
    @Test
    public void shouldMoveRobots2(){
        SilkRoad simulator = new SilkRoad(20);
        simulator.placeStore(5, 10);
        simulator.placeStore(20, 9);
        simulator.placeStore(15, 3);
        simulator.placeRobot(13);
        simulator.placeRobot(7);
        simulator.moveRobots();
        assertTrue(simulator.ok());
        ArrayList<Robot> robots = simulator.getRobots();
        assertTrue(robots.get(0).getActualLocation()==15);
        assertTrue(robots.get(0).getProfit()==1);
        assertTrue(robots.get(1).getActualLocation()==5);
        assertTrue(robots.get(1).getProfit()==8);
    }
    
    @Test
    public void shouldNotMoveRobots(){
        SilkRoad simulator = new SilkRoad(20);
        simulator.moveRobots();
        assertFalse(simulator.ok());
        simulator.placeRobot(12);
        assertTrue(simulator.ok());
        simulator.moveRobots();
        assertFalse(simulator.ok());
    }
    
    @Test
    public void shouldEmptiedStores(){
        SilkRoad simulator = new SilkRoad(20);
        int[][] answer = {{5, 4}, {11, 1}, {15, 2}};
        simulator.placeStore(15, 8);
        simulator.placeStore(5, 23);
        simulator.placeStore(11, 14);
        simulator.placeRobot(12);
        try{
            simulator.moveRobot(12,-1);
            simulator.moveRobot(11, 4);
            simulator.moveRobot(15, -10);
        }catch(InterruptedException e){}
        simulator.reboot();
        try{
            simulator.moveRobot(12, 3);
            simulator.moveRobot(15, -10);
        }catch(InterruptedException e){}
        simulator.reboot();
        try{
            simulator.moveRobot(12, -7);
        }catch(InterruptedException e){}
        simulator.reboot();
        try{
            simulator.moveRobot(12, -7);
        }catch(InterruptedException e){}
        
        assertArrayEquals(answer, simulator.emptiedStores());
    }
    
    @Test
    public void shouldNotEmptiedStores(){
        SilkRoad simulator = new SilkRoad(20);
        assertNull(simulator.emptiedStores());
    }
    
    @Test
    public void shouldProfitPerMove(){
        SilkRoad simulator = new SilkRoad(20);
        int[][] answer = {{3, 0, 0, 0, 0, 0}, {5, 3, 20, 0, 0, 0}, {13, -1, -1, -1, -1, -1}, {20, -6, 0, 0, 0, 0}};
        simulator.placeStore(15, 8);
        simulator.placeStore(5, 30);
        simulator.placeRobot(14);
        simulator.placeRobot(3);
        simulator.placeRobot(20);
        simulator.placeRobot(10);
        try{
            simulator.moveRobot(14, 6);
            simulator.moveRobot(20, -5);
            simulator.moveRobot(10, -1);
            simulator.moveRobot(9, 1);
            simulator.moveRobot(10, 1);
            simulator.moveRobot(11, 1);
            simulator.moveRobot(12, 1);
            simulator.moveRobot(15, -10);
        }catch(InterruptedException e){}
        assertArrayEquals(answer, simulator.profitPerMove());
    }
    
    @Test
    public void shouldNotProfitPerMove(){
        SilkRoad simulator = new SilkRoad(20);
        assertNull(simulator.profitPerMove());
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown(){
    }
}