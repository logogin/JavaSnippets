package logogin.idetools.cleanup.target;

/**
 * MembersAccess.java<br>
 * Tests:<br>
 * <code>this</code> qualifier fields<br>
 * <code>this</code> qualifier methods<br>
 * class qualifier field/method<br>
 * 
 * @created Mar 7, 2013
 * @author Pavel Danchenko
 */

class E {
    public static int NUMBER;

    public static void set(int i) {
        NUMBER = i;
    }

    public void reset() {
        set(0);
    }
}

class ESub extends E {
    @Override
    public void reset() {
        E.NUMBER = 0;
    }
}

public class MembersAccess {

    private int value;

    public int get() {
        return this.value + value;
    }

    public int getZero() {
        return this.get() - get();
    }

    public void dec() {
        E.NUMBER--;
    }
}
