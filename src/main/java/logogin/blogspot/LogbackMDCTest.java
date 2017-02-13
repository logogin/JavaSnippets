package logogin.blogspot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * LogbackMDCTest.java
 *
 * @author logogin
 * @date Sep 3, 2014
 *
 */
public class LogbackMDCTest {

    private static final Logger logger = LoggerFactory.getLogger(LogbackMDCTest.class);

    public static void main(String ... args) {
        MDC.put("emp", "test_emp");
        MDC.put("org", "test_org");
        logger.info("This line should have MDC values");

        MDC.put("emp", "");
        MDC.put("org", "");
        logger.info("This line should have no empty MDC keys");
    }

}
