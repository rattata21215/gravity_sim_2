package core;

public abstract class Component {
    protected transient GameObject gameObject;

    public void start() {}
    public void update(final float dt, final Window window) {}
    public void tick(final float dt, final Window window) {}
}
