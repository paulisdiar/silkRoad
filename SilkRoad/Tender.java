/**
 * A robot that only steels half of the store.
 * 
 * @author Paula Diaz
 * @author Juan Pablo Vélez
 * @version 4
 */
public class Tender extends Robot{
    /**
     * Constructor for objects of class Tender
     * @param int inLocation
     * @param Block block
     */
    public Tender(int inLocation, Block inBlock){
        super(inLocation, inBlock);
        int x = inBlock.getXPosition();
        int y = inBlock.getYPosition();
        boolean isH = inBlock.isHorizontal();
        if(isH){
            head = new Rectangle(7, 7, x+5, y+7, UtilColors.getColor(UtilColors.awtColors.get(24)));
            body = new Rectangle(10, 7, x+5, (y+7)+7);
        }else if(!isH){
            head = new Rectangle(7, 7, x+5, y+20, UtilColors.getColor(UtilColors.awtColors.get(24)));
            body = new Rectangle(10, 7, x+5, (y+7)+20);
        }
    }
}