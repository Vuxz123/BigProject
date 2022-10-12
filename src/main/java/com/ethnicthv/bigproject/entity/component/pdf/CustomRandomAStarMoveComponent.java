package com.ethnicthv.bigproject.entity.component.pdf;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.time.LocalTimer;
import com.ethnicthv.bigproject.client.map.SafeCell;
import com.ethnicthv.bigproject.util.WrappedBoolean;
import javafx.util.Duration;

import java.util.function.Predicate;

import static com.almasb.fxgl.core.math.FXGLMath.random;

@Required(CustomAStarMoveComponent.class)
public class CustomRandomAStarMoveComponent extends Component {
    private int minDistance = 0;
    private int maxDistance = Integer.MAX_VALUE;

    private Duration minDelay = Duration.seconds(1.0);
    private Duration maxDelay = Duration.seconds(1.0);

    private CustomAStarMoveComponent astar;

    private final LocalTimer moveTimer = FXGL.newLocalTimer();

    private Duration delay = Duration.seconds(random(minDelay.toSeconds(), maxDelay.toSeconds()));

    private final Predicate<SafeCell> cellFilter = safeCell -> true;
    private boolean wasAtDestination = true;

    public CustomRandomAStarMoveComponent() {
    }

    public CustomRandomAStarMoveComponent(int minDistance, int maxDistance) {
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
    }

    public CustomRandomAStarMoveComponent(Duration minDelay, Duration maxDelay) {
        this.minDelay = minDelay;
        this.maxDelay = maxDelay;
    }

    public CustomRandomAStarMoveComponent(int minDistance, int maxDistance, Duration minDelay, Duration maxDelay) {
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.minDelay = minDelay;
        this.maxDelay = maxDelay;
    }

    @Override
    public void onAdded() {
        super.onAdded();
        moveTimer.capture();
    }

    @Override
    public void onUpdate(double tpf) {
        var isAtDestination = astar.isAtDestination();

        if (!wasAtDestination && isAtDestination) {
            delay = Duration.seconds(random(minDelay.toSeconds(), maxDelay.toSeconds()));
            moveTimer.capture();
        }

        wasAtDestination = isAtDestination;

        if (!astar.isAtDestination()) {
            return;
        }

        if (moveTimer.elapsed(delay)) {
            moveToRandomCell();
        }
    }

    private void moveToRandomCell() {
        astar.getCurrentCell().flatMap(currentCell -> astar.getGrid().getRandomCell(aStarCell -> aStarCell.isWalkable()
                && cellFilter.test(aStarCell)
                && currentCell.distance(aStarCell) >= minDistance
                && currentCell.distance(aStarCell) <= maxDistance)).ifPresent((aStarCell) -> {
            WrappedBoolean isunsafe = new WrappedBoolean(false);
            astar.moveToCell(aStarCell, isunsafe);
            if (isunsafe.get()) astar.stopMovement();
        });
    }
}
