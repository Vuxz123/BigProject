package com.ethnicthv.bigproject.entity.entities.pools;

import com.almasb.fxgl.core.pool.Pool;
import com.almasb.fxgl.core.reflect.ReflectionUtils;
import com.ethnicthv.bigproject.entity.entities.Projectile;

/**
 * Một cái pool chứa các Projectile có sử dụng ParticleEmitter T
 *
 * @param <T> Loại emitter được dùng
 */
public class ProjectilePool<T extends Projectile> extends Pool<T> {

    private Class<T> type;

    public ProjectilePool(int initialCapacity, int max, Class<T> type) {
        super(initialCapacity, max);
        this.type = type;
    }

    protected T newObject() {
        return ReflectionUtils.newInstance(type);
    }
}
