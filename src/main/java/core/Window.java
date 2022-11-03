package core;

import org.joml.Vector2f;
import processing.core.*;
import scenes.TestScene;

import static java.awt.event.KeyEvent.*;

public class Window extends PApplet {
    public void settings() {
        fullScreen(P3D);
    }
    public void setup() {
        frameRate(1000);
        imageMode(CENTER);
        window = this;

        AssetPool.addTexture("earth", requestImage("assets/textures/planets/earth.png"));
        AssetPool.addTexture("moon", requestImage("assets/textures/planets/moon.png"));
        AssetPool.addTexture("shadow", requestImage("assets/textures/planets/shadow.png"));

        scene = new TestScene();
        scene.start();
    }
    private static Window window;
    public static Window getWindow() {
        return window;
    }

    public Scene scene;

    /* DeltaTime */
    private static float millis = 0f;

    public final Vector2f camera = new Vector2f();

    public void draw() {
        /* Calculate DeltaTime */
        float _millis = millis;
        millis = millis() / 1000.0f;
        float dt = millis - _millis;

        /* Update the input detection */
        Input.update();

        /* Update all objects in the current scene */
        updateCamera(dt);

        scene.update(dt, this);

        /* Draw objects to the screen */
        background(0);
        scene.tick(dt, this);
    }

    private void updateCamera(final float dt) {
        if(Input.GetKey(VK_W)) {
            camera.y += 500 * dt;
        }
        if(Input.GetKey(VK_S)) {
            camera.y -= 500 * dt;
        }
        if(Input.GetKey(VK_A)) {
            camera.x += 500 * dt;
        }
        if(Input.GetKey(VK_D)) {
            camera.x -= 500 * dt;
        }

        translate(camera.x, camera.y);
    }

    public void keyPressed() {
        Input.setKey(keyCode, true);
    }
    public void keyReleased() {
        Input.setKey(keyCode, false);
    }
}
