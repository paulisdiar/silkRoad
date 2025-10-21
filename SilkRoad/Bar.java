
/**
 * Write a description of class Bar here.
 * 
 * @author Paula Díaz
 * @author Juan Pablo Vélez
 * @version 4
 */
public class Bar{
    // instance variables - replace the example below with your own
    private Rectangle backGround;
    private Rectangle filler;
    private int total;
    private int progress;
    private boolean isVisible;

    /**
     * Constructor for objects of class Bar
     */
    public Bar(){
        isVisible = false;
        backGround = new Rectangle(35, 205, 650, 425, UtilColors.getColor(UtilColors.awtColors.get(7)));
        filler = new Rectangle(35, 5, 645, 425, UtilColors.getColor(UtilColors.awtColors.get(2)));
    }
    
    /**
     * Activates the components of the bar
     */
    public void makeVisible(){
        isVisible = true;
        backGround.makeVisible();
        filler.makeVisible();
    }
    
    /**
     * Deactivates the components of the bar
     */
    public void makeInvisible(){
        isVisible = false;
        backGround.makeInvisible();
        filler.makeInvisible();
    }
    
    /**
     * Updates the bar
     * @param int total
     */
    public void updateTotal(int total){
        this.total = total;
    }
    
    /**
     * Updatest the progress
     * @int profit
     */
    public void updateProgress(int profit){
        progress = profit;
        if(profit > 0 && profit <= total){
            filler.changeSize(35, 5 + ((int) ((profit*100)/total)));
        }else if(profit > total){
            filler.changeSize(35, 205);
        }else if(profit < 0){
            filler.changeSize(35, 5);
        }
    }
    
    //Extra
    /**
     * Answers if the object is equal
     * @param Object obj
     * @return true
     * @override
     */
    public boolean equals(Object obj){
        if(obj instanceof Bar){
            Rectangle t = ((Bar) obj).getBackGround();
            Rectangle l = ((Bar) obj).getFiller();
            return (backGround.equals(t) && filler.equals(l)) ? true : false;
        }
        return false;
    }
    
    public Rectangle getBackGround(){
        return backGround;
    }
    
    public Rectangle getFiller(){
        return filler;
    }
    
}