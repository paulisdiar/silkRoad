/**
 * Is the class that defines the store in the simulation
 * 
 * @author Juan Pablo Vélez
 * @author Paula Díaz
 * @version 4
 */
public class Store{
    //Atributes
    protected Rectangle facade;
    protected Triangle roof;
    protected final int location;
    protected final int stash;
    protected int left;
    protected int timesStolen; //Ciclo 2
    protected boolean isVisible;
    
    //Methods
    /**
     * Constructor for objects of class Store
     * @param int location
     * @param int tenges
     * @param Block block
     */
    public Store(int location, int tenges, Block block){
        this.location = location;
        stash = tenges;
        timesStolen = 0; //Ciclo 2
        left = tenges;
        isVisible = false;
        int x = block.getXPosition();
        int y = block.getYPosition();
        boolean isH = block.isHorizontal();
        if(isH){
            facade = new Rectangle(10, 10, x+20, (y-10)+5);
            roof = new Triangle(10, 10, (x+5)+20, (y-20)+5);
        }else if(!isH){
            facade = new Rectangle(10, 10, x+27, (y-10)+35);
            roof = new Triangle(10, 10, (x+5)+27, (y-20)+35);
        }
    }
    
    /**
     * Activates the components of the store
     */
    public void makeVisible(){
        isVisible = true;
        roof.makeVisible();
        facade.makeVisible();
    }
    
    /**
     * Deactivates the components of the store
     */
    public void makeInvisible(){
        isVisible = false;
        roof.makeInvisible();
        facade.makeInvisible();
    }
    
    /**
     * Gives the location of the store
     * @return int
     */
    public int getLocation(){
        return location;
    }
    
    /**
     * Answers if the store is full
     * qreturn boolean
     */
    public boolean hasTenges(){
        return left > 0;
    }
    
    /**
     * Resets the store stash
     */
    public void setFull(){
        left = stash;
        changeColor(true);
        if(isVisible)makeVisible();
    }
    
    /**
     * Gives the stash amount the stores has
     * @return int
     */
    public int getStash(){
        return stash;
    }
    
    /**
     * Gives the amout of tenges that are left
     * @return int
     */
    public int getLeft(){
        return left;
    }
    
    /**
     * Gives the times the store has been stolen
     * @return int
     */
    public int getTimesStolen(){
        return timesStolen;
    }
    
    /**
     * Set the value of left atribute
     * @param int l
     */
    public void setLeft(int l){
        left = l;
    }
    
    /**
     * Increases the count of times stolen
     */
    public void stolen(){
        timesStolen++;
    }
    
    /**
     * Changes the color of the store depending on the tenges left
     */
    public void updateColor(){
        if(left == 0){
            changeColor(false);
        }else{
            changeColor(true);
        }
    }
    
    /**
     * changes the color of the store based on the atribute full
     * @param boolean full
     */
    private void changeColor(boolean full){
        if(full){
            facade.changeColor(facade.getMyColor());
            roof.changeColor(roof.getMyColor());
        }else{
            facade.changeColor(UtilColors.getColor(UtilColors.awtColors.get(33)));
            roof.changeColor(UtilColors.getColor(UtilColors.awtColors.get(33)));
        }
    }
}