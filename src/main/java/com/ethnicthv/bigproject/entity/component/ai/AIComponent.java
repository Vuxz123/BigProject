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
        if(player.distance(entity) < 50) {
            stateComponent.changeState(states.PURSUEING);
        }else{
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
    }

}
