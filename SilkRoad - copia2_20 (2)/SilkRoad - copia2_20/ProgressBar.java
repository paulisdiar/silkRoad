import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa una barra de progreso gráfica simple
 * para visualizar las ganancias y pérdidas diarias.
 * 
 * Cada barra verde representa una ganancia y cada barra roja una pérdida.
 * 
 * @author Paula Alejandra Díaz
 * @version 3
 */
public class ProgressBar extends JPanel {

    private int[] profits;   // arreglo con las ganancias y pérdidas
    private int maxProfit;   // utilidad máxima calculada

    public ProgressBar(int[] profits, int maxProfit) {
        this.profits = profits;
        this.maxProfit = maxProfit;
        setPreferredSize(new Dimension(800, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int baseY = getHeight() - 60;  // posición base de las barras
        int barWidth = 60;             // ancho de cada barra
        int spacing = 20;              // espacio entre barras
        int startX = 50;               // posición inicial en X

        for (int i = 0; i < profits.length; i++) {
            int value = profits[i];
            int height = Math.abs(value) * 10; // escala visual
            int x = startX + i * (barWidth + spacing);
            int y = (value >= 0) ? baseY - height : baseY;

            // color según signo del valor
            g2.setColor(value >= 0 ? Color.GREEN : Color.RED);
            g2.fillRect(x, y, barWidth, height);

            // contorno
            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, barWidth, height);

            // etiquetas del día y valor
            g2.setColor(Color.BLACK);
            g2.drawString("Día " + (i + 1), x + 5, baseY + 20);
            g2.drawString(String.valueOf(value), x + 15, y - 5);
        }

        // título con la utilidad máxima
        g2.setColor(Color.BLUE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("Máxima utilidad posible: " + maxProfit, 50, 30);
    }

    /**
     * Muestra la ventana con las barras gráficas.
     */
    public void showWindow() {
        JFrame frame = new JFrame("Simulación Silk Road");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

