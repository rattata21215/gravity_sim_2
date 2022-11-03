package core;

import java.util.HashMap;

public final class Input {
    private static final HashMap<Integer, Boolean> newKeys = new HashMap<>();
    private static final HashMap<Integer, Boolean> oldKeys = new HashMap<>();

    public static void update() {
        oldKeys.putAll(newKeys);
    }
    public static void setKey(int key, boolean pressed) {
        newKeys.put(key, pressed);
    }

    public static boolean GetKeyDown(int key) {
        return newKeys.getOrDefault(key, false) && !oldKeys.getOrDefault(key, false);
    }

    public static boolean GetKey(int key) {
        return newKeys.getOrDefault(key, false);
    }

    public static boolean GetKeyUp(int key) {
        return !newKeys.getOrDefault(key, false) && oldKeys.getOrDefault(key, false);
    }
}
