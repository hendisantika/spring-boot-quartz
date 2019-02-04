package com.hendisantika.springbootquartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-quartz
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-02-04
 * Time: 18:24
 */
public class ExampleJob extends QuartzJobBean {

    @Autowired
    private ApplicationContext applicationContext;

    private static final Logger LOG = LoggerFactory.getLogger(ExampleJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        LOG.info("Example Job executed. url={}", context.getJobDetail().getJobDataMap().get("url"));
    }
}