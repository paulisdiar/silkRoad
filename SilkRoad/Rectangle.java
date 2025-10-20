import java.awt.*;
import java.util.Random;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */
public class Rectangle{
    //Atributes
    public static int EDGES = 4;
    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private Color color;
    private boolean isVisible;
    
    private final Color myColor;//Ciclo 2: para guardar el color de la tienda al cambiarsele el color
    
    //Methods
    /**
     * Create a new rectangle at default position with a random color.
     * @param int height
     * @param int width
     * @param int xPos
     * @param int yPos
     */
    public Rectangle(int height, int width, int xPos, int yPos){
        this.height = height;
        this.width = width;
        xPosition = xPos;
        yPosition = yPos;
        color = UtilColors.getRandomColor();
        isVisible = false;
        myColor = color;
    }
    
    /**
     * Create a new rectangle at default position with a givencolor.
     * @param int height
     * @param int width
     * @param int xPos
     * @param int yPos
     * @param Color color
     */
    public Rectangle(int height, int width, int xPos, int yPos, Color color){
        this.height = height;
        this.width = width;
        xPosition = xPos;
        yPosition = yPos;
        this.color = color;
        isVisible = false;
        myColor = color;
    }
    
    /**
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
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
     * Answers if tow rectangles are equal
     * @param Object object
     * @return boolean
     */
    @Override
    public boolean equals(Object object){
        if(this == object)return true;
        if(!(object instanceof Rectangle))return false;
        Rectangle r = (Rectangle) object;
        return height==r.getHeight() && width==r.getWidth() && xPosition==r.getXPosition() && yPosition==r.getYPosition() && color==r.getColor();
    }
    
    /**
     * Gives the height of the rectangle
     * @return int
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * Gives the width of the rectangle
     * @return int
     */
    public int getWidth(){
        return width;
    }
    
    /**
     * Gives the  xPosition of the rectangle
     * @return int
     */
    public int getXPosition(){
        return xPosition;
    }
    
    /**
     * Gives the yPosition of the rectangle
     * @return int
     */
    public int getYPosition(){
        return yPosition;
    }
    
    /**
     * Gives the color of the rectangle
     * @return Color
     */
    public Color getColor(){
        return color;
    }
    
    /**
     * Sets the color of the rectangle
     * @param Color newColor
     */
    public void changeColor(Color newColor){
        color = newColor;
    }
    
    /**
     * Gives the original color of the rectangle
     * @return Color
     */
    public Color getMyColor(){
        return myColor;
    }
    
    /**
     * Changes the rectangle size
     * @param int h
     * @param int w
     */
    public void changeSize(int h, int w){
        height = h;
        width = w;
        if(isVisible)this.makeVisible();
    }
    
    //Extra
    /*
     * Draw the rectangle with current specifications on screen.
     */

    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, 
                                       width, height));
            canvas.wait(10);
        }
    }

    /*
     * Erase the rectangle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}

