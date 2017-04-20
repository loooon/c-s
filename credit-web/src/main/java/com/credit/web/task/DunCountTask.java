package com.credit.web.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.credit.common.util.RandomUtil;
import com.credit.service.DunNumberCountService;

/**
 * Created by wangjunling on 2017/3/22.
 */
@Component
public class DunCountTask
{
    private Logger logger = LoggerFactory.getLogger(DunCountTask.class);

    @Resource
    private DunNumberCountService dunNumberCountService;

    @Scheduled(initialDelay = 10000, fixedRateString = "${task.dun.count.fixed.rate}")
    public void job()
    {
        int add = RandomUtil.genRandomNumber(1, 3);
        logger.debug("已发现催收号码增加数值：" + add);
        dunNumberCountService.addCountFind(add);

        int addC = RandomUtil.genRandomNumber(1, 10);
        logger.debug("已覆盖催收号码增加数值：" + addC);
        dunNumberCountService.addCountCoverage(addC);
    }

}
