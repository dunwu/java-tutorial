package io.github.dunwu.javatool.monitor.zipkin;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/** The application is simple, it only uses Web MVC and a {@linkplain RestTemplate}. */
@EnableWebMvc
public class AppConfiguration {
  @Autowired(required = false)
  HttpClient httpClient;

  @Bean RestTemplate restTemplate() {
    HttpClient httpClient = this.httpClient;
    if (httpClient == null) httpClient = HttpClients.createDefault();
    return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
  }
}
