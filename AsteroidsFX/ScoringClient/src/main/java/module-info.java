import dk.sdu.cbse.scoringclient.ScoringClient;
import dk.sdu.common.springclient.ISpringScoreClient;

module ScoringClient {
    requires spring.web;
    requires CommonSpringClient;
    exports dk.sdu.cbse.scoringclient;
    provides ISpringScoreClient with ScoringClient;
}