package logogin.idetools.cleanup.target;

import java.awt.*;
import java.util.*;

/**
 * MissingCode.java<br>
 * Tests:<br>
 * missing <code>@Deprecated, @Override</code> annotations<br>
 * missing serial version ID<br>
 * missing unimplemented method<br>
 * 
 * @created Mar 7, 2013
 * @author Pavel Danchenko
 */

class S {
    /**
     * @deprecated
     */
    @Deprecated
    public void foo() {
    }
}

class SSub extends S implements Runnable {
    @Override
    public void foo() {
    }

    @Override
    public void run() {
    }
}

class K implements java.io.Serializable {
}

public class Face implements Comparator {
}
