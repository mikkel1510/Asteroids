package dk.sdu.cbse.scoringclient;

import dk.sdu.common.springclient.ISpringScoreClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class ScoringClient implements ISpringScoreClient {

    private RestTemplate restTemplate;
    private static final String url = "http://localhost:8080";

    public ScoringClient(){
        this.restTemplate = new RestTemplate();
    }

    @Override
    public String get() {
        try {
            return restTemplate.getForObject(url+"/points", String.class);
        } catch (RestClientException e){
            System.err.println("Failed to get points: "+e.getMessage());
            return "0";
        }
    }

    @Override
    public void post(int points){
        try {
            restTemplate.postForObject(url+"/points?points="+points, null, Void.class);
        } catch (RestClientException e){
            System.err.println("Failed to post points: "+e.getMessage());
        }
    }
}
