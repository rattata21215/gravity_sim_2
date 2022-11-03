package core;

import java.util.ArrayList;

public abstract class Scene {
    protected final ArrayList<GameObject> gameObjects;

    public Scene() {
        this.gameObjects = new ArrayList<>();
    }

    public void start() {
        for(GameObject gameObject : gameObjects) {
            gameObject.start();
        }
    }

    public void update(final float dt, final Window window) {
        for(GameObject gameObject : gameObjects) {
            gameObject.update(dt, window);
        }
    }

    public void tick(final float dt, final Window window) {
        for(GameObject gameObject : gameObjects) {
            gameObject.tick(dt, window);
        }
    }

    public ArrayList<GameObject> getObjects() {
        return this.gameObjects;
    }
}
