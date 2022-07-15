package quartz.job;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;

@Component
public class MyTask {
    public void performAction() {
        System.out.println(Date.from(Instant.now()) + "Hey, you reached me...:)");
    }
}
