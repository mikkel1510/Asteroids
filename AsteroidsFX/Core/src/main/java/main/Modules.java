package main;

import dk.sdu.cbse.common.Services.IEntityProcessor;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.common.Services.IPostProcessor;
import dk.sdu.common.springclient.ISpringClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Configuration
public class Modules {
    @Bean
    public List<IEntityProcessor> entityProcessorList(){
        return ServiceLoader.load(IEntityProcessor.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IGamePluginService> gamePluginServiceList(){
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IPostProcessor> postProcessorList(){
        return ServiceLoader.load(IPostProcessor.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public ISpringClient springClient(){
        return ServiceLoader.load(ISpringClient.class).findFirst().orElse(null);
    }

}
