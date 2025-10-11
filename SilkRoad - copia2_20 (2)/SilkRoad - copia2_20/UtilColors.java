import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class is in charge of the color.
 * 
 * @author Paula Díaz
 * @author Juan Pablo Vélez
 * @version 3
 */
public class UtilColors{
    //Atributes
    public static final String[] awtColorNames = {
    // Estándar de AWT
    "BLUE", "CYAN", "GREEN", "LIGHT_GRAY",
    "MAGENTA", "ORANGE", "PINK", "RED", "WHITE", "YELLOW",
    // Paleta extendida (no están en java.awt.Color, hay que definirlos con RGB)
    "BROWN", "PURPLE", "VIOLET", "INDIGO", "TURQUOISE", "TEAL",
    "NAVY", "LIME", "OLIVE", "MAROON", "AQUA", "BEIGE", "CORAL",
    "CRIMSON", "GOLD", "KHAKI", "LAVENDER", "SALMON", "SIENNA",
    "SKY_BLUE", "SLATE_GRAY", "TAN", "TOMATO",
    //colores reservados, grey para cabeza robot y black, dark_grey para los bloques
    "GRAY", "BLACK", "DARK_GRAY"};
    
    public static final ArrayList<String> awtColors = new ArrayList(Arrays.asList(awtColorNames));
    
    //Methods
    /**
     * Gives a random color from the AwtColors list
     */
    public static Color getRandomColor(){
        Random random = new Random();
        int ranNumber = random.nextInt(33);
        String color = UtilColors.getColorName(ranNumber);
        return UtilColors.getColor(color);
    }
    
    /**
     * Gives the color by a number between 0 - 35. the number given is the index in the ArrayList valid for Awt
     * @param int index
     * @return String
     */
    public static String getColorName(int index){
        return awtColors.get(index);
    }
    
    /**
     * Gives the valid format in awt to use the color. It is given by the name of the color given
     * @param String name
     * @return Color 
     */
    public static Color getColor(String name) {
        switch (name.toUpperCase()) {
            // Estándar AWT
            case "BLACK": return Color.BLACK;
            case "BLUE": return Color.BLUE;
            case "CYAN": return Color.CYAN;
            case "DARK_GRAY": return Color.DARK_GRAY;
            case "GRAY": return Color.GRAY;
            case "GREEN": return Color.GREEN;
            case "LIGHT_GRAY": return Color.LIGHT_GRAY;
            case "MAGENTA": return Color.MAGENTA;
            case "ORANGE": return Color.ORANGE;
            case "PINK": return Color.PINK;
            case "RED": return Color.RED;
            case "WHITE": return Color.WHITE;
            case "YELLOW": return Color.YELLOW;
            // Extendidos (RGB definidos manualmente)
            case "BROWN": return new Color(165, 42, 42);
            case "PURPLE": return new Color(128, 0, 128);
            case "VIOLET": return new Color(238, 130, 238);
            case "INDIGO": return new Color(75, 0, 130);
            case "TURQUOISE": return new Color(64, 224, 208);
            case "TEAL": return new Color(0, 128, 128);
            case "NAVY": return new Color(0, 0, 128);
            case "LIME": return new Color(0, 255, 0);
            case "OLIVE": return new Color(128, 128, 0);
            case "MAROON": return new Color(128, 0, 0);
            case "AQUA": return new Color(0, 255, 255);
            case "BEIGE": return new Color(245, 245, 220);
            case "CORAL": return new Color(255, 127, 80);
            case "CRIMSON": return new Color(220, 20, 60);
            case "GOLD": return new Color(255, 215, 0);
            case "KHAKI": return new Color(240, 230, 140);
            case "LAVENDER": return new Color(230, 230, 250);
            case "SALMON": return new Color(250, 128, 114);
            case "SIENNA": return new Color(160, 82, 45);
            case "SKY_BLUE": return new Color(135, 206, 235);
            case "SLATE_GRAY": return new Color(112, 128, 144);
            case "TAN": return new Color(210, 180, 140);
            case "TOMATO": return new Color(255, 99, 71);
        }
        return null; // Si no coincide
    }
}