/**
 * Is the class in charge of the graphical representation of the robot and its behaviour.
 * 
 * @author Paula Alejandra Díaz
 * @author Juan Pablo Vélez
 * @version 1
 */
public class Robot{
    // Attributes
    private final int initialLocation;
    private int actualLocation;
    private int profit;
    private boolean isVisible;
    private Rectangle head;
    private Rectangle body;
    
    // Methods
    /**
     * Constructor for objects of class Robot
     */
    public Robot(int location){
        initialLocation = location;
        actualLocation = location;
        profit = 0;
        isVisible = false;
        head = new Rectangle();
        body = new Rectangle();
    }
    
    /**
     * makes the robot visible
     * @return void
     */
    public void makeVisible(){
        isVisible = true;
        head.makeVisible();
        body.makeVisible();
    }
    
    /**
     * makes the robot invisible
     * @return void
     */
    public void makeInvisible(){
        isVisible = false;
        head.makeInvisible();
        body.makeInvisible();
    }
    
    /**
     * moves the robot to another location some meters
     * @param Road r
     * @param int meters
     * @return void
     */
    public void move(int meters, Road r){
        actualLocation += meters;
        int[] coordinates = r.giveCoordinates(actualLocation);
        //ajustar las medidas del cuerpo
        head.teleport(coordinates[0], coordinates[1]);
        body.teleport(coordinates[0], coordinates[1]);
    }
    
    /**
     * moves the robot to the itss initial location
     * @param Road r
     * @return void
     */
    public void moveInitial(Road r){
        move(0, r);
    }
    
    /**
     * gives th actual location of the robot in the road
     * @return int
     */
    public int getActualLocation(){
        return actualLocation;
    }
    
    /**
     * gives the total profit the robot has gained
     * @return int
     */
    public int getProfit(){
        return profit;
    }
}
