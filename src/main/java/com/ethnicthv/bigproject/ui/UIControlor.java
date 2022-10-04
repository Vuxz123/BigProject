package com.ethnicthv.bigproject.ui;

import com.almasb.fxgl.core.Updatable;
import com.ethnicthv.bigproject.client.GameManager;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class UIControlor {
    public static UIControlor INSTANCE = new UIControlor();

    private List<Updatable> updatedNode = new ArrayList<>();
    private UIControlor() {
        updatedNode.add(GameManager.ui.test);
    }

    public void onUpdate(double tpf) {
        for (var i : updatedNode) {
            i.onUpdate(tpf);
        }
    }
}
