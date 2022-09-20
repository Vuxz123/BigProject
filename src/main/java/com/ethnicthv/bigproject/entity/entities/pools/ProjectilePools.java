package com.ethnicthv.bigproject.entity.entities.pools;

import com.almasb.fxgl.core.pool.Pool;
import com.almasb.fxgl.core.pool.ReflectionPool;

import java.util.HashMap;
import java.util.Map;

public class ProjectilePools {
    private ProjectilePools(){}

    private static final Map<Class, Pool> typePools = new HashMap<>();

    private static <T> Pool<T> get(Class<T> type, int max) {
        Pool pool = typePools.get(type);
        if (pool == null) {
            pool = new ProjectilePool(30, max, type);
            typePools.put(type, pool);
        }
        return pool;
    }

    public static <T> Pool<T> get(Class<T> type) {
        return get(type, 100);
    }

    public static <T> void set(Class<T> type, Pool<T> pool) {
        typePools.put(type, pool);
    }

    public static <T> T obtain(Class<T> type) {
        return get(type).obtain();
    }

    public static void free(Object object) {
        Pool pool = typePools.get(object.getClass());
        if (pool == null)
            return; // Ignore freeing an object that was never retained.

        pool.free(object);
    }
}
