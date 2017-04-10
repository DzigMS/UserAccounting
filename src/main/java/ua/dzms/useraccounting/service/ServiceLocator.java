package ua.dzms.useraccounting.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private static ServiceLocator serviceLocator;
    private Map<Class<?>, Object> serviceMap = new HashMap<>();

    public static ServiceLocator getInstance() {
        if (serviceLocator == null) {
            serviceLocator = new ServiceLocator();
        }
        return serviceLocator;
    }

    public <T> void register(Class<T> clazz, T service) {
        serviceMap.put(clazz, service);
    }

    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> clazz){
        return (T) serviceMap.get(clazz);
    }
}
