/**
 * BlankLines.java
 *
 * @created Mar 7, 2013
 * @author Pavel Danchenko
 */
package logogin.idetool.format;
import java.util.List;
import java.util.Vector;
import java.net.Socket;
public class Another {
}
public class Example {
    public static class Pair {
        public String first;
        public String second;
        // Between here...
        // ...and here are 10 blank lines
    };
    private LinkedList fList;
    public int counter;
    public Example(LinkedList list) {
        fList = list;
        counter = 0;
    }
    public void push(Pair p) {
        fList.add(p);
        ++counter;
    }
    public Object pop() {
        --counter;
        return (Pair) fList.getLast();
    }
}