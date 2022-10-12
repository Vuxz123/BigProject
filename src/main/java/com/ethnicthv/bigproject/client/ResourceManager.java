package com.ethnicthv.bigproject.client;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ResourceManager implements Serializable {
    public static final ResourceManager INSTANCE = new ResourceManager();
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String fileName = "1.txt";
    public ArrayList<Data> playerData = new ArrayList<>();

    public static void save(Serializable data, String fileName) throws Exception {
        URI uri = ClassLoader.getSystemResource("1.txt").toURI();
        String mainPath = Paths.get(uri).toString();
        Path path = Paths.get(mainPath);
        ObjectOutputStream ois;
        ois = new ObjectOutputStream(Files.newOutputStream(path));
        ois.writeObject(data);
    }

    public static Object load(String fileName) throws Exception {
        URI uri = ClassLoader.getSystemResource("1.txt").toURI();
        String mainPath = Paths.get(uri).toString();
        Path path = Paths.get(mainPath);
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path));
        return ois.readObject();
    }

    public void add(String name) {
        playerData.add(new Data(GameManager.data.killed, name));
    }

    public void save() throws Exception {
        save(this, fileName);
    }

    public void load() throws Exception {
        Object o = null;
        try {
            o = load(fileName);
        } catch (EOFException ignored) {
        }
        if (o == null) {
            playerData = new ArrayList<>();
        } else {
            playerData = ((ResourceManager) o).playerData;
            System.out.println(playerData);

        }
    }

    public static class Data implements Serializable, Comparable<Data> {
        @Serial
        private static final long serialVersionUID = 1L;
        final int score;
        final String name;

        public Data(int score, String name) {
            this.score = score;
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "score=" + score +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public int compareTo(@NotNull Data o) {
            return Integer.compare(this.score, o.score);
        }
    }
}
