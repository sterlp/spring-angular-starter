package org.sterl.education.angular;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/example")
public class ExampleRSI {

    @RequestMapping
    public List<String> get() throws InterruptedException {
    	Thread.sleep(1500);
        return Arrays.asList("Item 1 " + Instant.now(), 
            "Item 2 " + Instant.now().plus(1, ChronoUnit.DAYS));
    }
}
