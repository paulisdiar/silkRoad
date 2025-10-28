import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SilkRoadC4Test.
 *
 * @author  Juan Pablo Vélez
 * @author Paula Díaz
 * @version 4
 */
public class SilkRoadC4Test{
    /**
     * Default constructor for test class SilkRoadC4Test
     */
    public SilkRoadC4Test(){}

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp(){}
    
    @Test
    public void shouldFighter(){
        //Given
        SilkRoad s = new SilkRoad(20);
        //When
        s.placeStore("fighter", 10, 50);
        //Then
        Store t = s.getStores().get(0);
        assertTrue(s.getRoad().get(9).hasStore());
        assertTrue(t instanceof Fighter);
        assertTrue(t.getLocation() == 10);
        assertTrue(t.getStash() == 50);
        assertTrue(t.getTimesStolen() == 0);
        assertTrue(t.getLeft() == 50); 
    }
    
    @Test
    public void shouldNotFighter(){
        //Given
        SilkRoad s = new SilkRoad(20);
        //When
        s.placeStore("fighter", 40, 100);
        s.placeStore("fighter", 5, -20);
        //Then
        assertTrue(s.getStores().size() == 0);
    }
    
    @Test
    public void shouldAutonomous(){
        //Given
        SilkRoad s = new SilkRoad(20);
        //When
        s.placeStore("autonomous", 1, 12);
        //Then
        Store t = s.getStores().get(0);
        int location = t.getLocation();
        assertTrue(s.getRoad().get(location-1).hasStore());
        assertTrue(t instanceof Autonomous);
        assertTrue(t.getStash() == 12);
        assertTrue(t.getTimesStolen() == 0);
        assertTrue(t.getLeft() == 12);
    }
    
    @Test
    public void shouldNotAutonomous(){
        //Given
        SilkRoad s = new SilkRoad(20);
        //When
        s.placeStore("autonomous", 5, -1000);
        //Then
        assertTrue(s.getStores().size() == 0);
    }
    
    @Test
    public void shouldNeverBack(){
        //Given
        SilkRoad s = new SilkRoad(20);
        //When
        s.placeRobot("neverback", 10);
        //Then
        Robot r = s.getRobots().get(0);
        assertTrue(r instanceof NeverBack);
        assertTrue(s.getRoad().get(9).hasRobots());
        assertTrue(r.getInitialLocation() == 10);
        assertTrue(r.getActualLocation() == 10);
        assertTrue(r.getProfit() == 0);
        assertTrue(r.getProfitsPerMove().size() == 0);
    }
    
    @Test
    public void shouldNotNeverBack(){
        //Given
        SilkRoad s = new SilkRoad(10);
        //When
        s.placeRobot("neverback", 11);
        //Then
        assertTrue(s.getRobots().size() == 0);
    }
    
    @Test
    public void shouldTender(){
        //Given
        SilkRoad s = new SilkRoad(20);
        //When
        s.placeRobot("tender", 15);
        //Then
        Robot r = s.getRobots().get(0);
        assertTrue(r instanceof Tender);
        assertTrue(s.getRoad().get(14).hasRobots());
        assertTrue(r.getInitialLocation() == 15);
        assertTrue(r.getActualLocation() == 15);
        assertTrue(r.getProfit() == 0);
        assertTrue(r.getProfitsPerMove().size() == 0);
    }
    
    @Test
    public void shouldNotTender(){
        //Given
        SilkRoad s = new SilkRoad(10);
        //When
        s.placeRobot("tender", -1);
        //Then
        assertTrue(s.getRobots().size() == 0);
    }
    
    @Test
    public void shouldMoveRobot(){
        //Given
        SilkRoad s = new SilkRoad(15);
        s.placeRobot("neverback", 5);
        //When
        s.moveRobot(5, 7);
        s.moveRobot(12,-7);
        //Then
        Robot r = s.getRobots().get(0);
        assertFalse(s.ok());
        assertTrue(r.getActualLocation() == 12);
        assertTrue(r.getInitialLocation() == 5);
        assertTrue(r.getProfit() == -7);
        assertTrue(r.getProfitsPerMove().size() == 1);
        assertTrue(r.getProfitsPerMove().get(0) == -7);
        assertFalse(s.getRoad().get(4).hasRobots());
        assertTrue(s.getRoad().get(11).hasRobots());
    }
    
    @Test
    public void shouldMoveRobot2(){
        //Give
        SilkRoad s = new SilkRoad(20);
        s.placeStore("normal", 10, 100);
        s.placeRobot("tender", 5);
        s.placeRobot("tender", 15);
        //When
        s.moveRobot(5, 5);
        s.moveRobot(15, -5);
        //Then
        Robot r1 = s.getRobots().get(0);
        Robot r2 = s.getRobots().get(1);
        Store t = s.getStores().get(0);
        assertTrue(r1.getActualLocation() == 10);
        assertTrue(r1.getInitialLocation() == 5);
        assertTrue(r2.getActualLocation() == 10);
        assertTrue(r2.getInitialLocation() == 15);
        assertTrue(r1.getProfit() == 45);
        assertTrue(r1.getProfitsPerMove().get(0) == 45);
        assertTrue(r2.getProfit() == 20);
        assertTrue(r2.getProfitsPerMove().get(0) == 20);
        assertTrue(t.getTimesStolen() == 2);
        assertTrue(t.getLeft() == 25);
    }
    
    @Test
    public void shouldMoveRobot3(){
        //Given
        SilkRoad s = new SilkRoad(20);
        s.placeStore("fighter", 15, 100);
        s.placeRobot("normal", 10);
        //When
        s.moveRobot(10, 5);
        //Then
        assertTrue(s.getRobots().get(0).getProfit() == -5);
        assertTrue(s.getStores().get(0).getTimesStolen() == 0);
        assertTrue(s.getStores().get(0).getLeft() == 100);
    }
    
    @Test
    public void shouldMoveeRobot4(){
        //Given
        SilkRoad s = new SilkRoad(20);
        s.placeStore("fighter", 15, 100);
        s.placeStore("normal", 11, 110);
        s.placeRobot("normal", 10);
        s.moveRobot(10, 1);
        //When
        s.moveRobot(11, 4);
        //Then
        assertTrue(s.getRobots().get(0).getProfit() == 205);
        assertTrue(s.getStores().get(0).getTimesStolen() == 1);
        assertTrue(s.getStores().get(0).getLeft() == 0);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown(){}
}