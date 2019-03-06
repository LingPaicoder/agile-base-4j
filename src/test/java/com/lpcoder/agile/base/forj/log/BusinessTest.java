package com.lpcoder.agile.base.forj.log;

import com.lpcoder.agile.base.forj.util.RandomUtil;

/**
 * @author liurenpeng
 * @date Created in 19-3-6
 */
public class BusinessTest {

    private static final LoggerDecorator LOG = BaseLoggerDecorator.getLogger(BusinessTest.class);

    public static void main(String[] args) {
        for (int i = 1; i <= 1000; i++) {
            LOG.setLogId(RandomUtil.shortUUID())
                    .setBusinessPrefix("具体业务描述")
                    .register("currNum", i)
                    .info("START");

            if (i % 2 == 0) {
                LOG.info("currNum是偶数");
            }
            if (i % 3 == 0) {
                LOG.info("currNum是3的倍数");
            }

            LOG.info("END");
            LOG.clear();
        }
    }

}
