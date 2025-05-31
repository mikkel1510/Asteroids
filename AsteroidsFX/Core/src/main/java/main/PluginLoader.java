package main;

import java.lang.module.ModuleFinder;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public class PluginLoader {
    public static ModuleLayer loadPluginLayer(String pluginsDir, String moduleName){
        var finder = ModuleFinder.of(Paths.get(pluginsDir));
        var parent = ModuleLayer.boot();
        var cf = parent.configuration().resolve(finder, ModuleFinder.of(), Set.of(moduleName));
        return parent.defineModulesWithOneLoader(cf, ClassLoader.getSystemClassLoader());
    }
}
