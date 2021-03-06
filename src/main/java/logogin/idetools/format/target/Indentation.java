package logogin.idetool.format;

/**
 * Indentation.java
 * 
 * @created Mar 7, 2013
 * @author Pavel Danchenko
 */
class Example {
    int[] myArray = {1, 2, 3, 4, 5, 6};
    int theInt = 1;
    String someString = "Hello";
    double aDouble = 3.0;

    void foo(int a, int b, int c, int d, int e, int f) {
        switch (a) {
            case 0:
                Other.doFoo();
                break;
            default:
                Other.doBaz();
        }
    }

    void bar(List v) {
        for (int i = 0; i < 10; i++) {
            v.add(new Integer(i));
        }
    }
}

enum MyEnum {
    UNDEFINED(0) {
        void foo() {
        }
    }
}

@interface MyAnnotation {
    int count() default 1;
}