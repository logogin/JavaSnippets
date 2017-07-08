package logogin.idetool.format;

/**
 * ControlStatements.java
 * 
 * @created Mar 7, 2013
 * @author Pavel Danchenko
 */
class Example {
    void bar() {
        do {
        } while (true);
        try {
        } catch (Exception e) {
        } finally {
        }
    }

    void foo2() {
        if (true) {
            return;
        }
        if (true) {
            return;
        } else if (false) {
            return;
        } else {
            return;
        }
    }

    void foo(int state) {
        if (true) {
            return;
        }
        if (true) {
            return;
        } else if (false) {
            return;
        } else {
            return;
        }
    }
}