import java.util.*;

/**
 * Is the class in charge of the graphical representation of the road in the silk orad problem.
 * 
 * @author Paula Alejandra Díaz
 * @author Juan Pablo Vélez
 * @version 3
 */
public class Road{
    //Atributes
    private boolean isVisible;
    private ArrayList<Block> blocks;
    
    //Atributes nedeed for construction
    private int[] verticalLimits = {60, 900};
    private int[] horizontalLimits = {0, 1500};
    private boolean isHorizontal;
    private int actualX = 0;
    private int actualY = 20;
    private int direction = 1;
    
    //Methods
    /**
     * Creates a Road object by its lenght
     * @param int length
     * @return Road
     */
    public Road(int length){
        isVisible = false;
        blocks = new ArrayList();
        isHorizontal = true;
        
        for(int i = 0; i < length; i++){
            int[] coordinates = determineCoordinates(isHorizontal);
            Block newBlock = new Block(isHorizontal, coordinates[0], coordinates[1], i+1);
            blocks.add(newBlock);
        }
    }
    
    /**
     * Determines coordinates of the block knowing if its horizontal or vertical. Also it is an accumulation process
     * @param boolean isHorizontal
     * @return int[]
     */
    private int[] determineCoordinates(boolean isHorizontal){
        int[] coordinates = new int[2];
        if(isHorizontal){
            if(direction == 1 && actualX+45 > horizontalLimits[1]){
                horizontalLimits[1] -= 50;
                this.isHorizontal = !isHorizontal;
                actualY += 35;
                actualX -= 40;
                return determineCoordinates(this.isHorizontal);
            }else if(direction == -1 && actualX < horizontalLimits[0]){
                horizontalLimits[0] = actualX+55;
                this.isHorizontal = !isHorizontal;
                actualY -= 45;
                actualX += 45;
                return determineCoordinates(this.isHorizontal);
            }else{
                coordinates[0] = actualX;
                coordinates[1] = actualY;
                if(direction == 1) actualX += 45;
                if(direction == -1) actualX -= 45;
            }
        }
        
        if(!isHorizontal){
            if(direction == 1 && actualY+45 > verticalLimits[1]){
                direction = -1;
                verticalLimits[1] = actualY-30;
                this.isHorizontal = !isHorizontal;
                actualX -= 45;
                actualY -= 35 ;
                return determineCoordinates(this.isHorizontal);
            }else if(direction == -1 && actualY < verticalLimits[0]){
                direction = 1;
                verticalLimits[0] = actualY+65;
                this.isHorizontal = !isHorizontal;
                actualX += 40;
                actualY += 45;
                return determineCoordinates(this.isHorizontal);
            }else{
                coordinates[0] = actualX;
                coordinates[1] = actualY;
                if(direction == 1) actualY += 45;
                if(direction == -1)actualY -= 45;
            }
        }
        return coordinates;
    }
    
    /**
     * Places a store in the given location with the tenges given
     * @param int location
     * @param int tenges
     * @return Store
     */
    public Store placeStore(int location, int tenges){
        Block block = blocks.get(location-1);
        Store store = new Store(location, tenges, block);
        block.setStore(store);
        return store;
    }
    
    /**
     * Removes a store in the given location
     * @param int location
     */
    public void removeStore(int location){
        Store store = blocks.get(location-1).getStore();
        store.setBlock(null);
        blocks.get(location-1).setStore(null);
    }
    
    /**
     * Places a store in the road in the given location
     * @param int location
     * @return Robot
     */
    public Robot placeRobot(int location){
        Block block = blocks.get(location-1);
        return block.placeRobot(location);
    }
    
    /**
     * Removes a robot in the given position. Also answers if the action was done
     * @param int location
     * @return Robot
     */
    public Robot removeRobot(int location){
        return blocks.get(location-1).removeRobot();
    }
    
    /**
     * Moves a robot froma given position the meter given (if posible). And answers if it was done
     * @param int location
     * @param int meters
     * @return boolean
     */
    public boolean moveRobot(int location, int meters) throws InterruptedException{
        Robot robot = blocks.get(location-1).getRobot();
        if(robot != null && (0 <= location+meters-1 && location+meters-1 < blocks.size())){
            int direction = (meters > 0) ? +1 : -1;
            blocks.get(location-1).deleteRobot(robot);
            int move = location-1;
            int steps = Math.abs(meters);
            while(steps > 0){
                move += direction;
                robot.teleportToBlock(blocks.get(move));
                if(isVisible){
                    blocks.get(move +(-1*direction)).makeRobotsVisible();
                    robot.makeVisible();
                }
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException e){
                    //no pasa nada, es necesario manejar la excepcion para poder dar un tiempo entre saltos de bloques
                }
                steps --;
            }
            
            blocks.get(move).addRobot(robot);
            robot.steelStore(meters);
            return true;
        }
        return false;
    }
    
    /**
     * Answers if a location is inside the road
     * @param int location
     * @return boolean
     */
    public boolean in(int location){
        if(1 <= location && location <= blocks.size()) return true;
        return false;
    }
    
    /**
     * makes the road visible
     * @return void
     */
    public void makeVisible(){
        isVisible = true;
        for(Block b : blocks){
            b.makeVisible();
        }
    }
    
    /**
     * makes the road invisible
     * @return void
     */
    public void makeInvisible(){
        isVisible = false;
        for(Block b : blocks){
            b.makeInvisible();
        }
    }
    
    //Extras
    /**
     * Answers if the object is equal
     * @param Road object
     * @return boolean
     */
    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (!(object instanceof Road)) return false;
        Road road = (Road) object;
        if(this.isVisible() != road.isVisible()) return false;
        ArrayList<Block> blocks1 = this.getBlocks();
        ArrayList<Block> blocks2 = road.getBlocks();
        if(blocks1.size() != blocks2.size())return false;
        int numBlocks = blocks1.size();
        for(int i = 0; i < numBlocks; i++){
            if(!blocks1.get(i).equals(blocks2.get(i)))return false;
        }
        return true;
    }
    
    /**
     * Answers if the road is visible
     * @return boolean
     */
    public boolean isVisible(){
        return isVisible;
    }
    
    /**
     * Gets the block of the road
     * @return ArrayList<Block>
     */
    public ArrayList<Block> getBlocks(){
        return blocks;
    }
}