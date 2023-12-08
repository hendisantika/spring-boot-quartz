package com.hendisantika.springbootquartz.service;

import com.hendisantika.springbootquartz.component.JobScheduleCreator;
import com.hendisantika.springbootquartz.entity.SchedulerJobInfo;
import com.hendisantika.springbootquartz.repository.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerMetaData;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-quartz
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/8/23
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class SchedulerJobService {

    private final Scheduler scheduler;

    private final SchedulerFactoryBean schedulerFactoryBean;

    private final SchedulerRepository schedulerRepository;

    private final ApplicationContext context;

    private final JobScheduleCreator scheduleCreator;

    public SchedulerMetaData getMetaData() throws SchedulerException {
        SchedulerMetaData metaData = scheduler.getMetaData();
        return metaData;
    }

    public List<SchedulerJobInfo> getAllJobList() {
        return schedulerRepository.findAll();
    }

}
