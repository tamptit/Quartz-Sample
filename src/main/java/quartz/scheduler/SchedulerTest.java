package quartz.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SchedulerTest extends SchedulerFactoryBean {

    public SchedulerTest() {
    }
}
