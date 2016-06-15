package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class ServerBuilder implements IBuilder<Server> {

    public int capacity;
    public double loadedPercentage;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        return new Server(capacity);
    }

    public IBuilder<Server> withCurrentLoadOf(double loadedPercentage) {
        this.loadedPercentage = loadedPercentage;
        return this;
    }
}
