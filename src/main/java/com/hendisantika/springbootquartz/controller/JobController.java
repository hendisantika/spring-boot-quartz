package com.hendisantika.springbootquartz.controller;

import com.hendisantika.springbootquartz.service.SchedulerJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
