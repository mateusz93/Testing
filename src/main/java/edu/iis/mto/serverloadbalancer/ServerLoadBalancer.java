package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class ServerLoadBalancer {

    private final double MAXIMUM_LOAD = 100.0d;

    public void balance(Server[] servers, Vm[] vms) {
        for (Vm vm : vms) {
            getTheLeastLoadedServer(servers).addVm(vm);
        }
    }

    private Server getTheLeastLoadedServer(Server[] servers) {
        Server theLeastLoadedServer = null;
        double theLeastLoadedServerPercentage = MAXIMUM_LOAD;
        for (Server server : servers) {
            if (server.getCurrentLoadPercentage() < theLeastLoadedServerPercentage) {
                theLeastLoadedServer = server;
                theLeastLoadedServerPercentage = server.getCurrentLoadPercentage();
            }
        }
        return theLeastLoadedServer;
    }
}
