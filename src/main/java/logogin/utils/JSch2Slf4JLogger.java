package logogin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSch2Slf4JLogger.java
 *
 * @created Mar 25, 2013
 * @author logogin
 */
public class JSch2Slf4JLogger implements com.jcraft.jsch.Logger {

    private static final Logger slf4jLogger = LoggerFactory.getLogger("com.jcraft.jsch");

    private static final int DEBUG_LEVEL_THRESHOLD = com.jcraft.jsch.Logger.DEBUG;
    private static final int INFO_LEVEL_THRESHOLD = com.jcraft.jsch.Logger.INFO;
    private static final int WARN_LEVEL_THRESHOLD = com.jcraft.jsch.Logger.WARN;

    @Override
    public boolean isEnabled(int level) {
        if (level <= DEBUG_LEVEL_THRESHOLD) {
            return slf4jLogger.isDebugEnabled();
        }
        if (level <= INFO_LEVEL_THRESHOLD) {
            return slf4jLogger.isInfoEnabled();
        }
        if (level <= WARN_LEVEL_THRESHOLD) {
            return slf4jLogger.isWarnEnabled();
        }

        return slf4jLogger.isErrorEnabled();
    }

    @Override
    public void log(int level, String message) {
        if (level <= DEBUG_LEVEL_THRESHOLD) {
            slf4jLogger.debug(message);
        } else if (level <= INFO_LEVEL_THRESHOLD) {
            slf4jLogger.info(message);
        } else if (level <= WARN_LEVEL_THRESHOLD) {
            slf4jLogger.warn(message);
        } else {
            slf4jLogger.error(message);
        }
    }
}
