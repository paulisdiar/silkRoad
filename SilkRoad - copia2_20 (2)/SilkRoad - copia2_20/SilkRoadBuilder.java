import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import javax.swing.JOptionPane;


/**
 * Clase auxiliar privada que construye la SilkRoad a partir de los datos ICPC.
 * Solo puede ser usada dentro de la clase SilkRoad.
 */
public class SilkRoadBuilder {
    private int days;
    private int length;
    private int[] dailyProfits;
    private ArrayList<Store> stores;
    private ArrayList<Robot> robots;
    private Road road;

    /**
     * Construye los datos necesarios para inicializar la SilkRoad.
     */
    public SilkRoadBuilder(int days, int[][] elements) {
        this.days = days;
        this.length = SilkRoad.searchLength(elements);

        // Extraer solo las ganancias diarias (columna 1)
        this.dailyProfits = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            this.dailyProfits[i] = elements[i][1];
        }

        // Inicializar componentes base
        this.road = new Road(length);
        this.stores = new ArrayList<>(Collections.nCopies(length, null));
        this.robots = new ArrayList<>();

        // Crear tiendas y robots automáticamente
        initializeStoresAndRobots();

        // Mostrar datos en consola (opcional)
        System.out.println("=== Datos de construcción de SilkRoad ===");
        for (int i = 0; i < dailyProfits.length; i++) {
            System.out.println("Día " + (i + 1) + ": " + dailyProfits[i]);
        }
        System.out.println("Longitud del camino: " + length);
        System.out.println("Tiendas creadas: " + countStores());
        System.out.println("Robots iniciales: " + robots.size());
        System.out.println("=========================================");
    }

    /**
     * Inicializa tiendas y robots automáticamente según los datos.
     * Las tiendas se colocan en posiciones impares y los robots en posiciones pares.
     */
    private void initializeStoresAndRobots() {
        for (int i = 1; i <= length; i++) {
            if (i % 2 != 0) {
                // Tienda
                Store store = road.placeStore(i, Math.abs(i * 2)); // stok inicial
                stores.set(i - 1, store);
            } else {
                // Robot
                Robot robot = road.placeRobot(i);
                robots.add(robot);
            }
        }
    }

    /**
     * Cuenta las tiendas no nulas.
     */
    private int countStores() {
        int count = 0;
        for (Store s : stores) {
            if (s != null) count++;
        }
        return count;
    }
}
