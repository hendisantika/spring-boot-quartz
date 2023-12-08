package com.hendisantika.springbootquartz.repository;

import com.hendisantika.springbootquartz.entity.SchedulerJobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-quartz
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/8/23
 * Time: 10:02
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface SchedulerRepository extends JpaRepository<SchedulerJobInfo, Long> {

    SchedulerJobInfo findByJobName(String jobName);
}
