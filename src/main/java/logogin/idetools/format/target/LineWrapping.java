package logogin.idetool.format;

/**
 * LineWrapping.java
 * 
 * @created Mar 7, 2013
 * @author Pavel Danchenko
 */
/**
 * Element-value pairs
 */
@MyAnnotation(value1 = "this is an example", value2 = "of an annotation", value3 = "with several arguments", value4 = "which may need to be wrapped",
        value4 = "which may need to be wrapped", value4 = "which may need to be wrapped")
class Example {
}

/**
 * 'extends' clause
 */
class Example extends
        OtherClasszzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz {
}

/**
 * 'implements' clause
 */
class Example implements I1, I2, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3, I3,
        I3, I3, I3, I3, I3, I3, I3, I3 {
}

/**
 * Parameters
 */
class Example {
    Example(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg6, int arg6, int arg6, int arg6, int arg6, int arg6, int arg6, int arg6,
            int arg6, int arg6, int arg6, int arg6) {
        this();
    }

    Example() {
    }
}

/**
 * 'throws' clause
 */
class Example {
    Example() throws FirstException, SecondException, ThirdException, ThirdException, ThirdException, ThirdException, ThirdException, ThirdException,
            ThirdException, ThirdException, ThirdException {
        return Other.doSomething();
    }
}

/**
 * Declaration
 */
class Example {
    public final synchronized java.lang.String a_method_with_a_loooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong_name() {
    }
}

/**
 * Parameters
 */
class Example {
    void foo(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg6, int arg6, int arg6, int arg6, int arg6, int arg6, int arg6, int arg6,
            int arg6, int arg6, int arg6, int arg6) {
    }
}

/**
 * 'throws' clause
 */
class Example {
    int foo() throws FirstException, SecondException, ThirdException, ThirdException, ThirdException, ThirdException, ThirdException, ThirdException,
            ThirdException, ThirdException, ThirdException {
        return Other.doSomething();
    }
}

/**
 * Constants
 */
enum Example {
    CANCELLED, RUNNING, WAITING, FINISHED, FINISHED, FINISHED, FINISHED, FINISHED, FINISHED, FINISHED, FINISHED, FINISHED, FINISHED, FINISHED, FINISHED,
    FINISHED, FINISHED, FINISHED, FINISHED
}

enum Example {
    GREEN(0, 255, 0), RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0),
    RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0)
}

/**
 * 'implements' clause
 */
enum Example implements A, B, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C, C,
        C, C, C, C, C, C, C, C, C, C, C {
}

/**
 * Constant arguments
 */
enum Example {
    GREEN(0, 255, 0), RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0),
    RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0), RED(255, 0, 0)
}

/**
 * Arguments
 */
class Example {
    void foo() {
        Other.bar(
                100,
                nested(200, 300, 400, 500, 600, 700, 800, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900,
                        900, 900, 900, 900, 900, 900, 900));
    }
}

/**
 * Qualified invocations
 */
class Example {
    int foo(Some a) {
        return a.getFirst().getFirst().getFirst().getFirst().getFirst().getFirst().getFirst().getFirst().getFirst().getFirst().getFirst().getFirst().getFirst()
                .getFirst().getFirst().getFirst().getFirst();
    }
}

/**
 * Explicit constructor invocations
 */
class Example extends AnotherClass {
    Example() {
        super(100, 200, 300, 400, 500, 600, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700,
                700, 700, 700, 700, 700, 700, 700);
    }
}

/**
 * Object allocation arguments
 */
class Example {
    SomeClass foo() {
        return new SomeClass(100, 200, 300, 400, 500, 600, 700, 800, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900,
                900, 900, 900, 900);
    }
}

/**
 * Qualified object allocation arguments
 */
class Example {
    SomeClass foo() {
        return SomeOtherClass.new SomeClass(100, 200, 300, 400, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,
                500, 500, 500, 500, 500);
    }
}

/**
 * Binary expressions
 */
class Example extends AnotherClass {
    int foo() {
        int sum = 100 + 200 + 300 + 400 + 500 + 600 + 700 + 800 + 800 + 800 + 800 + 800 + 800 + 800 + 800 + 800 + 800 + 800 + 800 + 800 + 800 + 800 + 800 + 800
                + 800 + 800 + 800 + 800 + 800;
        int product = 1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 10 * 10 * 10 * 10 * 10 * 10 * 10 * 10 * 10 * 10 * 10 * 10 * 10 * 10 * 10 * 10 * 10 * 10 * 10
                * 10 * 10 * 10 * 10 * 10;
        boolean val = true && false && true && false && true && true && true && true && true && true && true && true && true && true && true && true && true
                && true && true && true && true && true;
        return product / sum / sum / sum / sum / sum / sum / sum / sum / sum / sum / sum / sum / sum / sum / sum / sum / sum / sum / sum / sum / sum / sum
                / sum / sum / sum / sum / sum;
    }
}

/**
 * Conditionals
 */
class Example extends AnotherClass {
    int Example(boolean Argument) {
        return argument ? 100000 : argument ? 100000 : argument ? 100000 : argument ? 100000 : argument ? 100000 : argument ? 100000 : argument ? 100000
                : argument ? 100000 : argument ? 100000 : 200000;
    }
}

/**
 * Array initializers
 */
class Example {
    int[] fArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
            12};
}

/**
 * Assignments
 */
class Example {
    private static final String string = "TextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextText";

    void foo() {
        for (int i = 0; i < 10; i++) {
        }
        String s;
        s = "TextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextText";
    }
}

/**
 * Compact 'if else'
 */
class Example {
    int foo(int argument) {
        if (argument == 0) {
            return 0;
        }
        if (argument == 1) {
            return 42;
        } else if (argument == 1) {
            return 43;
        } else if (argument == 1) {
            return 43;
        } else if (argument == 1) {
            return 43;
        } else if (argument == 1) {
            return 43;
        } else if (argument == 1) {
            return 43;
        } else if (argument == 1) {
            return 43;
        } else {
            return 43;
        }
    }
}

/**
 * 'try-with-resources'
 */
class Example {
    void foo() {
        try (FileReader reader1 = new FileReader("file1"); FileReader reader2 = new FileReader("file2")) {
        }
    }
}

/**
 * 'multi-catch'
 */
class Example {
    void foo() {
        try {
        } catch (IllegalArgumentException | NullPointerException | ClassCastException e) {
            e.printStackTrace();
        }
    }
}
