package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class Server {

    public double currentLoadPercentage;
    private int capacity;

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public boolean contains(Vm vm) {
        return true;
    }

    public int getCapacity() {
        return capacity;
    }
}
