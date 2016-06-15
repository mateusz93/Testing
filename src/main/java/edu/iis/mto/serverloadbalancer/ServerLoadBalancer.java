package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) {
        for (Vm vm : vms) {
            getTheLeastLoadedServer(servers).addVm(vm);
        }
    }

    private Server getTheLeastLoadedServer(Server[] servers) {
        Server theLeastLoadedServer = null;
        double theLeastLoadedServerPercentage = 100.1d;
        for (Server server : servers) {
            if (server.currentLoadPercentage < theLeastLoadedServerPercentage) {
                theLeastLoadedServer = server;
                theLeastLoadedServerPercentage = server.currentLoadPercentage;
            }
        }
        return theLeastLoadedServer;
    }
}
