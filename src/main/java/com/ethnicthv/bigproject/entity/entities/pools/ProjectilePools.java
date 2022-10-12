package com.ethnicthv.bigproject.entity.entities.pools;

import com.ethnicthv.bigproject.entity.entities.Projectile;

import java.util.HashMap;
import java.util.Map;

public class ProjectilePools {
    private ProjectilePools() {
    }

    private static final Map<Class, ProjectilePool> typePools = new HashMap<>();

    private static <T extends Projectile> ProjectilePool<T> get(Class<T> type, int max) {
        ProjectilePool<T> pool = typePools.get(type);
        if (pool == null) {
            pool = new ProjectilePool<T>(30, max, type);
            typePools.put(type, pool);
        }
        return pool;
    }

    public static <T extends Projectile> ProjectilePool<T> get(Class<T> type) {
        return get(type, 100);
    }

    public static <T extends Projectile> void set(Class<T> type, ProjectilePool<T> pool) {
        typePools.put(type, pool);
    }

    public static <T extends Projectile> T obtain(Class<T> type) {
        return get(type).obtain();
    }

    public static <T extends Projectile> void free(Object object) {
        ProjectilePool<T> pool = typePools.get(object.getClass());
        if (pool == null)
            return;

        pool.free((T) object);
    }
}
