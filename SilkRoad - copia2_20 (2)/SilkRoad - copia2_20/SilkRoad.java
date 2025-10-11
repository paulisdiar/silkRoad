import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 * DOPO versión of the silk road problem with a graphical representation.
 * 
 * @author Paula Alejandra Díaz
 * @author Juan Pablo Vélez
 * @version 3
 */
public class SilkRoad{
    //Attributes
    private final int length;
    private int totalProfit;
    private final int days;
    private int actualDay;
    private boolean isVisible;
    private boolean lastDone;
    private ArrayList<Store> stores;
    private ArrayList<Robot> robots;
    private Road road;
    private int[] dailyProfits;

    
    //Methods
    //CICLO 1
    //MiniCiclo 1
    /**
     * Creates the silk road problem simulator
     * @param int length
     * @return SilkRoad
     */
    public SilkRoad(int length){
        //days y actual days los dejaremos para después.
        days = 0;
        actualDay = 0;
        //para después.
        
        this.length = Math.abs(length);
        totalProfit = 0;
        isVisible = false;
        lastDone = true;
        stores = new ArrayList(Collections.nCopies(Math.abs(length), null));
        robots = new ArrayList();
        road = new Road(Math.abs(length));
    }
    
    //MiniCiclo 2
    /**
     * Adds a new store to the game if its posible
     * @param int location
     * @param int tenges
     */
    public void placeStore(int location, int tenges){
        if(road.in(location) && stores.get(location-1) == null && tenges > 0){
            Store store = road.placeStore(location, tenges);
            stores.set(location-1, store);
            if(isVisible) store.makeVisible();
            lastDone = true;
        }else if(!road.in(location) || stores.get(location-1) != null || tenges <= 0){
            lastDone = false;
            if(isVisible)JOptionPane.showMessageDialog(null, "Fue imposible ubicar la tienda.");
        }
    }
    
    /**
     * Deletes a store from the game if its posible
     * @param int location
     * @return void
     */
    public void removeStore(int location){
        if(road.in(location) && stores.get(location-1) != null){
            road.removeStore(location);
            if(isVisible) stores.get(location-1).makeInvisible();
            stores.set(location-1, null);
            lastDone = true;
        }else if(!road.in(location) || stores.get(location-1) == null){
            lastDone = false;
            if(isVisible)JOptionPane.showMessageDialog(null, "Fue imposible eliminar la tienda.");
        }
    }
    
    //MiniCiclo 3
    /**
     * Adds a new robot to the game if its posible
     * @param int location
     * @return void
     */
    public void placeRobot(int location){
        if(road.in(location)){
            Robot robot = road.placeRobot(location);
            robots.add(robot);
            if(isVisible) robot.makeVisible();
            lastDone = true;
        }else if(!road.in(location)){
            lastDone = false;
            if(isVisible)JOptionPane.showMessageDialog(null, "Fue imposible ubicar el robot.");
        }
    }
    
    /**
     * Deletes a robot from the game if its posible
     * @param int location
     * @return void
     */
    public void removeRobot(int location){
        if(road.in(location)){
            Robot robot = road.removeRobot(location);
            if(robot != null){
                robots.remove(robot);
                lastDone = true;
            }else{
                lastDone = false;
                if(isVisible)JOptionPane.showMessageDialog(null, "Fue imposible eliminar el robot.");
            }
        }else if(!road.in(location)){
            lastDone = false;
            if(isVisible)JOptionPane.showMessageDialog(null, "Fue imposible eliminar el robot.");
        }
    }
    
    //MiniCiclo 4
    /**
     * Moves a robot from a given location a certain amount of meters
     * @param int location
     * @param int meters
     * @return void
     */
    public void moveRobot(int location, int meters) throws InterruptedException{
        if(road.in(location)){
            lastDone = road.moveRobot(location, meters);
        }else if(!road.in(location)){
            lastDone = false;
        }
        if(isVisible && lastDone == false)JOptionPane.showMessageDialog(null, "No se pudo realizar el movimiento del robot.");
    }
    
    /**
     * Resupply all stores stashes
     */
    public void resupplyStores(){
        if(countStores() > 0){
            for(Store s : stores){
                if(s != null){
                    s.setFull(true);
                    s.changeColor(true);//Ciclo 2
                    if(isVisible)s.makeVisible();
                }
            }
            lastDone = true;
        }else if(countStores() == 0){
            lastDone = false;
            if(isVisible)JOptionPane.showMessageDialog(null, "No hay tiendas a las cuales reabastecer.");
        }
    }
    
    /**
     * Returns all robots to their initial location
     */
    public void returnRobots(){
        if(!robots.isEmpty()){
            for(Robot r : robots){
                if(isVisible) r.makeInvisible();
                r.moveInitial();
                if(isVisible) r.makeVisible();
            }
            lastDone = true;
        }else{
            lastDone = false;
            if(isVisible)JOptionPane.showMessageDialog(null, "No hay robots a los cuales devolver.");
        }
    }
    
    /**
     * Set all stores to full and the robots to their initial location
     */
    public void reboot(){
        for(Robot r : robots){
            r.moveInitial();
            if(isVisible) r.makeVisible();
        }
        
        if(countStores() > 0){
            for(Store s : stores){
                if(s != null){
                    s.setFull(true);
                    s.changeColor(true);//Ciclo 2
                    if(isVisible)s.makeVisible();
                }
            }
        }
        actualDay ++;
    }
    
    //MiniCiclo 5
    /**
     * Answers the total profit
     * @return int
     */
    public int profit(){
        int count = 0;
        for(Robot r : robots){
            count += r.getProfit();
        }
        totalProfit = count;
        return totalProfit;
    }
    
    /**
     * Gives a list of stores by their location and stashes
     * @return int[][]
     */
    public int[][] stores(){
        int numberStores = countStores();
        int[][] answer = new int[numberStores][2];
        int i = 0;
        for(Store s : stores){
            if(s != null){
                answer[i][0] = s.getLocation();
                answer[i][1] = s.getStash();
                i++;
            }
        }
        return answer;
    }
    
    /**
     * Counts the number of stores in the road
     * @return int
     */
    public int countStores(){
        int count = 0;
        for(Store s  : stores){
            if(s != null) count++;
        }
        return count;
    }
    
    /**
     * Gives a list of robots ordered by their location and profit
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
        orderArray(answer);
        return answer;
    }
    
    /**
     * Orders the given array by column, from smallest to biggest
     * @param int[][] array
     * @return int[][]
     */
    private int[][] orderArray(int[][] array){
        Arrays.sort(array, (a, b) ->{
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]); // columna 0
            } else {
                return Integer.compare(a[1], b[1]); // columna 1
            }
        });
        return array;
    }
    
    //MiniCiclo 6
    /**
     * Makes the game visible in the canvas
     * @return void
     */
    public void makeVisible(){
        road.makeVisible();
        for(Store s : stores){
            if(s != null) s.makeVisible();
        }
        
        for(Robot r : robots){
            r.makeVisible();
        }
        isVisible = true;
    }
    
    /**
     * Makes the game invisible in the canvas
     * @return void
     */
    public void makeInvisible(){
        road.makeInvisible();
        for(Store s : stores){
            if(s != null) s.makeInvisible();
        }
        
        for(Robot r : robots){
            r.makeInvisible();
        }
        isVisible = false;
    }
    
    //MiniCiclo 7
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
    
    //CICLO 2
    
    //MiniCiclo 2
    /**
     * Creates a silkRoad simulator with the incoming data from the ICPC 2024 problem J.
     * @param int days
     * @param int[][] elements
     * @return SilkRoad
     */
    public SilkRoad(int days, int[][] elements){
        this.days = days;
        actualDay = 0;
        int l = searchLength(elements);
        length = l;
        totalProfit = 0;
        isVisible = false;
        lastDone = true;
        stores = new ArrayList(Collections.nCopies(length, null));
        robots = new ArrayList();
        road = new Road(length);
    }
    
    /**
     * Searches the length of the silkRoad with the ICPC data
     * @param int[][] array
     * @return int
     */
    public int searchLength(int[][] array){
        int l = array.length;
        int answer = array[0][1];
        for(int i = 1; i < l; i++){
            if(answer < array[i][1])answer = array[i][1];
        }
        return answer;
    }
    
    //MiniCiclo 3
    /**
     * Moves robots deciding the best move (maximizing profit)
     */
    public void moveRobots(){
        int robotsAmount = robots.size();
        int storesAmount = countStores();
        ArrayList<Robot> copyRobots = new ArrayList<>(robots);
        if(robotsAmount > 0 && storesAmount > 0){
            for(Store s : stores){
                if(s != null && s.isFull() && copyRobots.size() > 0){
                    int x1 = s.getLocation();
                    Integer[][] profits = new Integer[copyRobots.size()][];
                    for(int i = 0; i < copyRobots.size(); i++){
                        int x2 = copyRobots.get(i).getActualLocation();
                        int meters = x1 - x2;
                        profits[i] = new Integer[]{Integer.valueOf(s.getStash() - Math.abs(meters)), Integer.valueOf(meters)};
                    }
                    int robotIndex = searchMaxProfit(profits);
                    try{
                        moveRobot(copyRobots.get(robotIndex).getActualLocation(), profits[robotIndex][1]);
                    }catch(InterruptedException e){}
                    copyRobots.remove(robotIndex);
                }
            }
            lastDone = true;
        }else{
            lastDone = false;
        }
    }
    
    /**
     * Searches the max Profit and gives the index of the robot who has it
     * @param Integer[][]
     * @return int
     */
    private int searchMaxProfit(Integer[][] profits){
        int maxIndex = 0;
        Integer max = profits[0][0]; // el primer profit
        for(int i = 1; i < profits.length; i++){
            if(profits[i][0].compareTo(max) > 0){
                max = profits[i][0];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    //MiniCiclo 4
    /**
     * Gives a list of the store location and the times it has been stolen
     * @retun int[][]
     */
    public int[][] emptiedStores(){
        int numberStores = countStores();
        if(numberStores > 0){
            int[][] answer = new int[numberStores][2];
            int i = 0;
            for(Store s : stores){
                if(s != null){
                    answer[i][0] = s.getLocation();
                    answer[i][1] = s.getTimesStolen();
                    i++;
                }
            }
            return answer;
        }
        return null;
    }
    
    //MiniCiclo 5
    /**
     * gives a list of the robots location and the profit they have earned in each move. If
     * the robot hasn´t move it will show a zero. Also, makes the robot with the most profit
     * stand out
     * @return int[][]
     */
    public int[][] profitPerMove(){
        if(robots.size() > 0){
            int maxTimesStole = getMaxTimesStole();
            int[][] answer = new int[robots.size()][maxTimesStole + 1];
            int i = 0;
            for(Robot r : robots){
                answer[i][0] = r.getActualLocation();
                ArrayList<Integer> robotProfits = r.getProfitsPerMove();
                for(int j = 1; j <= maxTimesStole; j++){
                    if (j-1 < robotProfits.size()) {
                        answer[i][j] = robotProfits.get(j-1).intValue();
                    } else {
                        answer[i][j] = 0; // si no hay movimiento, cero
                    }
                }
                i++;
            }
            if(isVisible)makeStandOut();
            return orderArray2(answer);
        }
        return null;
    }
    
    /**
     * Gives the most times a robot has moved trying to get a profit.
     * @return int
     */
    private int getMaxTimesStole(){
        int max = robots.get(0).getProfitsPerMove().size();
        for(int i = 1; i < robots.size(); i++){
            int aux = robots.get(i).getProfitsPerMove().size();
            max = (max > aux) ? max: aux;
        }
        return max;
    }
    
    /**
     * Gives the array ordered using the next standar: from least to most by column
     * @param int[][]
     * @return int[][]
     */
    private int[][] orderArray2(int[][] array){
        Arrays.sort(array, (a, b) -> {
            for(int i = 0; i < a.length; i++){
                if (a[i] != b[i])return Integer.compare(a[i], b[i]);
            }
            return 0;
        });
        return array;
    }
    
    /**
     * Makes the robot with more profit stand out
     */
    private void makeStandOut(){
        //busca al robot con mayor profit
        Robot maxProfitRobot = robots.get(0);
        for(int i = 1; i < robots.size(); i++){
            if(maxProfitRobot.getProfit() < robots.get(i).getProfit())maxProfitRobot = robots.get(i);
        }
        //resalta al robot con mayor profit
        for(int t = 0; t < 10; t++){
            maxProfitRobot.makeInvisible();
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){}
            maxProfitRobot.makeVisible();
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){}
        }
    }
    //
    
    /**
     * 
     */
    public SilkRoad(int[] dailyProfits) {
        this.length = 0;
        this.days = 0;
        this.dailyProfits = dailyProfits;
    }
    
    public static int searchLength(int[][] array) {
    int answer = array[0][1];
    for (int i = 1; i < array.length; i++) {
        if (answer < array[i][1]) answer = array[i][1];
    }
    return answer;
    }

    /**
     * 
     */
    public void simulate() {
    System.out.println("=== Simulación de Silk Road ===");
    for (int i = 0; i < dailyProfits.length; i++) {
        System.out.println("Día " + (i + 1) + ": " + dailyProfits[i]);
    }

    int maxProfit = SilkRoadContest.solve(dailyProfits);
    System.out.println("Máxima utilidad posible: " + maxProfit);

    // Crear y mostrar la barra de progreso gráfica
    ProgressBar barra = new ProgressBar(dailyProfits, maxProfit);
    barra.showWindow();
    }

    //Extras
    /**
     * Gets the length of the road.
     * @return int
     */
    public int getLength(){
        return length;
    }
    
    /**
     * Gets the total profit earned
     * @return int
     */
    public int getTotalProfit(){
        return totalProfit;
    }
    
    /**
     * Answers if the simulator is visible
     * @return boolean
     */
    public boolean isVisible(){
        return isVisible;
    }
    
    /**
     * Gets the list of stores
     * @return ArrayList<Store>
     */
    public ArrayList<Store> getStores(){
        return stores;
    }
    
    /**
     * Gets the list of robots
     * @return ArrayList<Robot>
     */
    public ArrayList<Robot> getRobots(){
       return robots; 
    }
    
    /**
     * Gives the road
     * @return Road
     */
    public Road getRoad(){
        return road;
    }
    
    /**
     * Gives the days the simulator will go on
     * @return int
     */
    public int getDays(){
        return days;
    }
    
    /**
     * Gives the actual day of the simulator
     * @return int
     */
    public int getActualDay(){
        return actualDay;
    }
}
