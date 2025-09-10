/**
 * Is the class in charge of the graphical representation and behaviour of the stores in the silk road problem.
 * 
 * @author Paula Alejandra Díaz
 * @author Juan Pablo Vélez
 * @version 1
 */
public class Store{
    // Attributes
    private final int location;
    private final int stash;
    private boolean full;
    private boolean isVisible;
    private Rectangle facade;
    private Triangle roof;
    
    // Methods
    /**
     * Constructor for objects of class Store
     */
    public Store(int location, int tenges){
        this.location = location;
        stash = tenges;
        full = true;
        isVisible = false;
        //ajustar los constructores
        facade = new Rectangle();
        roof = new Triangle();
    }
    
    /**
     * makes the store visible
     * @return void
     */
    public void makeVisible(){
        isVisible = true;
        facade.makeVisible();
        roof.makeVisible();
    }
    
    /**
     * makes the store invisible
     * @return void
     */
    public void makeInvisible(){
        isVisible = false;
        facade.makeInvisible();
        roof.makeInvisible();
    }
    
    /**
     * reset the stash to the initial amount
     * @return void
     */
    public void resupply(){
        full = true;
    }
    
    /**
     * gives the stash amount
     * @return int
     */
    public int getStash(){
        return stash;
    }
}