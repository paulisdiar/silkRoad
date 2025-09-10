import java.util.*;

/**
 * Is the class in charge of the graphical representation of the road in the silk orad problem.
 * 
 * @author Paula Alejandra Díaz
 * @author Juan Pablo Vélez
 * @version 1
 */
public class Road{
    // Attributes
    private boolean isVisible;
    private ArrayList<Rectangle> blocks;
    
    //Methods
    public Road(int length){
        isVisible = false;
        blocks = new ArrayList();
        for(int i = length; 0 < i; i--)blocks.add(new Rectangle());
    }
    
    /**
     * answers if a location is inside the road
     * @param int location
     * @return boolean
     */
    public boolean in(int location){
        //termianr la logica
        return true;
    }
    
    /**
     * makes the road visible
     * @return void
     */
    public void makeVisible(){
        isVisible = true;
        for(Rectangle b : blocks){
            b.makeVisible();
        }
    }
    
    /**
     * makes the road invisible
     * @return void
     */
    public void makeInvisible(){
        isVisible = false;
        for(Rectangle b : blocks){
            b.makeInvisible();
        }
    }
    
    /**
     * gives coordinates of the position in the canvas of a certain location in the road
     * @param int location
     * @return int[]
     */
    public int[] giveCoordinates(int location){
        int[] answer = new int[2];
        //terminar logica
        return answer;
    }
}