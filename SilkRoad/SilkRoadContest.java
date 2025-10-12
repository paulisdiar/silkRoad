import java.util.ArrayList;

/**
 * Is the class in charge of solve and simulate the problem
 * @author Paula Díaz
 * @author Juan Pablo Vélez
 * @version 4
 */
public class SilkRoadContest {

    /**
     * Calculate the max profit given the information for each day.
     * @param int[][] days
     * @return int []
    */
    public int[] solve(int[][] days) {
        //Crear el simulador
        SilkRoad silkroad = new SilkRoad(days.length, days);
        //Crear arreglo para registrar ganancias diarias
        int[] dailyProfits = new int[days.length];
        //Simular día a día
        for (int d = 0; d < days.length; d++) {
            silkroad.reboot();     // reinicia robots y tiendas full
            silkroad.moveRobots(); // mueve los robots para mayor profit
            dailyProfits[d] = silkroad.profit(); // guarda ganancia del día
        }
        return dailyProfits;
    }
    
    /**
     * simulates the problem
     * @param int[][] days
     * @param boolean slow
     */
        public void simulate(int[][] days, boolean slow) {
         //Crear el simulador
        SilkRoad silkroad = new SilkRoad(days.length, days);
        silkroad.makeVisible();//Hacer visible
        //Simular dia dia
        for (int d = 0; d < days.length; d++) {
            silkroad.reboot(); // reinicia robots y tiendas full
            silkroad.moveRobots();// mueve los robots para mayor profit
            // Mostrar ganancias del día
            int profitToday = silkroad.profit();
            //Construir progressBar

            // slow
        }
    }
}

