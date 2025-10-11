import java.util.ArrayList;

/**
 * Is the class in charge of the graphical representation of the robot and its behaviour.
 * 
 * @author Paula Alejandra Díaz
 * @author Juan Pablo Vélez
 * @version 3
 */
public class Robot{
    // Attributes
    private final int initialLocation;
    private final Block initialBlock;
    private int actualLocation;
    private Block block;
    private int profit;
    private boolean isVisible;
    private Rectangle head;
    private Rectangle body;
    private ArrayList<Integer> profitsPerMove; //Ciclo 2
    
    //Methods
    /**
     * Constructor for objects of class Robot
     * @param int location
     * @param Block block
     * @return Robot
     */
    public Robot(int location, Block block){
        initialLocation = location;
        actualLocation = location;
        this.block = block;
        profit = 0;
        isVisible = false;
        int[] coordinates = block.getCoordinates();
        initialBlock = block;
        profitsPerMove = new ArrayList<>(); //Ciclo 2
        if(block.isHorizontal()){
            head = new Rectangle(7, 7, coordinates[0]+5, coordinates[1]+7, UtilColors.getColor(UtilColors.awtColors.get(33)));
            body = new Rectangle(10, 7, coordinates[0]+5, (coordinates[1]+7)+7);
        }else if(!block.isHorizontal()){
            head = new Rectangle(7, 7, coordinates[0]+5, coordinates[1]+20, UtilColors.getColor(UtilColors.awtColors.get(33)));
            body = new Rectangle(10, 7, coordinates[0]+5, (coordinates[1]+7)+20);
        }
    }
    
    /**
     * Makes the robot visible
     * @return void
     */
    public void makeVisible(){
        isVisible = true;
        head.makeVisible();
        body.makeVisible();
    }
    
    /**
     * Makes the robot invisible
     * @return void
     */
    public void makeInvisible(){
        isVisible = false;
        head.makeInvisible();
        body.makeInvisible();
    }
    
    /**
     * Moves the robot to another location some meters
     * @param Black block
     */
    public void teleportToBlock(Block block){
        actualLocation = block.getLocation();
        this.block = block;
        int[] coordinates = block.getCoordinates();
        if(block.isHorizontal()){
            head.teleport(coordinates[0]+5, coordinates[1]+7);
            body.teleport(coordinates[0]+5, (coordinates[1]+7)+7);
        }else if(!block.isHorizontal()){
            head.teleport(coordinates[0]+5, coordinates[1]+20);
            body.teleport(coordinates[0]+5, (coordinates[1]+7)+20);
        }
    }
    
    /**
     * Steels the store in the block if it is posible
     * @param int meters
     */
    public void steelStore(int meters){
        Store store = block.getStore();
        if(store != null && store.isFull()){
            profit += store.getStash() - Math.abs(meters);
            store.setFull(false);
            store.wasStolen();
            store.changeColor(false);
            if(isVisible)store.makeVisible();//Ciclo 2
            profitsPerMove.add(Integer.valueOf(store.getStash() - Math.abs(meters))); //Ciclo 2
        }else if(store == null || (store != null && !store.isFull())){
            profit -= Math.abs(meters);
            profitsPerMove.add(Integer.valueOf(- Math.abs(meters))); //Ciclo 2
        }
    }
    
    /**
     * Moves the robot to the itss initial location
     * @return void
     */
    public void moveInitial(){
        teleportToBlock(initialBlock);
        initialBlock.addRobot(this);
    }
    
    /**
     * Gives th actual location of the robot in the road
     * @return int
     */
    public int getActualLocation(){
        return actualLocation;
    }
    
    /**
     * Gives the total profit the robot has gained
     * @return int
     */
    public int getProfit(){
        return profit;
    }
    
    /**
     * Sets the block where the robot is in the road
     * @param Block block
     */
    public void setBlock(Block block){
        this.block = block;
    }
    
    //Ciclo 2
    /**
     * Gives the list of profits gained by each move
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getProfitsPerMove(){
        return profitsPerMove;
    }
}
