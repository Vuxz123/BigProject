package com.ethnicthv.bigproject.ui;

import com.almasb.fxgl.core.Updatable;
import com.ethnicthv.bigproject.client.GameManager;

import java.util.ArrayList;
import java.util.List;

public class UIControlor {
    public static final UIControlor INSTANCE = new UIControlor();

    private final List<Updatable> updatedNode = new ArrayList<>();

    private UIControlor() {
        updatedNode.add(GameManager.ui.shield);
        updatedNode.add(GameManager.ui.speed);
        updatedNode.add(GameManager.ui.health);
        updatedNode.add(GameManager.ui.mana);
        updatedNode.add(GameManager.ui.boom);
        updatedNode.add(GameManager.ui.block);
    }

    public void onUpdate(double tpf) {
        for (var i : updatedNode) {
            i.onUpdate(tpf);
        }
    }
}
