import java.util.ArrayList;

/**
 * This is the class that defines the robot in the simulation
 * 
 * @author Paula Díaz
 * @author Juan Pablo Vélez
 * @version 4
 */
public class Robot{
    //Atributes
    protected Rectangle head;
    protected Rectangle body;
    protected final int initialLocation;
    protected int actualLocation;
    protected int profit;
    protected boolean isVisible;
    protected ArrayList<Integer> profitsPerMove;
    
    //Methods
    /**
     * Constructor for objects of class Robot
     * @param int inLocation
     * @param Block inBLock
     */
    public Robot(int inLocation, Block inBlock){
        initialLocation = inLocation;
        actualLocation = inLocation;
        profit = 0;
        isVisible = false;
        profitsPerMove = new ArrayList<Integer>();
        int x = inBlock.getXPosition();
        int y = inBlock.getYPosition();
        boolean isH = inBlock.isHorizontal();
        if(isH){
            head = new Rectangle(7, 7, x+5, y+7, UtilColors.getColor(UtilColors.awtColors.get(33)));
            body = new Rectangle(10, 7, x+5, (y+7)+7);
        }else if(!isH){
            head = new Rectangle(7, 7, x+5, y+20, UtilColors.getColor(UtilColors.awtColors.get(33)));
            body = new Rectangle(10, 7, x+5, (y+7)+20);
        }
    }
    
    /**
     * Activates the visual components of the robot
     */
    public void makeVisible(){
        isVisible = true;
        head.makeVisible();
        body.makeVisible();
    }
    
    /**
     * Activates the visual components of the robot
     */
    public void makeInvisible(){
        isVisible = false;
        head.makeInvisible();
        body.makeInvisible();
    }
    
    /**
     * Moves the robot to the given block
     * @param int location
     * @param Block block
     */
    public void teleportToBlock(int location, Block block){
        actualLocation = location;
        int x = block.getXPosition();
        int y = block.getYPosition();
        if(block.isHorizontal()){
            head.teleport(x+5, y+7);
            body.teleport(x+5, (y+7)+7);
        }else if(!block.isHorizontal()){
            head.teleport(x+5, y+20);
            body.teleport(x+5, (y+7)+20);
        }
    }
    
    /**
     * The robot has stolen some tenges // meters must be positive
     * @param int tenges
     * @param int meters
     */
    public void steelTenges(int tenges, int meters){
        profit += tenges-meters;
        profitsPerMove.add(Integer.valueOf(tenges-meters));
    }
    
    /**
     * Gives the initial location of the robot
     * @return int
     */
    public int getInitialLocation(){
        return initialLocation;
    }
    
    /**
     * Gives the actual location of the robot
     * @return int
     */
    public int getActualLocation(){
        return actualLocation;
    }
    
    /**
     * Gives the profit the robot has gained
     * @return int
     */
    public int getProfit(){
        return profit;
    }
    
    /**
     * Gives the list of profit gained by the robot each move
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getProfitsPerMove(){
        return profitsPerMove;
    }
}