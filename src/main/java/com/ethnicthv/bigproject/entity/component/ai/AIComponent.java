package com.ethnicthv.bigproject.entity.component.ai;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.entity.state.EntityState;
import com.almasb.fxgl.entity.state.StateComponent;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeCell;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAStarMoveComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomRandomAStarMoveComponent;
import com.ethnicthv.bigproject.util.WrappedBoolean;

@Required(StateComponent.class)
@Required(CustomRandomAStarMoveComponent.class)
@Required(CustomAStarMoveComponent.class)
public class AIComponent extends Component {

    private Entity player;
    private CustomRandomAStarMoveComponent rdastar;
    private StateComponent stateComponent;
    private CustomAStarMoveComponent astar;

    private EnemyAIState states = new EnemyAIState();

    public AIComponent() {
    }

    @Override
    public void onAdded() {
        player = GameManager.player;
        rdastar = entity.getComponent(CustomRandomAStarMoveComponent.class);
        stateComponent = entity.getComponent(StateComponent.class);
        astar = entity.getComponent(CustomAStarMoveComponent.class);
        stateComponent.changeState(states.RANDOM);
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        if(GameManager.grid.pfg.getCell(entity.getPosition()).isNotSafe()){
            stateComponent.changeState(states.DODGE);
        }else if (player.distance(entity) < 50 && !GameManager.grid.pfg.getCell(entity.getPosition()).isSafe()) {
            stateComponent.changeState(states.PURSUEING);
        } else {
            stateComponent.changeState(states.RANDOM);
        }
    }

    public class EnemyAIState {
        public final EntityState RANDOM = new EntityState("RANDOM") {
            @Override
            public void onEntering() {
                super.onEntering();
                rdastar.resume();
            }

            @Override
            public void onExited() {
                super.onExited();
                rdastar.pause();
            }
        };

        public final EntityState PURSUEING = new EntityState("PURSUEING") {
            @Override
            protected void onUpdate(double tpf) {
                super.onUpdate(tpf);
                SafeCell pos = astar.getGrid().getCell(player);
                astar.moveToCell(pos);
            }
        };

        public final EntityState DODGE = new EntityState("DODGE") {
            @Override
            public void onEntering() {
                super.onEntering();
                int cellX = GameManager.grid.getGridX((int) entity.getX());
                int cellY = GameManager.grid.getGridY((int) entity.getY());
                SafeCell c = GameManager.grid.pfg.getNearestSafeCell(cellX, cellY);
                astar.moveToCell(c);
            }
        };
    }

}
