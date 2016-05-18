package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 18.05.16.
 */
public class Server {

    private static final double MAXIMUM_LOAD = 100.0d;
    public double currentLoadPercentage;
    public int capacity;

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public boolean contains(VirtualMachine virtualMachine) {
        return true;
    }

    public void addVirtualMachine(VirtualMachine vm) {
        currentLoadPercentage = vm.size / (double) capacity * MAXIMUM_LOAD;
    }
}
