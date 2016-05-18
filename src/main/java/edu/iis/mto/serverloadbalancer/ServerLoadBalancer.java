package edu.iis.mto.serverloadbalancer;

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
        Server lessLoadedServer = findTheLeastLoadedServer(servers);
        lessLoadedServer.addVirtualMachine(vm);
    }

    private Server findTheLeastLoadedServer(Server[] servers) {
        Server lessLoadedServer = null;
        for (Server server : servers) {
            if (lessLoadedServer == null || server.currentLoadPercentage < lessLoadedServer.currentLoadPercentage) {
                lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }
}
