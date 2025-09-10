import java.util.*;

/**
 * DOPO versión of the silk road problem with a graphical representation.
 * 
 * @author Paula Alejandra Díaz
 * @author Juan Pablo Vélez
 * @version 1
 */
public class SilkRoad{
    // Attributes
    private final int length;
    private int totalProfit;
    private final int days;
    private int actualDay;
    private boolean isVisible;
    private boolean lastDone;
    private TreeMap<Integer, Store> stores;
    private ArrayList<Robot> robots;
    private Road road;
    
    // Methods
    public SilkRoad(int length){
        //days y actual days los dejaremos para después.
        days = 0;
        actualDay = 0;
        //para después.
        
        this.length = length;
        totalProfit = 0;
        isVisible = false;
        lastDone = true;
        stores = new TreeMap();
        robots = new ArrayList();
        //ajustar constructor
        road = new Road(length);
    }
    
    // Miniciclo1
    /**
     * adds a new store to the game if its posible
     * @param int location
     * @param int tenges
     * @return void
     */
    public void placeStore(int location, int tenges){
        if(!stores.containsKey(Integer.valueOf(location))){
            Store aux = new Store(location, tenges);
            stores.put(Integer.valueOf(location), aux);
            if(isVisible)aux.makeVisible();
            lastDone = true;
        }else{
            lastDone = false;
        }
    }
    
    /**
     * deletes a store from the game if its posible
     * @param int location
     * @return void
     */
    public void removeStore(int location){
        if(stores.containsKey(Integer.valueOf(location))){
            if(isVisible)stores.get(Integer.valueOf(location)).makeInvisible();
            stores.remove(Integer.valueOf(location));
            lastDone = true;
        }else{
            lastDone = false;
        }
    }
    
    // Miniciclo2
    /**
     * adds a new robot to the game if its posible
     * @param int location
     * @return void
     */
    public void placeRobot(int location){
        if(searchRobot(location) == null){
            Robot aux = new Robot(location);
            robots.add(aux);
            if(isVisible)aux.makeVisible();
            lastDone = true;
        }else{
            lastDone = false;
        }
    }
    
    private Robot searchRobot(int location){
        //no olvidar de codificar
        //no olvidar de codificar
        //no olvidar de codificar
        //no olvidar de codificar
        //no olvidar de codificar
        return null;
    }
    
    /**
     * deletes a robot from the game if its posible
     * @param int location
     * @return void
     */
    public void removeRobot(int location){
        Robot thereIs = searchRobot(location);
        if(thereIs != null){
            if(isVisible)thereIs.makeInvisible();
            robots.remove(thereIs);
            lastDone = true;
        }else{
            lastDone = false;
        }
    }
    
    // Miniciclo3
    /**
     * moves a robot from a given location a certain amount of meters
     * @param int location
     * @param int meters
     * @return void
     */
    public void moveRobot(int location, int meters){
        Robot r = searchRobot(location);
        if(r != null && road.in(location+meters)){
            if(isVisible)r.makeInvisible();
            r.move(meters, road);
            if(isVisible)r.makeVisible();
            lastDone = true;
        }else{
            lastDone = false;
        }
    }
    
    /**
     * resupply all stores stashes
     * @return void
     */
    public void resupplyStores(){
        if(!stores.isEmpty()){
            for(Integer key: stores.keySet()){
                stores.get(key).resupply();
            }
            lastDone = true;
        }else{
            lastDone = false;
        }
    }
    
    /**
     * returns all robots to their initial location
     * @return void
     */
    public void returnRobots(){
        if(!robots.isEmpty()){
            for(Robot r : robots){
                r.moveInitial(road);
            }
            lastDone = true;
        }else{
            lastDone = false;
        }
    }
    
    /**
     * set all stores to full and the robots to their initial location
     * @return void
     */
    public void reboot(){
        resupplyStores();
        returnRobots();
    }
    
    // Miniciclo4
    /**
     * Answers the total profit
     * @return int
     */
    public int profit(){
        return totalProfit;
    }
    
    /**
     * gives a list of stores by their location and stashes
     * @return int[][]
     */
    public int[][] stores(){
        int[][] answer = new int[stores.size()][2];
        int i = 0;
        for(Integer location : stores.keySet()){
            answer[i][0] = location;
            answer[i][1] = stores.get(location).getStash();
            i++;
        }
        return answer;
    }
    
    /**
     * gives a list of robots ordered by their location and profit
     * @return in[][]
     */
    public int[][] robots(){
        int[][] answer = new int[robots.size()][2];
        int i = 0;
        for(Robot r : robots){
            answer[i][0] = r.getActualLocation();
            answer[i][1] = r.getProfit();
            i++;
        }
        orderRobots(answer);
        return answer;
    }
    
    private int[][] orderRobots(int[][] robots){
        int[][] ordered = new int[robots.length][2];
        //terminar logica
        return ordered;
    }
    
    // Miniciclo5
    /**
     * makes the game visible in the canvas
     * @return void
     */
    public void makeVisible(){
        road.makeVisible();
        
        for(Integer location : stores.keySet()){
            stores.get(location).makeVisible();
        }
        
        for(Robot r : robots){
            r.makeVisible();
        }
        isVisible = true;
    }
    
    /**
     * makes the game invisible in the canvas
     * @return void
     */
    public void makeInvisible(){
        road.makeInvisible();
        
        for(Integer location : stores.keySet()){
            stores.get(location).makeInvisible();
        }
        
        for(Robot r : robots){
            r.makeInvisible();
        }
        isVisible = false;
    }
    
    // Miniciclo6
    /**
     * ends the game
     * @return void
     */
    public void finish(){
        System.exit(0);
    }
    
    /**
     * answers if the last operation was made
     * @return boolean
     */
    public boolean ok(){
        return lastDone;
    }
}
