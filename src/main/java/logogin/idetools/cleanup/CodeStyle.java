package logogin.idetools.cleanup;
import java.awt.*;

/**
 * CodeStyle.java<br>
 * Tests:<br>
 *   if/for blocks<br>
 *   loop conversion<br>
 *   parentheses<br>
 *   final modifier<br>
 *
 * @created Mar 7, 2013
 * @author Pavel Danchenko
 */


public class CodeStyle {
    
    public void crazy_code() {
        int ids[] = new int[10];
        Object obj = new Object();
        
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        if (ids.length > 0) {
            System.out.println(ids[0]);
        } else 
            return;

        for (int i = 0; i < ids.length; i++) {
            double value= ids[i] / 2; 
            System.out.println(value);
        }

        boolean b= (i > 0 && i < 10 || i == 50);
    }
    
    private int i= 0;
    public void foo(int j) {
        int k, h;
        h= 0;
    }
    
}




