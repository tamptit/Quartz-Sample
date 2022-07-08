package job;

import org.quartz.CronTrigger;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

public class CronTriggerTest extends CronTriggerFactoryBean {
    public CronTriggerTest() {
        super.setCronExpression("0/2 * * * * ? *");
    }
}
