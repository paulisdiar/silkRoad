
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main {
    public static void main(String[] args) {
        // Datos de ejemplo: ganancias y pérdidas diarias
        int[] profits = {5, -3, 7, -2, 4, -6, 3};

        // Crear la simulación
        SilkRoad simulation = new SilkRoad(profits);

        // Ejecutar la simulación
        simulation.simulate();
    }
}
