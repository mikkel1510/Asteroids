package main.common;

import java.util.*;

public class SPILocator {
    private static final Map<Class, ServiceLoader> loaderMap = new HashMap<Class, ServiceLoader>();

    public static <T> List<T> locateAll(Class<T> service){
        ServiceLoader<T> loader = loaderMap.get(service);

        if (loader == null){
            loader = ServiceLoader.load(service);
            loaderMap.put(service, loader);
        }

        List<T> list = new ArrayList<>();

        if (loader != null){
            try {
                for (T instance : loader){
                    list.add(instance);
                }
            } catch (ServiceConfigurationError e){
                e.printStackTrace();
            }
        }

        return list;
    }

}
