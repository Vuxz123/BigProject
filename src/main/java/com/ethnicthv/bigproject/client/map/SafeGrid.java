package com.ethnicthv.bigproject.client.map;

import com.almasb.fxgl.core.collection.grid.Grid;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.ethnicthv.bigproject.client.GameManager;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SafeGrid extends Grid<SafeCell> {
    /**
     * Tạo một bảng các ô đánh dấu vị trí safe trên bản đồ.
     * Tất cả các ô đã được khởi tạo với giá trị {@link SafeCellState#SAFE}
     */
    public SafeGrid(int width, int height) {
        super(SafeCell.class, width, height, (x, y) -> new SafeCell(x, y, SafeCellState.NULL));
    }

    /**
     * Trả về một list các ô được đánh dấu là {@link SafeCellState#SAFE}
     */
    public List<SafeCell> getWalkableCell() {
        return getCells()
                .stream()
                .filter(c -> c.getState().isWalkable())
                .toList();
    }

    public List<SafeCell> getSafeCell() {
        return getCells()
                .stream()
                .filter(c -> c.getState().isSafe())
                .toList();
    }

    public SafeCell getNearestSafeCell(int cellX, int cellY){
        AtomicInteger max = new AtomicInteger(Integer.MAX_VALUE);
        AtomicReference<SafeCell> cell = null;
        getCells()
                .stream()
                .filter(c -> c.getState().isSafe())
                .forEach(c -> {
                    int x = c.getX() - cellX;
                    int y = c.getY() - cellY;
                    int distance = (int) Math.sqrt(x*x + y*y);
                    if(distance < max.get()) {
                        max.set(distance);
                        assert cell != null;
                        cell.set(c);
                    }
                });
        assert cell != null;
        return cell.get();
    }



    public void setUnSafe(int cellX, int cellY, CellUnSafeFunction function){
        function.apply(this.getCells(), cellX, cellY);
    }

    public boolean isSafe(int cellX, int cellY) {
        return get(cellX, cellY).isSafe();
    }

    public boolean isNotSafe(int cellX, int cellY) {
        return get(cellX, cellY).isNotSafe();
    }

    public interface CellUnSafeFunction {
        void apply(List<SafeCell> cell, int centerX, int centerY);
    }

    /**
     * Method này nhằm mục đích test.
     * Không nên dùng.
     * Xem chi tiết bản gốc: {@link AStarGrid}
     */
    public static SafeGrid fromWorld(GameWorld world,
                                      int worldWidth, int worldHeight,
                                      int cellWidth, int cellHeight,
                                      Function<Object, SafeCellState> mapping) {

        SafeGrid grid = new SafeGrid(worldWidth, worldHeight);
        grid.populate((x, y) -> {

            int worldX = GameManager.OFFSETX + x * cellWidth + cellWidth / 2;
            int worldY = GameManager.OFFSETY + y * cellHeight + cellWidth / 2;

            boolean isWalkable;

            System.out.println( world.getEntitiesInRange(new Rectangle2D(worldX-2, worldY-2, 4, 4)).size());
            // size 4 is a "good enough" value
            List<Object> collidingTypes = //world.getEntitiesInRange(new Rectangle2D(worldX-2, worldY-2, 4, 4))
                                            world.getEntitiesAt(new Point2D(worldX,worldY))
                    .stream()
                    .map(Entity::getType)
                    .collect(Collectors.toList());

            if (collidingTypes.isEmpty()) {
                // if no types found at given worldX, worldY, then just see what mapping returns by default
                isWalkable = mapping.apply("") == SafeCellState.NULL;
            } else {
                isWalkable = collidingTypes.stream()
                        .map(mapping)
                        .noneMatch(state -> state == SafeCellState.NOT_WALKABLE);
            }


            return new SafeCell(x, y, isWalkable ? SafeCellState.NULL : SafeCellState.NOT_WALKABLE);
        });

        return grid;
    }
}
