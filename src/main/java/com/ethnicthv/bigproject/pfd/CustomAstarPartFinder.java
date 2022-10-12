package com.ethnicthv.bigproject.pfd;

import com.almasb.fxgl.pathfinding.Pathfinder;
import com.ethnicthv.bigproject.client.map.SafeCell;
import com.ethnicthv.bigproject.client.map.SafeCellState;
import com.ethnicthv.bigproject.client.map.SafeGrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Code gốc tại {@link com.almasb.fxgl.pathfinding.astar.AStarPathfinder}
 */
@SuppressWarnings("ProtectedMemberInFinalClass")
public record CustomAstarPartFinder(SafeGrid grid) implements Pathfinder<SafeCell> {

    @Override
    public List<SafeCell> findPath(int sourceX, int sourceY, int targetX, int targetY) {
        return findPath(grid.getData(), grid.get(sourceX, sourceY), grid.get(targetX, targetY));
    }

    @Override
    public List<SafeCell> findPath(int sourceX, int sourceY, int targetX, int targetY, List<SafeCell> busyCells) {
        return findPath(grid.getData(), grid.get(sourceX, sourceY), grid.get(targetX, targetY), busyCells.toArray(new SafeCell[0]));
    }

    /**
     * Since the equality check is based on references,
     * start and target must be elements of the array.
     *
     * @param grid      the grid of nodes
     * @param start     starting node
     * @param target    target node
     * @param busyNodes busy "unwalkable" nodes
     * @return path as list of nodes from start (excl) to target (incl) or empty list if no path found
     */
    public List<SafeCell> findPath(SafeCell[][] grid, SafeCell start, SafeCell target, SafeCell... busyNodes) {
        if (start == target || target.getState() == SafeCellState.NOT_WALKABLE)
            return Collections.emptyList();

        // reset grid cells data
        for (int y = 0; y < grid[0].length; y++) {
            for (int x = 0; x < grid.length; x++) {
                grid[x][y].setHCost(Math.abs(target.getX() - x) + Math.abs(target.getY() - y));
                grid[x][y].setParent(null);
                grid[x][y].setGCost(0);
            }
        }

        List<SafeCell> open = new ArrayList<>();
        List<SafeCell> closed = new ArrayList<>();

        SafeCell current = start;

        boolean found = false;

        while (!found && !closed.contains(target)) {
            for (SafeCell neighbor : getValidNeighbors(current, busyNodes)) {
                if (neighbor == target) {
                    target.setParent(current);
                    found = true;
                    closed.add(target);
                    break;
                }

                if (!closed.contains(neighbor)) {
                    if (open.contains(neighbor)) {
                        int newG = current.getGCost() + 10;

                        if (newG < neighbor.getGCost()) {
                            neighbor.setParent(current);
                            neighbor.setGCost(newG);
                        }
                    } else {
                        neighbor.setParent(current);
                        neighbor.setGCost(current.getGCost() + 10);
                        open.add(neighbor);
                    }
                }
            }

            if (!found) {
                closed.add(current);
                open.remove(current);

                if (open.isEmpty())
                    return Collections.emptyList();

                SafeCell acc = open.get(0);

                for (SafeCell a : open) {
                    acc = a.getFCost() < acc.getFCost() ? a : acc;
                }

                current = acc;
            }
        }

        return buildPath(start, target);
    }

    private List<SafeCell> buildPath(SafeCell start, SafeCell target) {
        List<SafeCell> path = new ArrayList<>();

        SafeCell tmp = target;
        do {
            path.add(tmp);
            tmp = tmp.getParent();
        } while (tmp != start);

        Collections.reverse(path);
        return path;
    }

    /**
     * @param node      the A* node
     * @param busyNodes nodes which are busy, i.e. walkable but have a temporary obstacle
     * @return neighbors of the node
     */
    protected List<SafeCell> getValidNeighbors(SafeCell node, SafeCell... busyNodes) {
        var busyNodesList = Arrays.asList(busyNodes);
        return grid.getNeighbors(node.getX(), node.getY()).stream()
                .filter(SafeCell::isWalkable)
                .filter(neighbor -> !busyNodesList.contains(neighbor))
                .collect(Collectors.toList());
    }
}
