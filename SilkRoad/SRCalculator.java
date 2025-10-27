import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class is in charge of all calculation and utility methods of the simulator
 * 
 * @author Paula Díaz
 * @author Juan Pablo Vélez
 * @version 4
 */
public class SRCalculator{
    // The next set of atributes are used to set the blocks x,y position in the canvas
    private int[] verticalLimits = {60, 900};
    private int[] horizontalLimits = {0, 1500};
    private boolean isHorizontal = true;
    private int actualX = 0;
    private int actualY = 20;
    private int direction = 1;
    //
    /**
     * Constructor for objects of class SRCalculator
     */
    public SRCalculator(){}
    
    /**
     * Determines coordinates of the block knowing if its horizontal or vertical. Also it is an accumulation process
     * @param boolean isH
     * @return int[]
     */
    public int[] determineCoordinates(boolean isH){
        int[] coordinates = new int[2];
        if(isHorizontal){
            if(direction == 1 && actualX+45 > horizontalLimits[1]){
                horizontalLimits[1] -= 50;
                isHorizontal = !isH;
                actualY += 35;
                actualX -= 40;
                return determineCoordinates(isHorizontal);
            }else if(direction == -1 && actualX < horizontalLimits[0]){
                horizontalLimits[0] = actualX+55;
                isHorizontal = !isH;
                actualY -= 45;
                actualX += 45;
                return determineCoordinates(isHorizontal);
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
                isHorizontal = !isH;
                actualX -= 45;
                actualY -= 35 ;
                return determineCoordinates(isHorizontal);
            }else if(direction == -1 && actualY < verticalLimits[0]){
                direction = 1;
                verticalLimits[0] = actualY+65;
                isHorizontal = !isH;
                actualX += 40;
                actualY += 45;
                return determineCoordinates(isHorizontal);
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
     * Gives the actual value of the isHorizontal atribute
     * @return boolean
     */
    public boolean isHorizontal(){
        return isHorizontal;
    }
    
    /**
     * Orders the given array by column, from smallest to biggest
     * @param int[][] array
     * @return int[][]
     */
    public static int[][] orderArray(int[][] array){
        Arrays.sort(array, (a, b) -> {
        for (int i = 0; i < Math.min(a.length, b.length); i++) {
            int cmp = Integer.compare(a[i], b[i]);
            if (cmp != 0) return cmp;
        }
        return Integer.compare(a.length, b.length);
        });
        return array;
    }
    
    /**
     * Determines the length of the silkRoad based on the information given by the elements
     * @param int[][]
     * return int
     */
    public static int determineLength(int[][] elements){
        int l = elements.length;
        int answer = elements[0][1];
        for(int i = 1; i < l; i++){
            if(answer < elements[i][1])answer = elements[i][1];
        }
        return answer;
    }
    
    /**
     * Determines max movements done between robots
     * @param ArrayList<Robot> robots
     * @return int
     */
    public static int determineMaxMovements(ArrayList<Robot> robots){
        int max = 0;
        for(Robot r : robots){
            int moves = r.getProfitsPerMove().size();
            if(moves > max) max = moves;
        }
        return max;
    }
    
    /**
     * Searches the robot who has gained the maximum profit
     * @param ArrayList<Robot> robots
     * @return Robot
     */
    public static Robot searchMaxProfitRobot(ArrayList<Robot> robots){
        Robot theOne = robots.get(0);
        for(int i = 1; i < robots.size(); i++){
            theOne = (theOne.getProfit() < robots.get(i).getProfit()) ? robots.get(i) : theOne;
        }
        return theOne;
    }
    
    /**
     * Determines the optimum moves the robots must do to maximize the profit
     * @param ArrayList<Robot> robots
     * @param ArrayList<Store> stores
     * @return ArrayList<int[]>
     */
    public static ArrayList<int[]> determineMoves(ArrayList<Robot> robots, ArrayList<Store> stores){
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[][] storesArray = new int[stores.size()][2];
        int[] robotsArray = new int[robots.size()];
        //llenamos los arreglos auxiliares
        for(int i = 0; i < storesArray.length; i++){
            storesArray[i][0] = stores.get(i).getLocation();
            storesArray[i][1] = stores.get(i).getStash();
        }
        for(int j = 0; j < robotsArray.length; j++){
            robotsArray[j] = robots.get(j).getActualLocation();
        }
        
        //solución
        for(int s = 0; s < storesArray.length; s++){
            int[][] distances = new int[robots.size()][2];
            if(stores.get(s).hasTenges()){
                int[] move = new int[2];
                int xS = storesArray[s][0];
                for(int r = 0; r < robotsArray.length; r++){
                    int xR = robotsArray[r];
                    distances[r][0] = r;
                    distances[r][1] = Math.abs(xS-xR);
                }
                int[] bestMove = determineMinimum(distances);
                move[0] = robotsArray[bestMove[0]];
                move[1] = xS-robotsArray[bestMove[0]];
                moves.add(move);
                //hacer el movimiento
                robotsArray[bestMove[0]] += (xS-robotsArray[bestMove[0]]);
            }
        }
        return moves;
    }
    
    private static int[] determineMinimum(int[][] array){
        int[] answer = array[0];
        for(int i = 1; i < array.length; i++){
            answer = (answer[1] > array[i][1]) ? array[i] : answer;
        }
        return answer;
    }
    
    /**
     * Determines the maximum profit posible to gain
     * @param Arraylist<Store> stores
     * @return int
     */
    public static int determineTotal(ArrayList<Store> stores){
        int total = 0;
        for(Store s : stores){
            total += s.getStash();
        }
        return total;
    }
    
    /**
     * Gives a random number between the 1 and the given limit
     * @param int limit
     * @return int
     */
    public static int getRandom(int limit){
        Random random = new Random();
        return random.nextInt(limit) +1;
    }
}