package core;

import org.joml.Vector2f;

import java.util.ArrayList;

public class GameObject {
    public final String name;

    public Vector2f position;
    public Vector2f size;

    public void start() {
        for(Component component : components) {
            component.start();
        }
    }

    public void update(final float dt, final Window window) {
        for(Component component : components) {
            component.update(dt, window);
        }
    }

    public void tick(final float dt, final Window window) {
        for(Component component : components) {
            component.tick(dt, window);
        }
    }

    private ArrayList<Component> components;

    public void addComponent(Component component) {
        for(Component _component : components) {
            if(_component.getClass().getName().equals(component.getClass().getName())) {
                System.err.printf("GameObject '%s' already has component %s!", name, component.getClass().getName());
                return;
            }
        }
        component.gameObject = this;
        components.add(component);
    }

    public <C extends Component> C getComponent(Class<C> componentClass) {
        for(Component component : components) {
            if(componentClass.isAssignableFrom(component.getClass())) {
                return componentClass.cast(component);
            }
        }

        System.err.printf("GameObject '%s' does not have component %s!", name, componentClass.getName());
        return null;
    }

    public GameObject(String name) {
        this.name = name;
        this.position = new Vector2f();
        this.size = new Vector2f(50, 50);
        this.components = new ArrayList<>();
    }
    public GameObject(String name, Vector2f position, Vector2f size) {
        this.name = name;
        this.position = position;
        this.size = size;
        this.components = new ArrayList<>();
    }
}
