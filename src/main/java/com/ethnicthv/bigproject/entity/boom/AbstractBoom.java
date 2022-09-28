package com.ethnicthv.bigproject.entity.boom;

import com.ethnicthv.bigproject.client.map.SafeGrid;

public abstract class AbstractBoom {
    public abstract Runnable getBoomFunc(int centerX, int centerY);

    public abstract SafeGrid.CellUnSafeFunction getCellFunc();
}
