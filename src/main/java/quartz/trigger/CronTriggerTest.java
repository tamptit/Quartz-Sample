package quartz.trigger;

import org.quartz.CronTrigger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.stereotype.Component;

//@Component
public class CronTriggerTest extends CronTriggerFactoryBean {

    public CronTriggerTest() {
//        super.setCronExpression("0/2 * * * * ? *");
    }
}
