package com.lpcoder.agile.base.forj.log;

import com.lpcoder.agile.base.forj.util.RandomUtil;

/**
 * @author liurenpeng
 * @date Created in 19-3-6
 */
public class LOGTest {

    private static final LoggerDecorator LOG = BaseLoggerDecorator.getLogger(LOGTest.class);

    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 1000; i++) {
            // 模仿前置拦截器
            LOG.setLogId(RandomUtil.shortUUID())
                    .setBusinessPrefix("具体业务描述")
                    .register("currNum", i)
                    .info("START");
            long startTime = System.currentTimeMillis();

            // 模仿业务代码
            LOG.register("userId", RandomUtil.nextInt(1000, 10000));
            Thread.sleep(RandomUtil.nextInt(5, 10));
            if (i % 2 == 0) {
                LOG.info("currNum是偶数");
            }
            if (i % 3 == 0) {
                LOG.info("currNum是3的倍数");
            }

            // 模仿后置拦截器
            long endTime = System.currentTimeMillis();
            LOG.register("usedTime", endTime - startTime).info("END");
            LOG.clear();
        }
    }

}
