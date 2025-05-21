import dk.sdu.cbse.scoringclient.ScoringClient;
import dk.sdu.common.springclient.ISpringClient;

module ScoringClient {
    requires spring.web;
    requires CommonSpringClient;
    exports dk.sdu.cbse.scoringclient;
    provides ISpringClient with ScoringClient;
}