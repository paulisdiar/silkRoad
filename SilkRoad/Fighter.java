
/**
 * This store only can be steel if the robot has more profit
 * 
 * @author Paula Diaz
 * @author Juan Pablo Vélez
 * @version 4
 */
public class Fighter extends Store{
    /**
     * Constructor for objects of class Autonomous
     * @param int location
     * @param int tenges
     * @param Block block
     */
    public Fighter(int location, int tenges, Block block){
        super(location, tenges, block);
        int x = block.getXPosition();
        int y = block.getYPosition();
        boolean isH = block.isHorizontal();
        if(isH){
            facade = new Rectangle(10, 10, x+20, (y-10)+5);
            roof = new Triangle(25, 10, (x+5)+20, (y-35)+5);
        }else if(!isH){
            facade = new Rectangle(10, 10, x+27, (y-10)+35);
            roof = new Triangle(25, 10, (x+5)+27, (y-35)+35);
        }
    }

}