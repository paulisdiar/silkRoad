/**
 * A type of robot that only moves right
 * 
 * @author Paula Diaz
 * @author Juan Pablo Vélez
 * @version 4
 */
public class NeverBack extends Robot{
    /**
     * Constructor for objects of class NeverBack
     * @param int inLocation
     * @param Block block
     */
    public NeverBack(int inLocation, Block inBlock){
        super(inLocation, inBlock);
        int x = inBlock.getXPosition();
        int y = inBlock.getYPosition();
        boolean isH = inBlock.isHorizontal();
        if(isH){
            head = new Rectangle(10, 15, (x-4)+5, y+7, UtilColors.getColor(UtilColors.awtColors.get(33)));
            body = new Rectangle(10, 7, x+5, (y+7)+7);
        }else if(!isH){
            head = new Rectangle(10, 15, (x-4)+5, y+20, UtilColors.getColor(UtilColors.awtColors.get(33)));
            body = new Rectangle(10, 7, x+5, (y+7)+20);
        }
    }
}