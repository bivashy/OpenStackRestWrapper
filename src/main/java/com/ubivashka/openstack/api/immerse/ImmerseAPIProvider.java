package com.ubivashka.openstack.api.immerse;

import java.lang.reflect.Field;
import java.util.Map;

import org.openstack4j.api.storage.BlockVolumeService;
import org.openstack4j.openstack.provider.DefaultAPIProvider;

public class ImmerseAPIProvider {
    private static Field INSTANCES_FIELD;

    static {
        try {
            INSTANCES_FIELD = DefaultAPIProvider.class.getDeclaredField("instances");
            INSTANCES_FIELD.setAccessible(true);
        } catch(NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        bindInstance(BlockVolumeService.class, new ImmerseBlockVolumeServiceImpl());
    }

    private static void bindInstance(Class<?> api, Object instance) {
        try {
            Map<Class<?>, Object> instances = (Map<Class<?>, Object>) INSTANCES_FIELD.get(null);
            instances.put(api, instance);
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
