package com.co.hm.batches.modules;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Config to run batches
 * <p>
 * - Runner: Config to run a job <br>
 * - Run: Run update token batch with schedule from xml config <br>
 * </p>
 */
public class JobRunner {

  @Autowired
  public JobLauncher jobLauncher;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private Job job;

  public void setJob(Job job) {
    this.job = job;
  }

  /**
   * Config to run a job
   */
  public void runner(Job job) {
    try {
      JobExecution execution = jobLauncher.run(job,
              new JobParametersBuilder().addString("date", new Date().toString()).toJobParameters());
      logger.info("Exit Status : " + execution.getStatus());
    } catch (Exception e) {
      logger.error("error", e);
    }
  }

  /**
   * Run update token batch with schedule from xml config
   */
  public void run() {
    runner(job);
  }

}
