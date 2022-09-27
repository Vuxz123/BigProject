package com.ethnicthv.bigproject.client.map;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.LevelLoader;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class CustomTextLevelLoader implements LevelLoader {
    private final int blockWidth;
    private final int blockHeight;
    private final int offsetX;
    private final int offsetY;
    private final char emptyChar;

    private final Function<EntityType, SafeCellState> mapping;

    public CustomTextLevelLoader(int blockWidth, int blockHeight, int offsetX, int offsetY, char emptyChar, Function<EntityType, SafeCellState> mapping) {
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.emptyChar = emptyChar;
        this.mapping = mapping;
    }

    @NotNull
    @Override
    public Level load(@NotNull URL url, @NotNull GameWorld gameWorld) {
        List<String> lines;
        GameManager.grid.pfg = new SafeGrid(GameManager.grid.maxGridX, GameManager.grid.maxGridY);
        SafeGrid temp = GameManager.grid.pfg;
        try {
            String mainPath = Paths.get(url.toURI()).toString();
            Path path = Paths.get(mainPath);
            lines = Files.readAllLines(path);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        List<Entity> entities = new ArrayList<>();

        AtomicInteger maxWidth = new AtomicInteger();
        AtomicInteger i = new AtomicInteger();
        lines.forEach(line -> {
            if (line.length() > maxWidth.get()) maxWidth.set(line.length());
            int j = 0;
            for (char c : line.toCharArray()) {
                if (c != emptyChar) {
                    Entity e = gameWorld.create("" + c, new SpawnData(offsetX + j * blockWidth, offsetY + i.get() * blockHeight));
                    boolean isNotWalkable = mapping.apply((EntityType) e.getType()).isNotWalkable();
                    if (isNotWalkable) {
                        temp.get(j, i.get()).setState(SafeCellState.NOT_WALKABLE);
                    }
                    entities.add(e);
                }
                j++;
            }
            i.getAndIncrement();
        });

        return new Level(maxWidth.get() * blockWidth, lines.size() * blockWidth, entities);
    }
}
