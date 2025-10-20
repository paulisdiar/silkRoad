
/**
 * Write a description of class Bar here.
 * 
 * @author Paula Díaz
 * @author Juan Pablo Vélez
 * @version 4
 */
public class Bar{
    // instance variables - replace the example below with your own
    private Rectangle total;
    private Rectangle loader;
    private int tot;
    private int progress;
    private boolean isVisible;

    /**
     * Constructor for objects of class Bar
     */
    public Bar(){
        isVisible = false;
        total = new Rectangle(35, 205, 650, 425, UtilColors.getColor(UtilColors.awtColors.get(7)));
        loader = new Rectangle(35, 5, 645, 425, UtilColors.getColor(UtilColors.awtColors.get(2)));
    }
    
    /**
     * Activates the components of the bar
     */
    public void makeVisible(){
        isVisible = true;
        total.makeVisible();
        loader.makeVisible();
    }
    
    /**
     * Deactivates the components of the bar
     */
    public void makeInvisible(){
        isVisible = false;
        total.makeInvisible();
        loader.makeInvisible();
    }
    
    /**
     * Updates the bar
     * @param int total
     */
    public void updateTotal(int total){
        tot = total;
    }
    
    /**
     * Updatest the progress
     * @int profit
     */
    public void updateProgress(int profit){
        progress = profit;
        if(profit > 0 && profit <= tot){
            loader.changeSize(35, 5 + ((int) ((profit*100)/tot)));
        }else if(profit > tot){
            loader.changeSize(35, 205);
        }else if(profit < 0){
            loader.changeSize(35, 5);
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
            Rectangle t = ((Bar) obj).getTotal();
            Rectangle l = ((Bar) obj).getLoader();
            return (total.equals(t) && loader.equals(l)) ? true : false;
        }
        return false;
    }
    
    public Rectangle getTotal(){
        return total;
    }
    
    public Rectangle getLoader(){
        return loader;
    }
    
}