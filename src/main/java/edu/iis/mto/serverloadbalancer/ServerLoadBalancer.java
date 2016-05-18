package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Mateusz Wieczorek, 18.05.16.
 */
public class ServerLoadBalancer {

    public void balance(Server[] servers, VirtualMachine[] virtualMachines) {
        for (VirtualMachine vm : virtualMachines) {
            addToTheLeastLoadedServer(servers, vm);
        }
    }

    private void addToTheLeastLoadedServer(Server[] servers, VirtualMachine vm) {
        List<Server> capableServers = new ArrayList<Server>();
        for (Server server : servers) {
            if (server.canFit(vm)) {
                capableServers.add(server);
            }
        }
        Server lessLoadedServer = findTheLeastLoadedServer(capableServers);
        if (lessLoadedServer != null) {
            lessLoadedServer.addVirtualMachine(vm);
        }
    }

    private Server findTheLeastLoadedServer(List<Server> servers) {
        Server lessLoadedServer = null;
        for (Server server : servers) {
            if (lessLoadedServer == null || server.currentLoadPercentage < lessLoadedServer.currentLoadPercentage) {
                lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }
}
