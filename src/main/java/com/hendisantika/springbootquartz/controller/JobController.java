package com.hendisantika.springbootquartz.controller;

import com.hendisantika.springbootquartz.entity.Message;
import com.hendisantika.springbootquartz.entity.SchedulerJobInfo;
import com.hendisantika.springbootquartz.service.SchedulerJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.quartz.SchedulerMetaData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-quartz
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/8/23
 * Time: 10:13
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class JobController {

    private final SchedulerJobService scheduleJobService;

    @RequestMapping(value = "/saveOrUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    public Object saveOrUpdate(SchedulerJobInfo job) {
        log.info("params, job = {}", job);
        Message message = Message.failure();
        try {
            scheduleJobService.saveOrUpdate(job);
            message = Message.success();
        } catch (Exception e) {
            message.setMsg(e.getMessage());
            log.error("updateCron ex:", e);
        }
        return message;
    }

    @GetMapping("/metaData")
    public Object metaData() throws SchedulerException {
        SchedulerMetaData metaData = scheduleJobService.getMetaData();
        return metaData;
    }

    @GetMapping("/getAllJobs")
    public Object getAllJobs() throws SchedulerException {
        List<SchedulerJobInfo> jobList = scheduleJobService.getAllJobList();
        return jobList;
    }

    @RequestMapping(value = "/runJob", method = {RequestMethod.GET, RequestMethod.POST})
    public Object runJob(SchedulerJobInfo job) {
        log.info("params, job = {}", job);
        Message message = Message.failure();
        try {
            scheduleJobService.startJobNow(job);
            message = Message.success();
        } catch (Exception e) {
            message.setMsg(e.getMessage());
            log.error("runJob ex:", e);
        }
        return message;
    }

    @RequestMapping(value = "/pauseJob", method = {RequestMethod.GET, RequestMethod.POST})
    public Object pauseJob(SchedulerJobInfo job) {
        log.info("params, job = {}", job);
        Message message = Message.failure();
        try {
            scheduleJobService.pauseJob(job);
            message = Message.success();
        } catch (Exception e) {
            message.setMsg(e.getMessage());
            log.error("pauseJob ex:", e);
        }
        return message;
    }

    @RequestMapping(value = "/resumeJob", method = {RequestMethod.GET, RequestMethod.POST})
    public Object resumeJob(SchedulerJobInfo job) {
        log.info("params, job = {}", job);
        Message message = Message.failure();
        try {
            scheduleJobService.resumeJob(job);
            message = Message.success();
        } catch (Exception e) {
            message.setMsg(e.getMessage());
            log.error("resumeJob ex:", e);
        }
        return message;
    }

    @RequestMapping(value = "/deleteJob", method = {RequestMethod.GET, RequestMethod.POST})
    public Object deleteJob(SchedulerJobInfo job) {
        log.info("params, job = {}", job);
        Message message = Message.failure();
        try {
            scheduleJobService.deleteJob(job);
            message = Message.success();
        } catch (Exception e) {
            message.setMsg(e.getMessage());
            log.error("deleteJob ex:", e);
        }
        return message;
    }
}
