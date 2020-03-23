package io.github.dunwu.javatool.monitor.zipkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RestController
@Configuration
@CrossOrigin // So that javascript can be hosted elsewhere
public class Frontend {
  @Autowired RestTemplate restTemplate;

  @RequestMapping("/") public String callBackend() {
    return restTemplate.getForObject("http://localhost:9000/api", String.class);
  }
}
