import java.util.ArrayList;

/**
 * Write a description of class Block here.
 * 
 * @author Paula Díaz
 * @author Juan Pablo Vélez
 * @version 3
 */
public class Block{
    //Atributes
    private Rectangle sideWalk;
    private Rectangle street;
    private Rectangle mark;
    private Store store;
    private ArrayList<Robot> robots;
    private final boolean isHorizontal;
    private boolean isVisible;
    private final int xPosition;
    private final int yPosition;
    private final int location;
    
    //Methods
    /**
     * Constructor for objects of class Block
     * @param boolean is Horizontal
     * @param int xPos
     * @param int yPos
     * @param int location
     * @return Block
     */
    public Block(boolean isHorizontal, int xPos, int yPos, int location){
        store = null;
        this.isHorizontal = isHorizontal;
        xPosition = xPos;
        yPosition = yPos;
        isVisible = false;
        robots = new ArrayList();
        this.location = location;
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
     * Create a new bot and sets it in the canvas
     * @param int location
     * @return Robot
     */
    public Robot placeRobot(int location){
        Robot robot = new Robot(location, this);
        robots.add(robot);
        return robot;
    }
    
    /**
     * Removes a robot if posible. Also if not posible returns null
     * @return Robot
     */
    public Robot removeRobot(){
        if(robots.size() > 0){
            Robot robot = robots.get(0);
            if(isVisible) robot.makeInvisible();
            robot.setBlock(null);
            robots.remove(0);
            return robot;
        }
        return null;
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
     * Gives the coordinates in the canvas of the block
     * @return int[]
     */
    public int[] getCoordinates(){
        int[] coordinates = new int[2];
        coordinates[0] = xPosition;
        coordinates[1] = yPosition;
        return coordinates;
    }
    
    /**
     * Set the store of the block
     * @param Store store
     */
    public void setStore(Store store){
        this.store = store;
    }
    
    /**
     * Answers if the block is horzontal
     * @return boolean
     */
    public boolean isHorizontal(){
        return isHorizontal;
    }
    
    /**
     * Gives the store of the block
     * @return Store
     */
    public Store getStore(){
        return store;
    }
    
    /**
     * Gives the first robot in the block
     * @return Robot
     */
    public Robot getRobot(){
        if(robots.size() > 0) return robots.get(0);
        return null;
    }
    
    /**
     * Gives the location of the block in the road
     * @return int
     */
    public int getLocation(){
        return location;
    }
    
    /**
     * Removes the given robot
     * @param Robot robot
     */
    public void deleteRobot(Robot robot){
        robots.remove(robot);
    }
    
    /**
     * Adds the Robot to the block
     * @param Robot robot
     */
    public void addRobot(Robot robot){
        robots.add(robot);
    }
    
    /**
     * Answers if the blocks are equal
     * @param Object object
     * @return boolean
     */
    @Override
    public boolean equals(Object object){
        if(this == object)return true;
        if (!(object instanceof Block)) return false;
        Block block = (Block) object;
        if(!this.getSideWalk().equals(block.getSideWalk()))return false;
        if(!this.getMark().equals(block.getMark()))return false;
        if(!this.getStreet().equals(block.getStreet()))return false;
        if(this.getLocation()!=block.getLocation() || this.isHorizontal()!=block.isHorizontal() || this.getXPosition()!=block.getXPosition() ||
        this.getYPosition()!=block.getYPosition())return false;
        return true;
    }
    
    /**
     * Gets the sideWalk of the block
     * @return Rectangle
     */
    public Rectangle getSideWalk(){
        return sideWalk;
    }
    
    /**
     * Gets the mark of the block
     * @return Rectangle
     */
    public Rectangle getMark(){
        return mark;
    }
    
    /**
     * Gets the street of the block
     * @return Rectangle
     */
    public Rectangle getStreet(){
        return street;
    }
    
    /**
     * Gives the list of robot
     * @return ArrayList<Robot>
     */
    public ArrayList<Robot> getRobots(){
        return robots;
    }
    
    /**
     * Answeres if the road is visible
     * @return boolean
     */
    public boolean isVisible(){
        return isVisible;
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
     * Makes its robots visible
     */
    public void makeRobotsVisible(){
        for(Robot r : robots){
            r.makeVisible();
        }
    }
}