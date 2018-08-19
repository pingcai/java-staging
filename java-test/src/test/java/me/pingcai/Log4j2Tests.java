package me.pingcai;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangpingcai
 * @since 2018/8/19 17:32
 */
public class Log4j2Tests {

    private static final Logger logger = LoggerFactory.getLogger(Log4j2Tests.class);

    @Test
    public void testPrint() {
        String pattern = "this is a {} log.";
        logger.trace(pattern,"trace");
        logger.debug(pattern,"debug");
        logger.info(pattern,"info");
        logger.warn(pattern,"warn");
        logger.error(pattern,"error");
    }


}
