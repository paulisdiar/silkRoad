import javax.swing.*;
import java.util.Arrays;

/**
 * La clase SilkroadContest Suma día a día las ganancias o pérdidas. Guarda la utilidad máxima encontrada.
 * 
 * @author Paula Díaz/Juan Pablo Vélez
 * @version 4
 */
public class SilkRoadContest {

    /**
     * Calcula la máxima utilidad posible dada la información de cada día.
     * @param profits 
     * @return int
    */
    public static int solve(int[] profits) {
        int maxProfit = 0;
        int currentProfit = 0;

        for (int p : profits) {
            currentProfit += p;
            if (currentProfit < 0) {
                currentProfit = 0; // reiniciamos si hay pérdidas
            }
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit; // actualizamos la mejor utilidad
            }
        }
        return maxProfit;
    }
    
    /**
     * 
     */
        public static void simulate() {
        // Solicitar número de días
        String inputDays = JOptionPane.showInputDialog("¿Cuántos días quieres simular?");
        int n = Integer.parseInt(inputDays);

        // Crear arreglo con ganancias o pérdidas
        int[] profits = new int[n];
        for (int i = 0; i < n; i++) {
            String value = JOptionPane.showInputDialog("Ganancia o pérdida del día " + (i + 1) + ":");
            profits[i] = Integer.parseInt(value);
        }

        // Mostrar datos ingresados
        JOptionPane.showMessageDialog(null, "Datos ingresados: " + Arrays.toString(profits));

        // Crear el objeto SilkRoad (esto disparará la ProgressBar)
        SilkRoad sr = new SilkRoad(profits);
    }

    /**
     * Permite ejecutar directamente desde BlueJ.
     */
    public static void main(String[] args) {
        simulate();
    }
}

