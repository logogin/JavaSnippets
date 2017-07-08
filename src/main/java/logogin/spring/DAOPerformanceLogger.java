package logogin.spring;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 *
 * @created Jun 1, 2011
 * @author logogin
 */

@Component
@Aspect
public class DAOPerformanceLogger {

    private static final Logger log = LoggerFactory.getLogger(DAOPerformanceLogger.class);

    @Around("execution(* logogin.app.dao..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object returnValue = joinPoint.proceed();
        stopWatch.stop();

        String targetClassName = joinPoint.getTarget().toString();

        log.debug("{}.{}({});{};ms", new Object[] {
                targetClassName.lastIndexOf(".") + 1, targetClassName.lastIndexOf("@"),
                joinPoint.getSignature().getName(),
                StringUtils.join(joinPoint.getArgs(), ", "),
                stopWatch.getTime()
        });

        return returnValue;
    }
}
