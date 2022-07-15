package quartz.job;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DynamicJobExample {


    @Bean(name="scheduler_bean_batch")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException, SchedulerException {
        System.out.println("Init scheduler_bean------- ");
        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        List<Trigger> triggerList = new ArrayList<>();
        triggerList.add((Trigger) new CronTriggerBean());

        List<JobDetail> jobDetailList = new ArrayList<>();

        factory.setOverwriteExistingJobs(true);
        factory.setAutoStartup(true);
//        factory.setJobFactory(jobFactory);
        factory.setJobDetails(jobDetailList.toArray(new JobDetail[jobDetailList.size()]));
        factory.setTriggers(triggerList.toArray(new Trigger[triggerList.size()]));

        System.out.println("SchedulerFactoryBean start----------->>>>");

        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setTargetObject(MyTask.class);
        jobDetail.setTargetMethod("performAction");
        jobDetail.setName("MyJobDetail2");
        jobDetail.setConcurrent(false);

        factory.getScheduler().scheduleJob(jobDetail.getObject(), new CronTriggerBean());
        factory.getScheduler().start();
        factory.getScheduler().shutdown();
        return factory;
    }


    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("dynamic-jobs.xml");
        //get the quartzFactory bean
        String[] allBeanNames = context.getBeanDefinitionNames();

        Scheduler scheduler = (Scheduler) context.getBean("quartz/scheduler");
//        Scheduler scheduler = new SchedulerTest().getScheduler();

        //get the task bean
        MyTask myTask = (MyTask) context.getBean("myTask");


        try {
            // create JOB
            MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
            jobDetail.setTargetObject(myTask);
            jobDetail.setTargetMethod("performAction");
            jobDetail.setName("MyJobDetail");
            jobDetail.setConcurrent(false);

            jobDetail.afterPropertiesSet();

            // create CRON Trigger
            CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
            trigger.setBeanName("UniqueCronBeanName");
            trigger.setGroup("uniqueCronGroup");
            trigger.setJobDetail(jobDetail.getObject());
            trigger.setCronExpression("0/1 * * * * ? *");
            trigger.afterPropertiesSet();

            scheduler.scheduleJob((JobDetail) jobDetail.getObject(), trigger.getObject());
            // Start Scheduler
            scheduler.start();

            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String clickUpdate = myObj.nextLine();  // Read user input
            Boolean isOnceRun = true;
            if (isOnceRun && !clickUpdate.isEmpty()){
                isOnceRun = false;
                System.out.println("new Trigger is creating...");
                CronTriggerFactoryBean trigger2 = new CronTriggerFactoryBean();
                trigger2.setBeanName("UniqueCronBeanName2");
                trigger2.setGroup("uniqueCronGroup");
                trigger2.setJobDetail(jobDetail.getObject());
                trigger2.setCronExpression("0/2 * * * * ? *");
                trigger2.afterPropertiesSet();
                System.out.println("---- Trigger2 created---");
                if (scheduler.checkExists(jobDetail.getObject().getKey())){
                    scheduler.deleteJob(jobDetail.getObject().getKey());
                }
                scheduler.scheduleJob((JobDetail) jobDetail.getObject(), trigger2.getObject());
                System.out.println("---- Trigger2 settings Check please---");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
