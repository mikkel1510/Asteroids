import dk.sdu.cbse.scoringclient.ScoringClient;
import dk.sdu.common.springclient.ISpringClient;

module ScoringClient {
    requires spring.context;
    requires spring.web;
    requires CommonSpringClient;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    exports dk.sdu.cbse.scoringclient;
}