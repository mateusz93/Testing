package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 18.05.16.
 */
public class ServerBuilder implements Builder<Server> {

    private int capacity;
    private double initLoad;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        Server server = new Server(capacity);
        if (initLoad > 0) {
            int initialVmSize = (int) (initLoad / (double) capacity * 100.0d);
            VirtualMachine virtualMachine = VirtualMachineBuilder.vm().ofSize(initialVmSize).build();
            server.addVirtualMachine(virtualMachine);
        }
        return server;
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

    public ServerBuilder withCurrentLoadOf(double initLoad) {
        this.initLoad = initLoad;
        return this;
    }
}
