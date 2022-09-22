package com.ethnicthv.bigproject.entity.component.pdf;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.pathfinding.astar.AStarCell;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.almasb.fxgl.pathfinding.astar.AStarPathfinder;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.value.ChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Required(CustomCellMoveComponent.class)
public class CustomAstarMoveComponent extends Component {
    private CustomCellMoveComponent moveComponent;
    private LazyValue<AStarPathfinder> pathfinder;
    private List<AStarCell> path;
    private Runnable delayedPathCalc;
    private ReadOnlyBooleanWrapper isAtDestinationProp;
    private ChangeListener<Boolean> isAtDestinationListener;

    public CustomAstarMoveComponent(AStarGrid grid) {
        this(new LazyValue(() -> {
            return grid;
        }));
    }

    public CustomAstarMoveComponent(LazyValue<AStarGrid> grid) {
        this.path = new ArrayList();
        this.delayedPathCalc = EmptyRunnable.INSTANCE;
        this.isAtDestinationProp = new ReadOnlyBooleanWrapper(true);
        this.isAtDestinationListener = (o, old, isAtDestination) -> {
            if (isAtDestination) {
                this.delayedPathCalc.run();
                this.delayedPathCalc = EmptyRunnable.INSTANCE;
            }

        };
        this.pathfinder = new LazyValue(() -> {
            return new AStarPathfinder((AStarGrid)grid.get());
        });
    }

    public void onAdded() {
        this.moveComponent = (CustomCellMoveComponent) this.entity.getComponent(CustomCellMoveComponent.class);
        this.moveComponent.atDestinationProperty().addListener(this.isAtDestinationListener);
    }

    public void onRemoved() {
        this.moveComponent.atDestinationProperty().removeListener(this.isAtDestinationListener);
    }

    public boolean isMoving() {
        return this.moveComponent.isMoving();
    }

    public boolean isPathEmpty() {
        return this.path.isEmpty();
    }

    public ReadOnlyBooleanProperty atDestinationProperty() {
        return this.isAtDestinationProp.getReadOnlyProperty();
    }

    public boolean isAtDestination() {
        return this.isAtDestinationProp.get();
    }

    public AStarGrid getGrid() {
        return ((AStarPathfinder)this.pathfinder.get()).getGrid();
    }

    public Optional<AStarCell> getCurrentCell() {
        int cellX = this.moveComponent.getCellX();
        int cellY = this.moveComponent.getCellY();
        return this.getGrid().getOptional(cellX, cellY);
    }

    public void stopMovementAt(int cellX, int cellY) {
        this.path.clear();
        this.moveComponent.setPositionToCell(cellX, cellY);
        this.isAtDestinationProp.set(true);
    }

    public void stopMovement() {
        this.stopMovementAt(this.moveComponent.getCellX(), this.moveComponent.getCellY());
    }

    public void moveToRightCell() {
        this.getGrid().getRight(this.moveComponent.getCellX(), this.moveComponent.getCellY()).ifPresent(this::moveToCell);
    }

    public void moveToLeftCell() {
        this.getGrid().getLeft(this.moveComponent.getCellX(), this.moveComponent.getCellY()).ifPresent(this::moveToCell);
    }

    public void moveToUpCell() {
        this.getGrid().getUp(this.moveComponent.getCellX(), this.moveComponent.getCellY()).ifPresent(this::moveToCell);
    }

    public void moveToDownCell() {
        this.getGrid().getDown(this.moveComponent.getCellX(), this.moveComponent.getCellY()).ifPresent(this::moveToCell);
    }

    public void moveToRandomCell() {
        this.moveToRandomCell(FXGLMath.getRandom());
    }

    public void moveToRandomCell(Random random) {
        this.getGrid().getRandomCell(random, AStarCell::isWalkable).ifPresent(this::moveToCell);
    }

    public void moveToCell(AStarCell cell) {
        this.moveToCell(cell.getX(), cell.getY());
    }

    public void moveToCell(int x, int y) {
        int startX = this.moveComponent.getCellX();
        int startY = this.moveComponent.getCellY();
        this.moveToCell(startX, startY, x, y);
    }

    public void moveToCell(int startX, int startY, int targetX, int targetY) {
        this.isAtDestinationProp.set(false);
        if (this.moveComponent.isAtDestination()) {
            this.path = ((AStarPathfinder)this.pathfinder.get()).findPath(startX, startY, targetX, targetY);
        } else {
            this.delayedPathCalc = () -> {
                this.path = ((AStarPathfinder)this.pathfinder.get()).findPath(this.moveComponent.getCellX(), this.moveComponent.getCellY(), targetX, targetY);
            };
        }

    }

    public void onUpdate(double tpf) {
        if (!this.isAtDestination() && !this.isMoving() && this.isPathEmpty()) {
            this.isAtDestinationProp.set(true);
        }

        if (!this.path.isEmpty() && this.moveComponent.isAtDestination()) {
            AStarCell next = (AStarCell)this.path.remove(0);
            this.moveComponent.moveToCell(next.getX(), next.getY());
        }
    }

    public boolean isComponentInjectionRequired() {
        return false;
    }
}
