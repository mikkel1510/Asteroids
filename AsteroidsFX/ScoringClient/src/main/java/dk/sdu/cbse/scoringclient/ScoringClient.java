package dk.sdu.cbse.scoringclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ScoringClient {

    private RestTemplate restTemplate;

    public ScoringClient(){
        this.restTemplate = new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ScoringClient.class, args);
    }

    @Bean
    public String getPoints() throws Exception {
        String response = restTemplate.getForObject("http://localhost:8080/points?points=1", String.class);
        return response;
    }
}
