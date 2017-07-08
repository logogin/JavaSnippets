package logogin.idetool.format;

/**
 * Braces.java
 * 
 * @created Mar 7, 2013
 * @author Pavel Danchenko
 */
interface Empty {
}

enum MyEnum {
    UNDEFINED(0) {
        void foo() {
        }
    }
}

@interface SomeAnnotationType {
}

class Example {
    SomeClass fField = new SomeClass() {};
    int[] myArray = {1, 2, 3, 4, 5, 6};
    int[] emptyArray = new int[] {};

    Example() {
    }

    void bar(int p) {
        for (int i = 0; i < 10; i++) {
        }
        switch (p) {
            case 0:
                fField.set(0);
                break;
            case 1: {
                break;
            }
            default:
                fField.reset();
        }
    }
}