-- Note, Quartz depends on row-level locking which means you must use the MVCC=TRUE
-- setting on your H2 database, or you will experience dead-locks
--
--
-- In your Quartz properties file, you'll need to set
-- org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.StdJDBCDelegate

CREATE TABLE qrtz_calendars
(
    sched_name    VARCHAR(120) NOT NULL,
    calendar_name VARCHAR(200) NOT NULL,
    calendar      image        NOT NULL
);

CREATE TABLE qrtz_cron_triggers
(
    sched_name      VARCHAR(120) NOT NULL,
    trigger_name    VARCHAR(200) NOT NULL,
    trigger_group   VARCHAR(200) NOT NULL,
    cron_expression VARCHAR(120) NOT NULL,
    time_zone_id    VARCHAR(80)
);

CREATE TABLE qrtz_fired_triggers
(
    sched_name        VARCHAR(120) NOT NULL,
    entry_id          VARCHAR(95)  NOT NULL,
    trigger_name      VARCHAR(200) NOT NULL,
    trigger_group     VARCHAR(200) NOT NULL,
    instance_name     VARCHAR(200) NOT NULL,
    fired_time        bigint       NOT NULL,
    sched_time        bigint       NOT NULL,
    priority          INTEGER      NOT NULL,
    state             VARCHAR(16)  NOT NULL,
    job_name          VARCHAR(200) NULL,
    job_group         VARCHAR(200) NULL,
    is_nonconcurrent  boolean NULL,
    requests_recovery boolean NULL
);

CREATE TABLE qrtz_paused_trigger_grps
(
    sched_name    VARCHAR(120) NOT NULL,
    trigger_group VARCHAR(200) NOT NULL
);

CREATE TABLE qrtz_scheduler_state
(
    sched_name        VARCHAR(120) NOT NULL,
    instance_name     VARCHAR(200) NOT NULL,
    last_checkin_time bigint       NOT NULL,
    checkin_interval  bigint       NOT NULL
);

CREATE TABLE qrtz_locks
(
    sched_name VARCHAR(120) NOT NULL,
    lock_name  VARCHAR(40)  NOT NULL
);

CREATE TABLE qrtz_job_details
(
    sched_name        VARCHAR(120) NOT NULL,
    job_name          VARCHAR(200) NOT NULL,
    job_group         VARCHAR(200) NOT NULL,
    description       VARCHAR(250) NULL,
    job_class_name    VARCHAR(250) NOT NULL,
    is_durable        boolean      NOT NULL,
    is_nonconcurrent  boolean      NOT NULL,
    is_update_data    boolean      NOT NULL,
    requests_recovery boolean      NOT NULL,
    job_data          image NULL
);

CREATE TABLE qrtz_simple_triggers
(
    sched_name      VARCHAR(120) NOT NULL,
    trigger_name    VARCHAR(200) NOT NULL,
    trigger_group   VARCHAR(200) NOT NULL,
    repeat_count    bigint       NOT NULL,
    repeat_interval bigint       NOT NULL,
    times_triggered bigint       NOT NULL
);

CREATE TABLE qrtz_simprop_triggers
(
    sched_name    VARCHAR(120) NOT NULL,
    trigger_name  VARCHAR(200) NOT NULL,
    trigger_group VARCHAR(200) NOT NULL,
    str_prop_1    VARCHAR(512) NULL,
    str_prop_2    VARCHAR(512) NULL,
    str_prop_3    VARCHAR(512) NULL,
    int_prop_1    INTEGER NULL,
    int_prop_2    INTEGER NULL,
    long_prop_1   bigint NULL,
    long_prop_2   bigint NULL,
    dec_prop_1    NUMERIC(13, 4) NULL,
    dec_prop_2    NUMERIC(13, 4) NULL,
    bool_prop_1   boolean NULL,
    bool_prop_2   boolean NULL
);

CREATE TABLE qrtz_blob_triggers
(
    sched_name    VARCHAR(120) NOT NULL,
    trigger_name  VARCHAR(200) NOT NULL,
    trigger_group VARCHAR(200) NOT NULL,
    blob_data     image NULL
);

CREATE TABLE qrtz_triggers
(
    sched_name     VARCHAR(120) NOT NULL,
    trigger_name   VARCHAR(200) NOT NULL,
    trigger_group  VARCHAR(200) NOT NULL,
    job_name       VARCHAR(200) NOT NULL,
    job_group      VARCHAR(200) NOT NULL,
    description    VARCHAR(250) NULL,
    next_fire_time bigint NULL,
    prev_fire_time bigint NULL,
    priority       INTEGER NULL,
    trigger_state  VARCHAR(16)  NOT NULL,
    trigger_type   VARCHAR(8)   NOT NULL,
    start_time     bigint       NOT NULL,
    end_time       bigint NULL,
    calendar_name  VARCHAR(200) NULL,
    misfire_instr  SMALLINT NULL,
    job_data       image NULL
);

ALTER TABLE qrtz_calendars
    ADD
        CONSTRAINT pk_qrtz_calendars PRIMARY KEY
            (
             sched_name,
             calendar_name
                );

ALTER TABLE qrtz_cron_triggers
    ADD
        CONSTRAINT pk_qrtz_cron_triggers PRIMARY KEY
            (
             sched_name,
             trigger_name,
             trigger_group
                );

ALTER TABLE qrtz_fired_triggers
    ADD
        CONSTRAINT pk_qrtz_fired_triggers PRIMARY KEY
            (
             sched_name,
             entry_id
                );

ALTER TABLE qrtz_paused_trigger_grps
    ADD
        CONSTRAINT pk_qrtz_paused_trigger_grps PRIMARY KEY
            (
             sched_name,
             trigger_group
                );

ALTER TABLE qrtz_scheduler_state
    ADD
        CONSTRAINT pk_qrtz_scheduler_state PRIMARY KEY
            (
             sched_name,
             instance_name
                );

ALTER TABLE qrtz_locks
    ADD
        CONSTRAINT pk_qrtz_locks PRIMARY KEY
            (
             sched_name,
             lock_name
                );

ALTER TABLE qrtz_job_details
    ADD
        CONSTRAINT pk_qrtz_job_details PRIMARY KEY
            (
             sched_name,
             job_name,
             job_group
                );

ALTER TABLE qrtz_simple_triggers
    ADD
        CONSTRAINT pk_qrtz_simple_triggers PRIMARY KEY
            (
             sched_name,
             trigger_name,
             trigger_group
                );

ALTER TABLE qrtz_simprop_triggers
    ADD
        CONSTRAINT pk_qrtz_simprop_triggers PRIMARY KEY
            (
             sched_name,
             trigger_name,
             trigger_group
                );

ALTER TABLE qrtz_triggers
    ADD
        CONSTRAINT pk_qrtz_triggers PRIMARY KEY
            (
             sched_name,
             trigger_name,
             trigger_group
                );

ALTER TABLE qrtz_cron_triggers
    ADD
        CONSTRAINT fk_qrtz_cron_triggers_qrtz_triggers FOREIGN KEY
            (
             sched_name,
             trigger_name,
             trigger_group
                ) REFERENCES qrtz_triggers (
                                            sched_name,
                                            trigger_name,
                                            trigger_group
                ) ON DELETE CASCADE;


ALTER TABLE qrtz_simple_triggers
    ADD
        CONSTRAINT fk_qrtz_simple_triggers_qrtz_triggers FOREIGN KEY
            (
             sched_name,
             trigger_name,
             trigger_group
                ) REFERENCES qrtz_triggers (
                                            sched_name,
                                            trigger_name,
                                            trigger_group
                ) ON DELETE CASCADE;

ALTER TABLE qrtz_simprop_triggers
    ADD
        CONSTRAINT fk_qrtz_simprop_triggers_qrtz_triggers FOREIGN KEY
            (
             sched_name,
             trigger_name,
             trigger_group
                ) REFERENCES qrtz_triggers (
                                            sched_name,
                                            trigger_name,
                                            trigger_group
                ) ON DELETE CASCADE;


ALTER TABLE qrtz_triggers
    ADD
        CONSTRAINT fk_qrtz_triggers_qrtz_job_details FOREIGN KEY
            (
             sched_name,
             job_name,
             job_group
                ) REFERENCES qrtz_job_details (
                                               sched_name,
                                               job_name,
                                               job_group
                );

COMMIT;
