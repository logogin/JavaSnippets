/**
 * NewLines.java
 *
 * @created Mar 7, 2013
 * @author Pavel Danchenko
 */
@Deprecated package logogin.idetool.format; // annotation on package is only allowed in package-info.java

public class Empty {}

@Deprecated class Example {
    @Deprecated static int[] fArray = { 1, 2, 3, 4, 5 };
    Listener fListener = new Listener() {};

    @Deprecated @Override public void bar(@SuppressWarnings("unused")
    int i) {
        @SuppressWarnings("unused") int k;
    }

    void foo() {
        ;;
        label: do {} while (false);
        for (;;) {}
    }
}

enum MyEnum {
    UNDEFINED(0) {}
}

enum EmptyEnum {}

@interface EmptyAnnotation {}