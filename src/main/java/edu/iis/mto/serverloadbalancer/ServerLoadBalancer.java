package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 18.05.16.
 */
public class ServerLoadBalancer {

    public void balance(Server[] servers, VirtualMachine[] virtualMachines) {
        for (VirtualMachine vm : virtualMachines) {
            Server lessLoadedServer = null;
            for (Server server : servers) {
                if (lessLoadedServer == null || server.currentLoadPercentage < lessLoadedServer.currentLoadPercentage) {
                    lessLoadedServer = server;
                }
            }
            lessLoadedServer.addVirtualMachine(vm);
        }
    }
}
