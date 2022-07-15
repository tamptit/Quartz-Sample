package quartz.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import quartz.entity.Chatter;

import java.util.List;

@RestController
public class TestController {

    @GetMapping("/chatter")
    public String getChatterByGroup() {
      return "test";
    }
}
