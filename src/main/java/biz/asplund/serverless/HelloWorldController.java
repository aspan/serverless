package biz.asplund.serverless;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public Map<String, String> hello(@RequestParam(required = false) String name) {
        if (name == null) {
            name = "";
        } else {
            name = " " + name.trim();
        }
        return Map.of("message", "Hello World" + name + "!");
    }
}
