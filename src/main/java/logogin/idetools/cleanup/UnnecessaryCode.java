package logogin.idetools.cleanup;
import java.awt.*;
import java.util.*;

/**
 * UnnecessaryCode.java<br>
 * Tests:<br>
 *   unused imports
 *   unused casts<br>
 *   unused private types/members/fields/constructors<br>
 *   unused $NON-NLS$ tags
 *
 * @created Mar 7, 2013
 * @author Pavel Danchenko
 */


class Example {
    private class Sub {}
    public Example(boolean b) {}
    private Example() {}
    private int fField;
    private void foo() {}
    public void bar() {
        int i= 10;
        
        Boolean b= (Boolean) Boolean.TRUE;
    }
    
    public String s; //$NON-NLS-1$
}


