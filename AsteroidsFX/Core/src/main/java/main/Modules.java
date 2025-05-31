package main;

import dk.sdu.cbse.common.Services.IEntityProcessingService;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.common.Services.IPostProcessingService;
import dk.sdu.common.springclient.ISpringScoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Configuration
public class Modules {

    @Bean
    public Game game(){
        return new Game(gamePluginServiceList(), entityProcessorList(), postProcessorList(), springScoreClient());
    }

    @Bean
    public List<IEntityProcessingService> entityProcessorList(){
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IGamePluginService> gamePluginServiceList(){
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IPostProcessingService> postProcessorList(){
        return ServiceLoader.load(IPostProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public ISpringScoreClient springScoreClient(){
        return ServiceLoader.load(ISpringScoreClient.class).findFirst().orElse(null);
    }


}
