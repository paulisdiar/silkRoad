import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Initial project of DOPO. SilkRoad simulator.
 * 
 * @author Juan Pablo Vélez
 * @author Paula Díaz
 * @version 4
 */
public class SilkRoad{
    //Atributes
    private final int length;
    private final int days;
    private int actualDay;
    private boolean isVisible;
    private boolean lastDone;
    private ArrayList<Block> road;
    private ArrayList<Robot> robots;
    private ArrayList<Store> stores;
    private int[] dailyProfits;
    private int[][] elements;
    private Bar progressBar;
    
    //Ciclo 1
    //Miniciclo 1
    /**
     * Constructor for objects of class SilkRoad
     */
    public SilkRoad(int length){        
        this.length = Math.abs(length);
        isVisible = false;
        lastDone = true;
        stores = new ArrayList<Store>();
        robots = new ArrayList<Robot>();
        road = new ArrayList<Block>();
        for(int i = 0; i < this.length; i++){
            int[] coordinates = SRCalculator.determineCoordinates(SRCalculator.isHorizontal);
            Block newBlock = new Block(SRCalculator.isHorizontal(), coordinates[0], coordinates[1], i+1);
            road.add(newBlock);
        }
        progressBar = new Bar();
        
        //Los demás atributos no son necesarios, el único es days y actualDay por ser final y facilidad
        days = 0;
        actualDay = 0;
    }
    
    /**
     * Activates the visual components of the simulator
     */
    public void makeVisible(){
        isVisible = true;
        for(Block b : road){
            b.makeVisible();
        }
        
        for(Store s : stores){
            s.makeVisible();
        }
        
        for(Robot r : robots){
            r.makeVisible();
        }
        progressBar.makeVisible();
    }
    
    /**
     * Deactivates the visual components of the simulator
     */
    public void makeInvisible(){
        isVisible = true;
        for(Block b : road){
            b.makeInvisible();
        }
        
        for(Store s : stores){
            s.makeInvisible();
        }
        
        for(Robot r : robots){
            r.makeInvisible();
        }
        progressBar.makeInvisible();
    }
    
    //Miniciclo 2
    /**
     * Adds a store in the given location with the given tenges
     * @param int location
     * @param int tenges
     */
    public void placeStore(int location, int tenges){
        if((0 <= location-1 && location-1 < length) && tenges >= 0){
            Block b = road.get(location-1);
            if(!b.hasStore()){
                Store newStore = new Store(location, tenges, b);
                if(isVisible)newStore.makeVisible();
                stores.add(newStore);
                b.setHasStore(true);
                lastDone = true;
                progressBar.updateTotal(SRCalculator.determineTotal(stores));
                progressBar.updateProgress(profit());
            }else{
                lastDone = false;
                if(isVisible)JOptionPane.showMessageDialog(null, "Ya existe una tienda en la ubicación.");
            }
        }else{
            lastDone = false;
            if(isVisible){
                if(!(0 <= location-1 && location-1 < length)){
                    JOptionPane.showMessageDialog(null, "La ubicación no es válida");
                }else if(tenges < 0){
                    JOptionPane.showMessageDialog(null, "no puede haber una cantidad negativa de tenges.");
                }
            }
        }
    }
    
    /**
     * Removes a store in thegiven location
     * @param location
     */
    public void removeStore(int location){
        if(0 <= location-1 && location-1 < length){
            Block b = road.get(location-1);
            if(b.hasStore()){
                b.setHasStore(false);
                Store remove = findStore(location);
                if(isVisible)remove.makeInvisible();
                stores.remove(remove);
                lastDone = true;
                progressBar.updateTotal(SRCalculator.determineTotal(stores));
                progressBar.updateProgress(profit());
            }else{
                lastDone = false;
                if(isVisible)JOptionPane.showMessageDialog(null, "No hay tienda que eliminar.");
            }
        }else{
            lastDone = false;
            if(isVisible)JOptionPane.showMessageDialog(null, "Ubicación inválida");
        }
    }
    
    //Miniciclo 3
    /**
     * Adds a robot in the Given location
     * @int location
     */
    public void placeRobot(int location){
        if(0 <= location-1 && location-1 < length){
            Block b = road.get(location-1);
            Robot newRobot = new Robot(location, b);
            b.addRobot(newRobot);
            robots.add(newRobot);
            if(isVisible)newRobot.makeVisible();
            lastDone = true;
        }else{
            lastDone = false;
            if(isVisible)JOptionPane.showMessageDialog(null, "Ubicación inválida");
        }
    }
    
    /**
     * Removes a robot in the given location
     * @param int location
     */
    public void removeRobot(int location){
        if(0 <= location-1 && location-1 < length){
            Block b = road.get(location-1);
            if(b.hasRobots()){
                Robot remove = b.getFirstRobot();
                b.removeRobot(remove);
                robots.remove(remove);
                if(isVisible)remove.makeInvisible();
                lastDone = true;
            }else{
                lastDone = false;
                if(isVisible)JOptionPane.showMessageDialog(null, "No hay robot para borrar.");
            }
        }else{
            lastDone = false;
            if(isVisible)JOptionPane.showMessageDialog(null, "Ubicación inválida");
        }
    }
    
    //Miniciclo 4
    /**
     * Moves a robot some meter from the given location
     * @param int location
     * @param int meters
     */
    public void moveRobot(int location, int meters){
        if(0 <= location-1 && location-1 < length){
            Block b = road.get(location-1);
            if(b.hasRobots()){
                if(0 < location+meters && location+meters <= length){
                    int direction = (meters > 0) ? +1 : -1;
                    int steps = Math.abs(meters);
                    int position = location-1;
                    Robot mover = b.getFirstRobot();
                    b.removeRobot(mover);
                    while(steps > 0){
                        position += direction;
                        mover.teleportToBlock(position+1, road.get(position));
                        if(isVisible)mover.makeVisible();
                        try{
                            Thread.sleep(500);
                        }catch(InterruptedException e){}
                        if(isVisible)mover.makeInvisible();
                        steps --;
                    }
                    if(isVisible)mover.makeVisible();
                    Block finalB = road.get(position);
                    finalB.addRobot(mover);
                    Store s = findStore(position+1);
                    if(s != null && s.isFull()){
                        s.setFull(false);
                        mover.steelTenges(s.getStash(), Math.abs(meters));
                    }else if(s == null || !s.isFull()){
                        mover.steelTenges(0, Math.abs(meters));
                    }
                    progressBar.updateProgress(profit());
                }else{
                    lastDone = false;
                    if(isVisible)JOptionPane.showMessageDialog(null, "El movimiento supera los limites de la ruta.");
                }
            }else{
                lastDone = false;
                if(isVisible)JOptionPane.showMessageDialog(null, "No hay robot para mover.");
            }
        }else{
            lastDone = false;
            if(isVisible)JOptionPane.showMessageDialog(null, "Ubicación inválida");
        }
    }
    
    //Miniciclo 5
    /**
     * Set all stores full again
     */
    public void resupplyStores(){
        if(stores.size() > 0){
            for(Store s : stores){
                  if(!s.isFull())s.setFull(true);
            }
            lastDone = true;
        }else{
            lastDone = false;
            if(isVisible)JOptionPane.showMessageDialog(null, "No hay tiendas que reabastecer.");
        }
    }
    
    /**
     * Takes all robots back to their initial location
     */
    public void returnRobots(){
        if(robots.size() > 0){
            for(Robot r : robots){
                int inLocation = r.getInitialLocation();
                int acLocation = r.getActualLocation();
                if(inLocation != acLocation){
                    road.get(acLocation-1).removeRobot(r);
                    Block inBlock = road.get(inLocation-1);
                    r.teleportToBlock(inLocation, inBlock);
                    road.get(inLocation-1).addRobot(r);
                    if(isVisible)r.makeVisible();
                }
            }
            lastDone = true;
        }else{
            lastDone = false;
            if(isVisible)JOptionPane.showMessageDialog(null, "No hay robots que regresar.");
        }
    }
    
    /**
     * Set all stores full again and takes all robots back to thier initial location
     * Also add the elemets depending on the day. (increases actualDay)
     */
    public void reboot(){
        //refill stores
        if(stores.size() > 0){
            for(Store s : stores){
                  if(!s.isFull())s.setFull(true);
            }
            lastDone = true;
        }
        //takes robots back
        if(robots.size() > 0){
            for(Robot r : robots){
                int inLocation = r.getInitialLocation();
                int acLocation = r.getActualLocation();
                if(inLocation != acLocation){
                    road.get(acLocation-1).removeRobot(r);
                    Block inBlock = road.get(inLocation-1);
                    r.teleportToBlock(inLocation, inBlock);
                    road.get(inLocation-1).addRobot(r);
                    if(isVisible)r.makeVisible();
                }
            }
        }
        
        //Ciclo 2. Agruega los elementos del día en el que va la simulación
        if(actualDay < days){
            actualDay ++;
            if(elements[actualDay][0] == 1){
                placeRobot(elements[actualDay][1]);
                if(isVisible){
                    Robot newElement = road.get(elements[actualDay][1]-1).getLastRobot();
                    newElement.makeVisible();
                }
            }else if(elements[actualDay][0] == 2){
                placeStore(elements[actualDay][1], elements[actualDay][2]);
                if(isVisible){
                    Store newElement = findStore(elements[actualDay][1]);
                    newElement.makeVisible();
                }
            }
        }
    }
    
    //Miniciclo 6
    /**
     * Answers the total profit gained by robtos
     * @return int
     */
    public int profit(){
        int total = 0;
        for(Robot r : robots){
            total += r.getProfit();
        }
        return total;
    }
    
    /**
     * Gives a list of stores ordered by location and tenges (from least to most)
     * @return int[][]
     */
    public int[][] stores(){
        int[][] answer = new int[stores.size()][2];
        int i = 0;
        for(Store s : stores){
            answer[i][0] = s.getLocation();
            answer[i][1] = s.getStash();
            i++;
        }
        return SRCalculator.orderArray(answer);
    }
    
    /**
     * Givesa list of robots ordered by location and profit (from least to most)
     * @return int[][]
     */
    public int[][] robots(){
        int[][] answer = new int[robots.size()][2];
        int i = 0;
        for(Robot r : robots){
            answer[i][0] = r.getActualLocation();
            answer[i][1] = r.getProfit();
            i++;
        }
        return SRCalculator.orderArray(answer);
    }
    
    //Miniciclo 7
    /**
     * Ends the simulator
     * @return void
     */
    public void finish(){
        System.exit(0);
    }
    
    /**
     * Answers if the last operation was made
     * @return boolean
     */
    public boolean ok(){
        return lastDone;
    }
    
    //Ciclo2
    //Miniciclo 1
    /**
     * Constructor for objects of class SilkRoad. In this case it will have elements that will be added day by day
     * @param int days
     * @param int[][] elements
     */
    public SilkRoad(int days, int[][] elements){
        //Los atributos necesarios para el paso de los días
        this.days = days;
        actualDay = 0;
        int length = SRCalculator.determineLength(elements);
        this.elements = elements;
        
        //Primer constructor
        this.length = Math.abs(length);
        isVisible = false;
        lastDone = true;
        stores = new ArrayList<Store>();
        robots = new ArrayList<Robot>();
        road = new ArrayList<Block>();
        for(int i = 0; i < length; i++){
            int[] coordinates = SRCalculator.determineCoordinates(SRCalculator.isHorizontal);
            Block newBlock = new Block(SRCalculator.isHorizontal(), coordinates[0], coordinates[1], i+1);
            road.add(newBlock);
        }
        progressBar = new Bar();
        
        //Adicionamos el elemento del primer día
        if(elements[actualDay][0] == 1){
            placeRobot(elements[actualDay][1]);
        }else if(elements[actualDay][0] == 2){
            placeStore(elements[actualDay][1], elements[actualDay][2]);
        }
    }
    
    //Miniciclo 2
    /**
     * Moves the robots based on maximazing the total profit
     */
    public void moveRobots(){
        if(stores.size() > 0 && robots.size() > 0){
            ArrayList<int[]> listMovements = SRCalculator.determineMoves(robots, stores);
            for(int[] move : listMovements){
                moveRobot(move[0], move[1]);
            }
            lastDone = true;
        }else{
            lastDone = false;
            if(isVisible)JOptionPane.showMessageDialog(null, "No hay robots o tiendas.");
        }
    }
    
    //Miniciclo 3
    /**
     * Gives a list of stores ordered by location and times its has been stolen (from least to most)
     * @return int[][]
     */
    public int[][] emptiedStores(){
        if(!stores.isEmpty()){
            int[][] answer = new int[stores.size()][2];
            int i = 0;
            for(Store s : stores){
                answer[i][0] = s.getLocation();
                answer[i][1] = s.getTimesStolen();
                i++;
            }
            return SRCalculator.orderArray(answer);
        }
        return null;
    }
    
    //Miniciclo 4
    /**
     * Gives a list of robots orderes by location and profits they have gained in each move (from least to most)
     * @return int[][]
     */
    public int[][] profitPerMove(){
        if(!robots.isEmpty()){
            int maxMovements = SRCalculator.determineMaxMovements(robots);
            int[][] answer = new int[robots.size()][maxMovements+1];
            int i = 0;
            for(Robot r : robots){
                answer[i][0] = r.getActualLocation();
                ArrayList<Integer> robotProfits = r.getProfitsPerMove();
                for(int j = 1; j <= maxMovements; j++){
                    if(j-1 < robotProfits.size()){
                        answer[i][j] = robotProfits.get(j-1).intValue();
                    }else{
                        answer[i][j] = 0;
                    }
               }
            }
            if(isVisible)makeStandOut();
            return SRCalculator.orderArray(answer);
        }
        return null;
    }
    
    /**
     * Makes the robot with the most profit gained stand out in the simulation
     */
    private void makeStandOut(){
        Robot theOne = SRCalculator.searchMaxProfitRobot(robots);
        //Desactivamos los robots
        for(Robot r : robots){
            r.makeInvisible();
        }
        //Lo hacemos resaltar
        for(int t = 0; t < 5; t++){
            theOne.makeInvisible();
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){}
            theOne.makeVisible();
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){}
        }
        //Activamos los robots
        for(Robot r : robots){
            r.makeVisible();
        }
    }
    
    //Extra
    /**
     * Finds the store in the given location. It must be previuosly guaranted the there is a store
     * @param int location
     * @return Store
     */
    private Store findStore(int location){
        for(Store s : stores){
            if(s.getLocation() == location)return s;
        }
        return null;
    }
    
    public int getLength(){
        return length;
    }
    
    public int getDays(){
        return days;
    }
    
    public int getActualDay(){
        return actualDay;
    }
    
    public ArrayList<Robot> getRobots(){
        return robots;
    }
    
    public ArrayList<Store> getStores(){
        return stores;
    }
    
    public Bar getProgressBar(){
        return progressBar;
    }
    
    public ArrayList<Block> getRoad(){
        return road;
    }
}