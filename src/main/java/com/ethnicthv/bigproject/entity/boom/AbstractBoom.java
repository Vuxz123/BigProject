package com.ethnicthv.bigproject.entity.boom;

import com.ethnicthv.bigproject.client.map.SafeGrid;

public abstract class AbstractBoom {
    public abstract Runnable getBoomFunc();

    public abstract SafeGrid.CellUnSafeFunction getCellFunc();
}
