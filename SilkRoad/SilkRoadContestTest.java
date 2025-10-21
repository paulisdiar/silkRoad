import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SilkRoadContestTest.
 *
 * @author  Paula Díaz
 * @author Juan Pablo Vélez Muñoz
 * @version 4
 */
public class SilkRoadContestTest{
    /**
     * Default constructor for test class SilkRoadContestTest
     */
    public SilkRoadContestTest(){}

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp(){}

    @Test 
    public void shouldSolve(){
        //Given
        int[][] days = {{2,3,10}, {1,15,0}, {2,20,40}, {1,10,0}};
        int[] answer = {0,-2,19,57};
        //When
        int[] solution = SilkRoadContest.solve(days);
        //Then
        assertArrayEquals(answer, solution);
    }
    
    @Test 
    public void shouldSolve2(){
        //Given
        int[][] days = {{1,8,0}, {1,15,0}, {2,10,40}, {2,4,30}, {2,1,10}};
        int[] answer = {0,0,38,100,169};
        //When
        int[] solution = SilkRoadContest.solve(days);
        //Then
        assertArrayEquals(answer, solution);
    }
    
    @Test 
    public void shouldSolve3(){
        //Given
        int[][] days = {{2,11,5}, {1,10,0}, {2,20,30}, {1,14,0}, {1,2,0}, {2,16,10}, {1,20,0}};
        int[] answer = {0,4,29,57,85,119,161};
        //When
        int[] solution = SilkRoadContest.solve(days);
        //Then
        assertArrayEquals(answer, solution);
    }
    
    @Test 
    public void shouldSolve4(){
        //Given
        int[][] days = {{2,11,5}, {1,10,0}, {2,20,30}, {1,14,0}, {1,2,0}, {2,16,10}, {1,20,0}, {2,9,50}, {1,4,0}, {2,21,3}, {1,1,0}, {1,13,0}, {2,7,111}, {1,25,0}};
        int[] answer = {0,4,29,57,85,119,161,251,341,433,525,617,818,1019};
        //When
        int[] solution = SilkRoadContest.solve(days);
        //Then
        assertArrayEquals(answer, solution);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown(){}
    
    
    
}