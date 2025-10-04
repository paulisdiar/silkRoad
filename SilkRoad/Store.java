/**
 * Is the class in charge of the graphical representation and behaviour of the stores in the silk road problem.
 * 
 * @author Paula Alejandra Díaz
 * @author Juan Pablo Vélez
 * @version 3
 */
public class Store{
    // Attributes
    private final int location;
    private Block block;
    private final int stash;
    private boolean full;
    private boolean isVisible;
    private Rectangle facade;
    private Triangle roof;
    private int timesStolen; //Ciclo 2
    
    // Methods
    /**
     * Constructor for objects of class Store
     * @param int location
     * @param int tenges
     * @param Block block
     * @return Store
     */
    public Store(int location, int tenges, Block block){
        this.location = location;
        this.block = block;
        stash = tenges;
        full = true;
        isVisible = false;
        timesStolen = 0; //Ciclo 2
        int[] coordinates = this.block.getCoordinates();
        if(block.isHorizontal()){
            facade = new Rectangle(10, 10, coordinates[0]+20, (coordinates[1]-10)+5);
            roof = new Triangle(10, 10, (coordinates[0]+5)+20, (coordinates[1]-20)+5);
        }else if(!block.isHorizontal()){
            facade = new Rectangle(10, 10, coordinates[0]+27, (coordinates[1]-10)+35);
            roof = new Triangle(10, 10, (coordinates[0]+5)+27, (coordinates[1]-20)+35);
        }
    }
    
    /**
     * Makes the store visible
     * @return void
     */
    public void makeVisible(){
        isVisible = true;
        facade.makeVisible();
        roof.makeVisible();
    }
    
    /**
     * Makes the store invisible
     * @return void
     */
    public void makeInvisible(){
        isVisible = false;
        facade.makeInvisible();
        roof.makeInvisible();
    }
    
    /**
     * Gives the stash amount
     * @return int
     */
    public int getStash(){
        return stash;
    }
    
    /**
     * Sets the block where the store is
     * @param Block block
     */
    public void setBlock(Block block){
        this.block = block;
    }
    
    /**
     * Answers if the store is full of tenges
     * @return boolean
     */
    public boolean isFull(){
        return full;
    }
    
    /**
     * Sets if the store is full or not
     * @param boolean is
     */
    public void setFull(boolean is){
        full = is;
    }
    
    /**
     * Gives the location of the store
     * @retun int
     */
    public int getLocation(){
        return location;
    }
    
    //Ciclo 2
    /**
     * Answers if two stores are equal
     * @param Object object
     * @return boolean
     */
    @Override
    public boolean equals(Object object){
        if(this == object)return true;
        if(!(object instanceof Store))return false;
        Store store = (Store) object;
        if(location!=store.getLocation() || !block.equals(store.getBlock()) || stash!=store.getStash() || full!=store.isFull() ||
        !facade.equals(store.getFacade()) || !roof.equals(store.getRoof()))return false;
        return true;
    }
    
    /**
     * Gives the block where the store is
     * @return Block
     */
    public Block getBlock(){
        return block;
    }
    
    /**
     * Gives the facade of the store
     * @return Rectangle
     */
    public Rectangle getFacade(){
        return facade;
    }
    
    /**
     * Gives the roof of the store
     * @return Triangle
     */
    public Triangle getRoof(){
        return roof;
    }
    
    /**
     * Increses the times the store has been Stolen
     */
    public void wasStolen(){
        timesStolen ++;
    }
    
    /**
     * Gives the number of times the store has been stolen
     * @return int
     */
    public int getTimesStolen(){
        return timesStolen;
    }
    
    /**
     * Changes the color of the store based on if it is full or not
     * @param boolean isFull
     */
    public void changeColor(boolean isFull){
        if(isFull){
            facade.changeColor(facade.getMyColor());
            roof.changeColor(roof.getMyColor());
        }else{
            facade.changeColor(UtilColors.getColor(UtilColors.awtColors.get(33)));
            roof.changeColor(UtilColors.getColor(UtilColors.awtColors.get(33)));
        }
    }
}