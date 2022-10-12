package com.ethnicthv.bigproject.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ResourceManager implements Serializable {
    public static final ResourceManager INSTANCE = new ResourceManager();

    private static String fileName = "1.txt";
    private static final long serialVersionUID = 1L;

    public ArrayList<Data> playerData = new ArrayList<Data>();

    public void add(String name) {
        playerData.add(new Data(GameManager.data.score, name));
        GameManager.data.reset();
    }

    public void save() throws Exception {
        save(this, fileName);
    }

    public void load() throws Exception {
        Object o = load(fileName);
        if (o == null) {
            playerData = new ArrayList<>();
        } else {
            playerData = (ArrayList<Data>) o;
        }
    }


    public static void save(Serializable data, String fileName) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            oos.writeObject(data);
        }
    }

    public static Object load(String fileName) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
            return ois.readObject();
        }
    }

    public class Data implements Serializable {
        private static final long serialVersionUID = 1L;
        int score;
        String name;

        public Data(int score, String name) {
            this.score = score;
            this.name = name;
        }
    }
}
