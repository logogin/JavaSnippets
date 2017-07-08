package logogin.idetool.format;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * WhiteSpace.java
 * 
 * @created Mar 7, 2013
 * @author Pavel Danchenko
 */

class MyClass implements I0, I1, I2 {
    Object AnonClass = new AnonClass() {
        void foo(Some s) {
        }
    };

    int a = 0, b = 1, c = 2, d = 3;

    MyClass() throws E0, E1 {
        this(0, 0, 0);
    }

    MyClass(int x, int y, int z) throws E0, E1 {
        super(x, y, z, true);
    }

    void foo() throws E0, E1 {
    }

    void bar(int x, int y) throws E0, E1 {
    }

    void format(String s, Object... args) {
    }

    void l() {
        label: for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                continue label;
            }
        }
    }

    @Annot(x = 23, y = -3)
    public class A {
    }

    enum MyEnum {
        GREEN(0, 1), RED() {
            void process() {
            }
        }
    }

    @interface MyAnnotation {
        String value();
    }

    @interface OtherAnnotation {
    }

    void statements() {

        int a = 4;
        foo();
        bar(x, y);

        if (true) {
            return 1;
        } else {
            return 2;
        }

        if (condition) {
            return foo;
        } else {
            return bar;
        }

        for (int i = 0, j = array.length; i < array.length; i++, j--) {
        }
        for (String s : names) {
        }

        switch (number) {
            case RED:
                return GREEN;
            case GREEN:
                return BLUE;
            case BLUE:
                return RED;
            default:
                return BLACK;
        }

        while (condition) {
        }
        ;
        do {
        } while (condition);

        synchronized (list) {
            list.add(element);
        }

        //try with resource
        try (FileReader reader1 = new FileReader("file1"); FileReader reader2 = new FileReader("file2")) {
        }

        try {
            number=Integer.parseInt(value);
        } catch (NumberFormatException e) {
        }

        assert condition : reportError();

        return (o);

        throw (e);
    }

    void expressions() {
        foo();
        bar(x, y);

        String str = new String();
        Point point = new Point(x, y);

        List list = new ArrayList();
        int a = -4 + -9;
        b = a++ / --number;
        c += 4;
        boolean value = true && false;

        result = (a * (b + c + d) * (e + f));

        String s = ((String) object);

        String value = condition ? TRUE : FALSE;
    }

    void arrays() {
        int[] array0 = new int[] {};
        int[] array1 = new int[] {1, 2, 3};
        int[] array2 = new int[3];

        array[i].foo();
    }

    class MyGenericType<S, T extends Element & List> {
    }

    void generics() {

        Map<String, Element> map = new HashMap<String, Element>();

        x.<String, Element> foo();

        Map<X<?>, Y<? extends K, ? super V>> t;
    }
}
