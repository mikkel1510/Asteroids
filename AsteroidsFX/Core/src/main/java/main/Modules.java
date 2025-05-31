package main;

import dk.sdu.cbse.common.Services.IEntityProcessingService;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.common.Services.IPostProcessingService;
import dk.sdu.common.springclient.ISpringScoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Configuration
public class Modules {

    private static final String pluginDir = "plugins";
    private static final ModuleLayer pluginLayer = PluginLoader.loadPluginLayer(pluginDir, "Enemy");

    @Bean
    public Game game(){
        return new Game(gamePluginServiceList(), entityProcessorList(), postProcessorList(), springScoreClient());
    }

    @Bean
    public List<IEntityProcessingService> entityProcessorList(){
        List<IEntityProcessingService> plugins = ServiceLoader.load(pluginLayer, IEntityProcessingService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());

        return plugins;
    }

    @Bean
    public List<IGamePluginService> gamePluginServiceList(){
        List<IGamePluginService> plugins = ServiceLoader.load(pluginLayer, IGamePluginService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());

        return plugins;
    }

    @Bean
    public List<IPostProcessingService> postProcessorList(){
        List<IPostProcessingService> plugins = ServiceLoader.load(pluginLayer, IPostProcessingService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());

        return plugins;
    }

    @Bean
    public ISpringScoreClient springScoreClient(){
        return ServiceLoader.load(ISpringScoreClient.class).findFirst().orElse(null);
    }


}
