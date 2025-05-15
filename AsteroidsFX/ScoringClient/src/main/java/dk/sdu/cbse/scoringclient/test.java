package dk.sdu.cbse.scoringclient;

import org.springframework.web.client.RestTemplate;

public class test {
    public static void main(String[] args) throws Exception {
        ScoringClient client = new ScoringClient();

        System.out.println(client.getPoints());
    }
}
