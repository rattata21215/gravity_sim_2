package components;

import core.AssetPool;
import core.Component;
import core.GameObject;
import core.Window;
import org.joml.Vector2f;
import processing.core.PImage;

import static core.Window.getWindow;

public class PlanetaryBody extends Component {
    private final float mass;
    private final float radius;

    PImage image;

    private final PImage shadow;

    private final float rotationSpeed;
    private float rotation = 0f;

    private Vector2f position;
    private Vector2f velocity;

    private static final float G = 6.67408f * 10E-11f;

    public void update(final float dt, final Window window) {
        for(GameObject gameObject : window.scene.getObjects()) {
            PlanetaryBody other = gameObject.getComponent(PlanetaryBody.class);
            if(other != this) {
                float strength = (G * other.mass()) / position().distanceSquared(other.position());
                Vector2f direction = new Vector2f(other.position()).sub(position()).normalize();
                velocity().add(direction.mul(strength * dt));
            }
        }

        rotation += (rotationSpeed / 360) * dt;
    }
    public void tick(final float dt, final Window window) {
        position().add(new Vector2f(velocity()).mul(dt));

        window.pushMatrix();
        window.translate(position().x, position().y);
        window.rotateZ(rotation);
        window.fill(255);
        window.image(image, 0, 0, radius() * 0.98f, radius() * 0.98f);
        window.popMatrix();

        window.image(shadow, position().x, position().y, radius(), radius());

        window.strokeWeight(4);
        window.stroke(255, 0, 0);
        window.line(position().x, position().y, position().x + velocity().x, position().y);

        window.stroke(0, 0, 255);
        window.line(position().x, position().y, position().x, position().y + velocity().y);

        window.strokeWeight(2);
        window.stroke(0, 255, 0);
        window.line(position().x, position().y, position().x + velocity().x, position().y + velocity().y);
        window.stroke(0, 0);

        if(new Vector2f(window.mouseX - window.camera.x, window.mouseY - window.camera.y).distance(position()) <= radius() / 2f) {
            window.fill(255, 0, 0, 25);
            window.ellipse(position().x, position().y, radius(), radius());

            window.fill(255);
            window.rect(position().x + 147.5f, position().y - 2.5f, 155, 85);
            window.fill(0);
            window.rect(position().x + 150, position().y, 150, 80);

            window.fill(255);

            window.textSize(15);
            window.text(String.format(
                    "%s\n%s kg\n%s km/s",
                    gameObject.name,
                    mass(),
                    velocity().length()
            ), position().x + 155, position().y + 20);
        }
    }

    public PlanetaryBody(float mass, float radius, Vector2f position, PImage image, float rotationSpeed) {
        this.mass = mass;
        this.radius = radius;

        this.image = image;
        this.rotationSpeed = rotationSpeed;

        shadow = AssetPool.getTexture("shadow");

        this.position = position;
        this.velocity = new Vector2f();
    }

    public float mass() {
        return this.mass;
    }
    public float radius() {
        return this.radius;
    }

    public Vector2f position() {
        return this.position;
    }
    public Vector2f velocity() {
        return this.velocity;
    }
}
