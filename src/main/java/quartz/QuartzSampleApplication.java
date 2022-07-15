package quartz;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAutoConfiguration
@EnableBatchProcessing
public class QuartzSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzSampleApplication.class, args);
        System.out.println("ok");
    }

//    @GetMapping("/chatter")
//    public String getChatterByGroup() {
//        return "test";
//    }

}
