package core;

import processing.core.PImage;

import java.util.HashMap;

public final class AssetPool {
    private static final HashMap<String, PImage> textures = new HashMap<>();

    public static void addTexture(String name, PImage texture) {
        textures.put(name, texture);
    }

    public static PImage getTexture(String name) {
        return textures.get(name);
    }
}
