package com.hendisantika.springbootquartz.controller;

import com.hendisantika.springbootquartz.service.SchedulerJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-quartz
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/8/23
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final SchedulerJobService scheduleJobService;
}
