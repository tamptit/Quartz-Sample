package job;

import java.sql.Date;
import java.time.Instant;

public class MyTask {
    public void performAction() {
        System.out.println(Date.from(Instant.now()) + "Hey, you reached me...:)");
    }
}
