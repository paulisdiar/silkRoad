import java.util.ArrayList;

/**
 * Write a description of class Block here.
 * 
 * @author Paula Díaz
 * @author Juan Pablo Vélez
 * @version 4
 */
public class Block{
    //Atributes
    private Rectangle sideWalk;
    private Rectangle street;
    private Rectangle mark;
    private final boolean isHorizontal;
    private boolean isVisible;
    private boolean hasStore;
    private final int xPosition;
    private final int yPosition;
    private ArrayList<Robot> robots;
    
    //Methods
    /**
     * Constructor for objects of class Block
     * @param boolean is Horizontal
     * @param int xPos
     * @param int yPos
     * @return Block
     */
    public Block(boolean isHorizontal, int xPos, int yPos){
        this.isHorizontal = isHorizontal;
        xPosition = xPos;
        yPosition = yPos;
        isVisible = false;
        hasStore = false;
        robots = new ArrayList<Robot>();
        if(isHorizontal == true){
            sideWalk = new Rectangle(5, 45, xPos, yPos, UtilColors.getColor(UtilColors.awtColors.get(35)));
            street = new Rectangle(30, 45, xPos, yPos+5, UtilColors.getColor(UtilColors.awtColors.get(34)));
            mark = new Rectangle(2, 10, xPos+18, yPos+18, UtilColors.getColor(UtilColors.awtColors.get(9)));
        }else if(isHorizontal == false){
            sideWalk = new Rectangle(45, 15, xPos+25, yPos, UtilColors.getColor(UtilColors.awtColors.get(35)));
            street = new Rectangle(45, 25, xPos, yPos, UtilColors.getColor(UtilColors.awtColors.get(34)));
            mark = new Rectangle(8, 2, xPos+12, yPos+18, UtilColors.getColor(UtilColors.awtColors.get(9)));
        }
    }
    
    /**
     * Makes the block visible in the canvas
     */
    public void makeVisible(){
        sideWalk.makeVisible();
        street.makeVisible();
        mark.makeVisible();
        isVisible = true;
    }
    
    /**
     * Makes the block invisible in the canvas
     */
    public void makeInvisible(){
        sideWalk.makeInvisible();
        street.makeInvisible();
        mark.makeInvisible();
        isVisible = false;
    }
    
    /**
     * Gives the xPosition of the bolck
     * @return int
     */
    public int getXPosition(){
        return xPosition;
    }
    
    /**
     * Gives the yPostion of the block
     * @return int
     */
    public int getYPosition(){
        return yPosition;
    }
    
    /**
     * Answers if the block is horizontal
     * @return boolean
     */
    public boolean isHorizontal(){
        return isHorizontal;
    }
    
    /**
     * Answers if the block has a store
     * @return boolean
     */
    public boolean hasStore(){
        return hasStore;
    }
    
    /**
     * Set the value of the atribute hasStore
     * @param has
     */
    public void setHasStore(boolean has){
        hasStore = has;
    }
    
    /**
     * Adds a robot to the list of robots that are in the block
     * @param Robot robot
     */
    public void addRobot(Robot robot){
        robots.add(robot);
    }
    
    /**
     * Answers if there are robots in the block
     * @return boolean
     */
    public boolean hasRobots(){
        return (robots.size() > 0) ? true : false;
    }
    
    /**
     * Gives the first robot in the list of robots that are in the block
     * @return Robot
     */
    public Robot getFirstRobot(){
        return robots.get(0);
    }
    
    /**
     * Gives the last robot that arrived to the block
     * @return Robot
     */
    public Robot getLastRobot(){
        return robots.get(robots.size()-1);
    }
    
    /**
     * Removes the given robot from the list of robots that are int he block
     * @param Robot robot
     */
    public void removeRobot(Robot robot){
        robots.remove(robot);
    }
    
    /**
     * Gives the list of robots in the block
     * @return ArrayList<Robot>
     */
    public ArrayList<Robot> getRobots(){
        return robots;
    }
}