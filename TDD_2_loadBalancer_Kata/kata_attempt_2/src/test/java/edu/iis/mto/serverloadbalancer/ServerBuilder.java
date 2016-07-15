package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class ServerBuilder implements IBuilder<Server> {

    private int capacity;
    private double loadedLevel;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        return new Server(capacity, loadedLevel);
    }

    public IBuilder<Server> withCurrentLoad(double expectedLoaded) {
        loadedLevel = expectedLoaded;
        return this;
    }
}
