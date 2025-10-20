import java.awt.*;
import java.util.Random;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Triangle{
    
    public static int VERTICES=3;
    
    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private Color color;
    private boolean isVisible;
    
    private final Color myColor;//Ciclo 2: para guardar el color de la tienda al cambiarsele el color

    /**
     * Create a new triangle at default position with a random color.
     * @param int height
     * @param int width
     * @param int xPos
     * @param int yPos
     */
    public Triangle(int height, int width, int xPos, int yPos){
        this.height = height;
        this.width = width;
        xPosition = xPos;
        yPosition = yPos;
        color = UtilColors.getRandomColor();
        isVisible = false;
        myColor = color;
    }
    
    /**
     * Create a new triangle at default position with the given color.
     * @param int height
     * @param int width
     * @param int xPos
     * @param int yPos
     * @param Color color
     */
    public Triangle(int height, int width, int xPos, int yPos, Color color){
        this.height = height;
        this.width = width;
        xPosition = xPos;
        yPosition = yPos;
        this.color = color;
        isVisible = false;
        myColor = color;
    }
    
    /**
     * Make this triangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this triangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * moves the figure to a certain position
     * @param int x
     * @param int y
     * @return void
     */
    public void teleport(int x, int y){
        xPosition = x;
        yPosition = y;
    }
    
    /**
     * Answers if tow triangles are equal
     * @param Object object
     * @return boolean
     * @override
     */
    public boolean equals(Object object){
        if(this == object)return true;
        if(!(object instanceof Triangle))return false;
        Triangle t = (Triangle) object;
        return height==t.getHeight() && width==t.getWidth() && xPosition==t.getXPosition() && yPosition==t.getYPosition() && color==t.getColor();
    }
    
    /**
     * Gives the height of the triangle
     * @return int
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * Gives the width of the triangle
     * @return int
     */
    public int getWidth(){
        return width;
    }
    
    /**
     * Gives the  xPosition of the triangle
     * @return int
     */
    public int getXPosition(){
        return xPosition;
    }
    
    /**
     * Gives the yPosition of the triangle
     * @return int
     */
    public int getYPosition(){
        return yPosition;
    }
    
    /**
     * Gives the color of the triangle
     * @return Color
     */
    public Color getColor(){
        return color;
    }
    
    /**
     * Sets the color of the triangle
     * @param Color newColor
     */
    public void changeColor(Color newColor){
        color = newColor;
    }
    
    /**
     * Gives the original color of the triangle
     * @return Color
     */
    public Color getMyColor(){
        return myColor;
    }
    
    //Extra
    /*
     * Draw the triangle with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { xPosition, xPosition + (width/2), xPosition - (width/2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }

    /*
     * Erase the triangle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
