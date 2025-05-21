package dk.sdu.cbse.scoringclient;

import dk.sdu.common.springclient.ISpringClient;
import org.springframework.web.client.RestTemplate;

public class ScoringClient implements ISpringClient {

    private RestTemplate restTemplate;
    private static final String url = "http://localhost:8080";

    public ScoringClient(){
        this.restTemplate = new RestTemplate();
    }

    @Override
    public String get() {
        String response = restTemplate.getForObject(url+"/points", String.class);
        return response;
    }

    @Override
    public void post(int points){
        restTemplate.postForObject(url+"/points?points="+points, null, Void.class);
    }
}
