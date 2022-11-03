package scenes;

import components.PlanetaryBody;
import core.AssetPool;
import core.GameObject;
import core.Scene;
import org.joml.Vector2f;

import static core.Window.getWindow;

public class TestScene extends Scene {
    public TestScene() {
        super();
        GameObject gameObject = new GameObject("Earth");
        gameObject.addComponent(
                new PlanetaryBody(5 * 10E14f, 150, new Vector2f(1920 / 2f, 1080 / 2f), AssetPool.getTexture("earth"), 60)
        );
        gameObjects.add(gameObject);

        gameObject = new GameObject("The Moon");
        gameObject.addComponent(
                new PlanetaryBody(4 * 10E13f, 50, new Vector2f((1920 / 2f) + 500, 1080 / 2f), AssetPool.getTexture("moon"), 60)
        );
        gameObject.getComponent(PlanetaryBody.class).velocity().y = 75;
        gameObjects.add(gameObject);
    }

    public void start() {
        super.start();

        System.out.println(gameObjects);
    }
}
