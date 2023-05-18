package org.arkham.examples.batch.gam.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JobCompleteListener extends JobExecutionListenerSupport {

    @Value("${batch-gam.result.folder}")
    private String resultFolders;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("starting job....");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        final Long jobId = System.currentTimeMillis();
        final String csvPath = resultFolders;
        final Date startTime = jobExecution.getCreateTime();
        final Date endTime = jobExecution.getEndTime();

        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("================ Job finished success ================");
            log.info("jobId      : {}", jobId);
            log.info("csv path   : {}", csvPath);
            log.info("start time : {}", startTime);
            log.info("end time   : {}", endTime);
            log.info("======================================================");
        }

    }
}
