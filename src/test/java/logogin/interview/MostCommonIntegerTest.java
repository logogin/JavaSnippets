
import java.io.PrintStream;
import java.util.Arrays;

/**
 * MostCommonSum.java
 * This is small test suite to batch test execution.
 * TestCase - contains test dataset and expected result; executes single test; validates test result.
 * TestCase.TestResult - contains test failure flag and exception caught during execution.
 *
 * @created May 27, 2012
 * @author Pavel Danchenko
 */
public class MostCommonIntegerTest {

    /**
     * Wrapper around single test. The expected test result could be integer or exception.
     * It also could print nice test title.
     */
    public static class TestCase {
        private String title;
        private Class<? extends Throwable> expectedThrowable;
        private int expected;
        private int[] input;

        /**
         * Wrapper around test result. Can delegate exception stack trace to provided PrintStream
         */
        public class TestResult {
            private boolean failed;
            private Throwable ex;

            public TestResult(boolean failed, Throwable ex) {
                this.failed = failed;
                this.ex = ex;
            }

            public boolean isFailed() {
                return failed;
            }

            public void printStackTrace(PrintStream ps) {
                if ( null != ex ) {
                    ex.printStackTrace(ps);
                }
            }
        }

        /**
         * Creates test with expected integer result
         */
        public TestCase(String title, int expected, int[] input) {
            this.title = title;
            this.expected = expected;
            this.input = input;
        }

        /**
         * Creates test with expected exception
         */
        public TestCase(String title, Class<? extends Throwable> expectedThrowable, int[] input) {
            this.title = title;
            this.expectedThrowable = expectedThrowable;
            this.input = input;
        }

        /**
         * Executes actual test and validates output. Each time new instance of MostCommonInteger created.
         * Stores exception (if caught) and returns TestResult
         */
        public TestResult execute() {
            boolean failed = false;
            Throwable caught = null;
            try {
                int actual = new MostCommonInteger().sumFast(input);
                assertEquals(actual, expected);
            } catch (Throwable ex) {
                if ( ex instanceof AssertionError
                        || (null != expectedThrowable && !expectedThrowable.isInstance(ex)) ) {
                    failed = true;
                }
                caught = ex;
            }
            return new TestResult(failed, caught);
        }

        public String getTitle() {
            return title;
        }

        /**
         * Simple factory to provide nice title
         */
        public static TestCase valueOf(int expected, int[] input) {
            return new TestCase(Arrays.toString(input) + " -> " + expected, expected, input);
        }

        /**
         * Simple factory to provide nice title
         */
        public static TestCase valueOf(Class<? extends Throwable> expectedThrowable, int[] input) {
            return new TestCase(Arrays.toString(input) + " -> " + expectedThrowable.getSimpleName(), expectedThrowable, input);
        }
    }

    /**
     * Simple validation routine
     */
    public static void assertEquals(int actual, int expected) {
        if ( actual != expected ) {
            throw new AssertionError("Expected [" + expected + "], but was [" + actual + "]");
        }
    }

    /**
     * Actual test cases
     */
    private static final TestCase[] TEST_CASES = new TestCase[] {
        /* null passed */
        TestCase.valueOf(IllegalArgumentException.class, null)
        /* empty array */
        , TestCase.valueOf(IllegalArgumentException.class, new int[] {})
        /* example case 1 */
        , TestCase.valueOf(8, new int[] {2,4,5,6,4})
        /* example case 2 */
        , TestCase.valueOf(3, new int[] {1,2,1,3,1})
        /* stress cases */
        , TestCase.valueOf(0, new int[] {0})
        , TestCase.valueOf(1, new int[] {1})
        , TestCase.valueOf(2, new int[] {1,1})
        , TestCase.valueOf(0, new int[] {0,2,0,3,0})
        /* arrays with negatives */
        , TestCase.valueOf(-1, new int[] {-1})
        , TestCase.valueOf(-1, new int[] {-1,-2,-3,1})
        , TestCase.valueOf(0, new int[] {-1,0,0,-1})
        , TestCase.valueOf(-3, new int[] {-1,0,0,-1,-1})
        /* several candidates for the most common */
        , TestCase.valueOf(2, new int[] {1,1,2,2,3,3})
        /* several (negative) candidates for the most common */
        , TestCase.valueOf(-8, new int[] {-4, -2, -3,-4, -2})
    };

    /**
     * Just loops through TestCase-s, executes single one and prints out TestResult.
     */
    public static void main(String[] args) {
        boolean failed = false;
        for ( TestCase test : TEST_CASES ) {
            System.out.print("Test [" + test.getTitle() + "]...");
            TestCase.TestResult result = test.execute();
            if ( !result.isFailed() ) {
                System.out.println("Passed");
            } else {
                failed = true;
                System.out.println("Failed");
                result.printStackTrace(System.out);
            }
        }
        System.out.println("Test: " + (failed ? "Failed" : "Success"));
        System.exit(0);
    }

}
