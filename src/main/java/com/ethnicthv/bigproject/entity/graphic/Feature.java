package com.ethnicthv.bigproject.entity.graphic;

public abstract class Feature {

    public abstract void onAdd(Renderer renderer);

    public abstract void render(Renderer renderer, double tpf);

    public abstract void onRemove(Renderer renderer);
}
